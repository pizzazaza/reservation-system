package kr.or.connect.reservation.dao;

public class ReservationSqls {
	final static public String SELECT_ALL = "select * from category";
	final static public String DELETE_BY_ID = "delete from category where id = :id";
	final static public String INSERT_CATEGORY = "insert into category(name) values(:name)";
}
