package group.zcc.lib.common.exception;

import com.zcc.login.common.constant.ErrorCodeEnum;

/**
 * Created by ZhangChicheng on 2017/11/22.
 */
public class CommonException extends Exception {
	protected String errorCode;
	protected String errorMsg;
	public static final int COMMON_DEFAULT_CODE = 10000;

	public CommonException() {
	}

	public CommonException(ErrorCodeEnum errorCodeEnum) {
		this.errorCode = errorCodeEnum.getErrorCode();
		this.errorMsg = errorCodeEnum.getErrorMsg();
	}

	public CommonException(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
