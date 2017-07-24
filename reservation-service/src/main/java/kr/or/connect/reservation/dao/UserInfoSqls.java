package kr.or.connect.reservation.dao;

public class UserInfoSqls {
	public static String SELECT_USER_PHONE_BY_EMAIL = ""
			+ "select tel "
			+ "from users "
			+ "where email =:email";
}
