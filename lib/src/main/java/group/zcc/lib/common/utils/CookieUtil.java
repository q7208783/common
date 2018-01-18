package group.zcc.lib.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zcc.login.common.constant.CommonConstant;

/**
 * Created by ZhangChicheng on 2017/11/15.
 */
public class CookieUtil {


	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(int i = 0; i < cookies.length; ++i) {
				if(cookieName.equals(cookies[i].getName())) {
					Cookie cookie = cookies[i];
					if(cookie != null) {
						return cookie.getValue();
					}

					return null;
				}
			}
		}

		return null;
	}

	public static void addCookie(HttpServletResponse response, String key, String value){
		Cookie cookie = new Cookie(key, value);
		cookie.setDomain(CommonConstant.DOMAIN_NAME);
		cookie.setPath("/");
		cookie.setMaxAge(604800);
		response.addCookie(cookie);
	}

	public static void deleleCookie(HttpServletResponse response, String key){
		Cookie cookie = new Cookie(key, null);
		cookie.setDomain(CommonConstant.DOMAIN_NAME);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
}
