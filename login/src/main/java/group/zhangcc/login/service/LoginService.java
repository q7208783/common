package group.zhangcc.login.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.mobile.device.Device;

import group.zhangcc.lib.common.exception.ServiceException;
import group.zhangcc.login.model.Token;
import group.zhangcc.login.vo.LoginRequest;

/**
 * Created by ZhangChicheng on 2017/11/27.
 */
public interface LoginService {
	Token login(LoginRequest request, Device device,
		HttpServletResponse response)throws ServiceException;

	void logout(HttpServletResponse response);
}
