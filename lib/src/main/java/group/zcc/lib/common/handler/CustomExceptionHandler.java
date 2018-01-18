package group.zcc.lib.common.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcc.login.common.constant.ErrorCodeEnum;
import com.zcc.login.common.exception.CommonException;
import com.zcc.login.vo.ExceptionResponse;

/**
 * Created by ZhangChicheng on 2017/11/22.
 */
@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(CommonException.class)
	@ResponseBody
	public ExceptionResponse handleServiceException(CommonException e) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode(e.getErrorCode());
		response.setErrorMsg(e.getErrorMsg());
		return response;
	}

	@ExceptionHandler(AuthenticationException.class)
	@ResponseBody
	public ExceptionResponse handleAuthenticationException(AuthenticationException e) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode(ErrorCodeEnum.AUTH_FAILURE.getErrorCode());
		response.setErrorMsg(e.getMessage());
		return response;
	}
}
