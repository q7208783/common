package group.zhangcc.login.model;

import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/10/30.
 */
@Data
public class Authority {
	private int authId;
	private String authName;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Authority authority = (Authority) o;

		if (authId != authority.authId) return false;
		return authName != null ? authName.equals(authority.authName) : authority.authName == null;
	}
}
