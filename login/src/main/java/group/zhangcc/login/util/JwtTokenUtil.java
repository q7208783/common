package group.zhangcc.login.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;

import group.zhangcc.lib.common.utils.DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Created by ZhangChicheng on 2017/11/13.
 */
public class JwtTokenUtil implements Serializable {
	private static final long serialVersionUID = -3301605591108950415L;

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_AUDIENCE = "audience";
	static final String CLAIM_KEY_CREATED = "created";
	static final String CLAIM_KEY_EXPIRED = "exp";

	static final String AUDIENCE_UNKNOWN = "unknown";
	static final String AUDIENCE_WEB = "web";
	static final String AUDIENCE_MOBILE = "mobile";
	static final String AUDIENCE_TABLET = "tablet";


	private final static String secret = "emhhbmdjaGljaGVuZw==";


	private final static Long expiration = 604800l;

	public static String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	public static Date getCreatedDateFromToken(String token) {
		Date created;
		try {
			final Claims claims = getClaimsFromToken(token);
			created = new Date((Long)claims.get(CLAIM_KEY_CREATED));
		} catch (Exception e) {
			created = null;
		}
		return created;
	}

	public static Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	public static long getRemainExpireTime(String token) {
		long exDate = getExpirationDateFromToken(token).getTime();
		long now = DateUtil.now().getTime();
		return exDate - now;
	}

	public static String getAudienceFromToken(String token) {
		String audience;
		try {
			final Claims claims = getClaimsFromToken(token);
			audience = (String)claims.get(CLAIM_KEY_AUDIENCE);
		} catch (Exception e) {
			audience = null;
		}
		return audience;
	}

	private static Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	private static Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
		return (lastPasswordReset != null && created.before(lastPasswordReset));
	}

	private static String generateAudience(Device device) {
		String audience = AUDIENCE_UNKNOWN;
		if (device.isNormal()) {
			audience = AUDIENCE_WEB;
		} else if (device.isTablet()) {
			audience = AUDIENCE_TABLET;
		} else if (device.isMobile()) {
			audience = AUDIENCE_MOBILE;
		}
		return audience;
	}

	private static Boolean ignoreTokenExpiration(String token) {
		String audience = getAudienceFromToken(token);
		return (AUDIENCE_TABLET.equals(audience) || AUDIENCE_MOBILE.equals(audience));
	}

	public static String generateToken(String userName, Device device) {
		Map<String, Object> claims = new HashMap<>();

		claims.put(CLAIM_KEY_USERNAME, userName);
		claims.put(CLAIM_KEY_AUDIENCE, generateAudience(device));

		final Date createdDate = DateUtil.now();
		claims.put(CLAIM_KEY_CREATED, createdDate);

		return doGenerateToken(claims);
	}

	private static String doGenerateToken(Map<String, Object> claims) {
		final Date createdDate = (Date)claims.get(CLAIM_KEY_CREATED);
		final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);

		System.out.println("doGenerateToken " + createdDate);

		return Jwts.builder()
			.setClaims(claims)
			.setExpiration(expirationDate)
			.signWith(SignatureAlgorithm.HS512, secret)
			.compact();
	}

	public static Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
		final Date created = getCreatedDateFromToken(token);
		return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
			&& (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}

	public static Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(DateUtil.now());
	}

	public String refreshToken(String token) {
		String refreshedToken;
		try {
			final Claims claims = getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, DateUtil.now());
			refreshedToken = doGenerateToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}

	public static Boolean validateToken(String token, UserDetails userDetails) {

		final String username = getUsernameFromToken(token);
		return (
			username.equals(userDetails.getUsername())
				&& !isTokenExpired(token)
				&& userDetails.isAccountNonExpired());
	}
}
