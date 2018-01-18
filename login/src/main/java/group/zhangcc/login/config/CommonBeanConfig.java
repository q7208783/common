package group.zhangcc.login.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;

import group.zhangcc.lib.common.exception.CommonExceptionI18nAspect;
import group.zhangcc.lib.common.response.CommonResponseHandleAspect;
import group.zhangcc.lib.common.utils.PropertyMessageUtils;
import group.zhangcc.login.util.Converter;

/**
 * Created by ZhangChicheng on 2018/1/18.
 */
@Configuration
public class CommonBeanConfig{

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return modelMapper;
	}

	@Bean
	public Converter converter(ModelMapper modelMapper){
		Converter converter = new Converter();
		converter.setModelMapper(modelMapper);
		return converter;
	}

	@Bean
	public CommonExceptionI18nAspect commonExceptionI18nAspect(){
		return new CommonExceptionI18nAspect();
	}

	@Bean
	public CommonResponseHandleAspect commonResponseHandleAspect(){
		return new CommonResponseHandleAspect();
	}

	@Bean
	public PropertyMessageUtils propertyMessageUtils(MessageSource messageSource){
		MessageSourceAccessor messageSourceAccessor = new MessageSourceAccessor(messageSource);
		return new PropertyMessageUtils().setMessageSourceAccessor(messageSourceAccessor);
	}
}
