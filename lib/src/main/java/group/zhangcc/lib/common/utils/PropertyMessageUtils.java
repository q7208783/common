package group.zhangcc.lib.common.utils;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

/**
 * Created by ZhangChicheng on 2017/11/28.
 */


public class PropertyMessageUtils {

	private MessageSourceAccessor messageSourceAccessor;

	private static PropertyMessageUtils propertyMessageUtils;

	@PostConstruct
	public void init(){
		propertyMessageUtils = this;
		propertyMessageUtils.messageSourceAccessor = this.messageSourceAccessor;
	}

	public PropertyMessageUtils setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor){
		this.messageSourceAccessor=messageSourceAccessor;
		return this;
	}

	public static String getMessage(String key){
		if(LocaleContextHolder.getLocaleContext() == null){
			return propertyMessageUtils.messageSourceAccessor.getMessage(key, Locale.CHINA);
		}
		return propertyMessageUtils.messageSourceAccessor.getMessage(key, LocaleContextHolder.getLocaleContext().getLocale());
	}

	public static String getMessage(String key, Locale locale){
		return propertyMessageUtils.messageSourceAccessor.getMessage(key, locale);
	}
}
