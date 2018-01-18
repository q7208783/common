package group.zhangcc.lib.common.exception;

import group.zhangcc.lib.common.constant.ErrorCodeEnum;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
public class ServiceException extends CommonException{

	private final static String DEFAULT_MSG = "service.default.error";
	private final static String DEFAULT_CODE = "10000";

	public ServiceException(){
		this.errorCode = DEFAULT_CODE;
		this.errorMsg = DEFAULT_MSG;
	}

	public ServiceException(ErrorCodeEnum errorCodeEnum){
		this.errorCode = errorCodeEnum.getErrorCode();
		this.errorMsg = errorCodeEnum.getErrorMsg();
	}

	public ServiceException(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
