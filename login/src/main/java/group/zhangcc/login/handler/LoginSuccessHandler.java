package group.zhangcc.login.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Created by ZhangChicheng on 2017/10/30.
 */

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LoginSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
		Authentication authentication) throws IOException, ServletException {
		handle(httpServletRequest,httpServletResponse,authentication);
	}

	// TODO: 2017/10/30 login success handle
	// http://blog.csdn.net/superdog007/article/details/49757237
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication){

	}
}
