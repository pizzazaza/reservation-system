package kr.or.connect.reservation.domain;

public class ReservationUserCommentImage {
	
	Integer id;
	Integer reservationUserCommentId;
	Integer fileId;
	
	public ReservationUserCommentImage(){
		
	}
	
	public ReservationUserCommentImage(Integer id, Integer reservationUserCommentId, Integer fileId){
		this.id = id;
		this.reservationUserCommentId = reservationUserCommentId;
		this.fileId = fileId;
	}
	
	public ReservationUserCommentImage(Integer reservationUserCommentId){
		this.reservationUserCommentId = reservationUserCommentId;
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getReservationUserCommentId() {
		return reservationUserCommentId;
	}
	public void setReservationUserCommentId(Integer reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	
	
	
}
