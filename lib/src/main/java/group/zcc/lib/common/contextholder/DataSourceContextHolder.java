package group.zcc.lib.common.contextholder;

import com.zcc.login.common.constant.DataSourceEnum;

/**
 * Created by ZhangChicheng on 2017/11/30.
 */
public class DataSourceContextHolder {
	private static final ThreadLocal<DataSourceEnum> CONTEXT_HOLDER =
		ThreadLocal.withInitial(() -> DataSourceEnum.LOGIN);

	public static void setDataSourceType(DataSourceEnum type) {
		CONTEXT_HOLDER.set(type);
	}

	public static DataSourceEnum getDataSourceType() {
		return CONTEXT_HOLDER.get();
	}

	public static void resetDataSourceType() {
		setDataSourceType(DataSourceEnum.LOGIN);
	}

	public static void clearDataSourceType() {
		CONTEXT_HOLDER.remove();
	}
}
