package group.zhangcc.lib.common.response;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import group.zhangcc.lib.common.utils.PropertyMessageUtils;

/**
 * Created by ZhangChicheng on 2017/11/28.
 */
@Aspect
public class CommonResponseHandleAspect {
	private static final String MSG_PREFIX = "common.message.";

	@Around(value = "@annotation(org.springframework.web.bind.annotation.ResponseBody)"
		+ "&&execution(* group.zhangcc.*.controller.*Controller.*(..))")
	public Object handleResponse(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		CommonResponse returnValue = (CommonResponse)joinPoint.proceed();
		long endTime = System.currentTimeMillis();
		returnValue.setProcTime((endTime - startTime) + "ms");
		String returnCode = returnValue.getReturnCode();
		String propertyCode = MSG_PREFIX + returnCode;
		String message = PropertyMessageUtils.getMessage(propertyCode);
		returnValue.setReturnMsg(message);
		return returnValue;
	}
}
