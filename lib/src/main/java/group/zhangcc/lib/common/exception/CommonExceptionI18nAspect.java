package group.zhangcc.lib.common.exception;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import group.zhangcc.lib.common.constant.CommonConstant;
import group.zhangcc.lib.common.utils.PropertyMessageUtils;
import lombok.extern.apachecommons.CommonsLog;

/**
 * Created by ZhangChicheng on 2017/11/22.
 */
@Aspect
@CommonsLog
public class CommonExceptionI18nAspect {

	@AfterThrowing(pointcut = "execution(* group.zhangcc.*.service.impl.*Impl.*(..))", throwing = "ex")
	public void handleExceptionMsg(CommonException ex) {
		log.debug("*********CommonExceptionI18nAspect.handleExceptionMsg start");
		if (ex == null) {
			log.debug("*********** params ex is null");
			return;
		}
		String errorCode = ex.getErrorCode();
		String errorMsg = ex.getErrorMsg();
		log.debug("*******errorCode : " + errorCode);
		log.debug("*******errorMsg : " + errorMsg);
		String propertyCode;
		String message;
		if (ex instanceof ServiceException) {
			propertyCode = CommonConstant.SERVICE_EX_PREFIX + errorCode;
		} else {
			propertyCode = CommonConstant.COMMON_EX_PREFIX + errorCode;
		}

		message = PropertyMessageUtils.getMessage(propertyCode);
		ex.setErrorMsg(message);
		log.debug("**********CommonException.errorMsg is : " + ex.getErrorMsg());
		log.debug("*********CommonExceptionI18nAspect.handleExceptionMsg end");
	}
}
