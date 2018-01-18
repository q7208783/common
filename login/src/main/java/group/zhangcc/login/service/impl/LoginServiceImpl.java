package group.zhangcc.login.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import group.zhangcc.lib.common.constant.CommonConstant;
import group.zhangcc.lib.common.constant.ErrorCodeEnum;
import group.zhangcc.lib.common.exception.ServiceException;
import group.zhangcc.lib.common.utils.CookieUtil;
import group.zhangcc.login.constant.UserConstant;
import group.zhangcc.login.model.Token;
import group.zhangcc.login.service.LoginService;
import group.zhangcc.login.util.JwtTokenUtil;
import group.zhangcc.login.vo.LoginRequest;

/**
 * Created by ZhangChicheng on 2017/11/27.
 */
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public Token login(LoginRequest request, Device device, HttpServletResponse response) throws ServiceException {
		try{
			final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
					request.getUsername(),
					request.getPassword()
				)
			);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}catch (Exception e){
			throw new ServiceException(ErrorCodeEnum.AUTH_FAILURE);
		}
		String token0 = JwtTokenUtil.generateToken(request.getUsername(), device);
		CookieUtil.addCookie(response, UserConstant.X_ZCC_TOKEN, token0);
		Token token = new Token();
		token.setToken(token0);
		return token;
	}

	@Override
	public void logout(HttpServletResponse response) {
		CookieUtil.deleleCookie(response, UserConstant.X_ZCC_TOKEN);
	}
}
