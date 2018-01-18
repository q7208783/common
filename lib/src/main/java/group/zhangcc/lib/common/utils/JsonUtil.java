package group.zhangcc.lib.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.apachecommons.CommonsLog;

/**
 * Created by ZhangChicheng on 2017/12/11.
 */
@CommonsLog
public class JsonUtil {
	private static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static String toJson(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			log.error("Json coverted got error", e);
		}
		return null;
	}

	public static Object toObject(String json, Class clazz){
		try{
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			log.error("Object converted got error",e);
		}
		return null;
	}
}
