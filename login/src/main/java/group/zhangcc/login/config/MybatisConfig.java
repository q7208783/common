package group.zhangcc.login.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ZhangChicheng on 2017/11/8.
 */
@Configuration
@MapperScan(basePackages = "group.zhangcc.login.mapper")
public class MybatisConfig {
}
