package com.redwood.rp.flaunt.bo;


// ThreadLocal object to save UserRegistrationDetail with institutionVip and institutionDepositORD only
public class UserRegDetailThreadLocal {

	public static final ThreadLocal<UserRegistrationDetailBO> userRegThreadLocal = new ThreadLocal<UserRegistrationDetailBO>();

	public static void set(UserRegistrationDetailBO userRegDetail) {
		userRegThreadLocal.set(userRegDetail);
	}

	public static void unset() {
		userRegThreadLocal.remove();
	}

	public static UserRegistrationDetailBO get() {
		return userRegThreadLocal.get();
	}
}

