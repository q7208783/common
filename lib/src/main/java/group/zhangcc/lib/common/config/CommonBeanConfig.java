package group.zhangcc.lib.common.config;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
@Configuration
public class CommonBeanConfig {
	@Bean
	public MessageSourceAccessor messageSourceAccessor(MessageSource messageSource){
		MessageSourceAccessor messageSourceAccessor = new MessageSourceAccessor(messageSource);
		return messageSourceAccessor;
	}
	@Bean
	public ObjectMapper objectMapper(){
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper;
	}
}
