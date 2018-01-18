package group.zhangcc.login.constant;

import group.zhangcc.login.model.Authority;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
public enum AuthorityEnum {

	ADMIN(1,"ADMIN"),USER(2,"USER");

	private int authId;
	private String authName;

	AuthorityEnum(int authId, String authName) {
		this.authId = authId;
		this.authName = authName;
	}

	public int getAuthId() {
		return authId;
	}

	public String getAuthName() {
		return authName;
	}

	public static Authority getAuthority(AuthorityEnum authorityEnum){
		Authority authority = new Authority();
		authority.setAuthId(authorityEnum.getAuthId());
		authority.setAuthName(authorityEnum.getAuthName());
		return authority;
	}
}
