package group.zhangcc.lib.common.utils;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by stephan on 04.07.17.
 */
public class DateUtil implements Serializable {

    private static final long serialVersionUID = -3301695478208950415L;
    private static final String timePattern = "yyyy-MM-dd HH:mm:ss";
    private static final Logger LOGGER= LoggerFactory.getLogger(DateUtil.class);
    public static Date now() {
        return new Date();
    }

    public static String timeStamp(){
        return Instant.now().toString();
    }

    public static Date getDateFromYmdt(String ymdt){
        Instant timestamp = Instant.parse(ymdt);
        Date date = Date.from(timestamp);
        return date;
    }
}
