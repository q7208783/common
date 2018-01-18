package group.zhangcc.lib.common.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import group.zhangcc.lib.common.constant.PasswordConfig;
import lombok.extern.apachecommons.CommonsLog;

/**
 * Created by ZhangChicheng on 2018/1/18.
 */
@CommonsLog
public class DataSourceFactory {
	private static Map<String, HikariDataSource> dataSourceMap;

	static {
		dataSourceMap = new HashMap<>();
	}

	private static HikariConfig loadConfig(String configuration) {
		Properties properties = new Properties();
		try {
			properties.load(DataSourceFactory.class.getClassLoader().getResourceAsStream(configuration));
		} catch (IOException e) {
			log.error("an error occurred", e);
		}
		HikariConfig config = new HikariConfig(properties);
		config.setUsername(PasswordConfig.datasourceUsername);
		config.setPassword(PasswordConfig.datasourcePassword);
		return config;
	}

	private static HikariDataSource getDataSourceInternal(String configuration) {
		HikariConfig config = loadConfig(configuration);
		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}

	public static HikariDataSource getDataSource(String configuration){
		log.debug("retrieving data source from Mysql");
		if (dataSourceMap.get(configuration) == null) {
			synchronized (DataSourceFactory.class) {
				if (dataSourceMap.get(configuration) == null) {
					HikariDataSource dataSource = getDataSourceInternal(configuration);
					dataSourceMap.put(configuration, dataSource);
				}
			}
		}
		return dataSourceMap.get(configuration);
	}
}
