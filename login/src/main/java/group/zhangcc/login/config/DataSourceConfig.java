package group.zhangcc.login.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import group.zhangcc.lib.common.utils.DataSourceFactory;
/**
 * Created by ZhangChicheng on 2018/1/18.
 */
@Configuration
public class DataSourceConfig {
	@Bean
	public DataSource userDataSource() {
		return DataSourceFactory.getDataSource("user-database-config.properties");
	}

	@Bean
	public DataSourceTransactionManager transaction(DataSource userDataSource) {
		return new DataSourceTransactionManager(userDataSource);
	}
}
