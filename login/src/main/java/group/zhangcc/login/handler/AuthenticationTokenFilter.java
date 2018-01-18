package group.zhangcc.login.handler;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import group.zhangcc.lib.common.utils.CookieUtil;
import group.zhangcc.login.constant.UserConstant;
import group.zhangcc.login.model.User;
import group.zhangcc.login.service.UserService;
import group.zhangcc.login.util.Converter;
import group.zhangcc.login.util.JwtTokenUtil;
import lombok.extern.apachecommons.CommonsLog;

/**
 * Created by ZhangChicheng on 2017/11/20.
 */
@Component
@CommonsLog
public class AuthenticationTokenFilter extends OncePerRequestFilter {
	private final Logger logger = LoggerFactory.getLogger(AuthenticationTokenFilter.class);

	@Autowired
	private UserService userService;
	@Autowired
	private Converter converter;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
		FilterChain filterChain) throws ServletException, IOException {
		String token = CookieUtil.getCookieValue(httpServletRequest, UserConstant.X_ZCC_TOKEN);
		log.debug("token is ： " + token);
		if (token == null) {
			token = httpServletRequest.getHeader(UserConstant.X_ZCC_TOKEN);
		}
		String userName = JwtTokenUtil.getUsernameFromToken(token);
		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			User user = userService.getUser(userName);
			user.setToken(token);
			UserDetails userDetails = converter.covertUser2AuthUser(user);
			if (JwtTokenUtil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					userDetails, "", userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
				logger.info("authenticated user " + userName + ", setting security context");
				SecurityContextHolder.getContext().setAuthentication(authentication);
				httpServletRequest.setAttribute("user", user);
			}
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
