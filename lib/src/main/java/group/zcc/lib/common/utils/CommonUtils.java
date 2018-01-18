package group.zcc.lib.common.utils;

/**
 * Created by ZhangChicheng on 2017/11/10.
 */
public class CommonUtils {
	public static boolean containInDb(int num) {
		return num > 0;
	}

	public static boolean opearationSuccess(int affectRows) {
		return affectRows > 0;
	}
}
