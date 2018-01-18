package group.zcc.lib.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zcc.login.common.constant.DataSourceEnum;

/**
 * Created by ZhangChicheng on 2017/11/30.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceType {

	DataSourceEnum value() default DataSourceEnum.LOGIN;
}
