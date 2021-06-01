package jeju.dto;

import java.util.Date;

public class JejuUser {
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private String userNick;
	private Date userBirthDate;
	private String userEmail;
	private String userPhone;
	private String userZipcode;
	private String userAddress;
	private String userAddressDetail;
	private String userAgree;
	private Date userJoinDate;
	private String userMailAuthkey;
	private String userMailAuth;
	private String userGrade;
	private String userOrigin;
	private String userStored;
	
	
	@Override
	public String toString() {
		return "JejuUser [userNo=" + userNo + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", userNick=" + userNick + ", userBirthDate=" + userBirthDate + ", userEmail=" + userEmail
				+ ", userPhone=" + userPhone + ", userZipcode=" + userZipcode + ", userAddress=" + userAddress
				+ ", userAddressDetail=" + userAddressDetail + ", userAgree=" + userAgree + ", userJoinDate="
				+ userJoinDate + ", userMailAuthkey=" + userMailAuthkey + ", userMailAuth=" + userMailAuth
				+ ", userGrade=" + userGrade + ", userOrigin=" + userOrigin + ", userStored=" + userStored + "]";
	}


	public int getUserNo() {
		return userNo;
	}


	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserPw() {
		return userPw;
	}


	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserNick() {
		return userNick;
	}


	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}


	public Date getUserBirthDate() {
		return userBirthDate;
	}


	public void setUserBirthDate(Date userBirthDate) {
		this.userBirthDate = userBirthDate;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserPhone() {
		return userPhone;
	}


	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}


	public String getUserZipcode() {
		return userZipcode;
	}


	public void setUserZipcode(String userZipcode) {
		this.userZipcode = userZipcode;
	}


	public String getUserAddress() {
		return userAddress;
	}


	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}


	public String getUserAddressDetail() {
		return userAddressDetail;
	}


	public void setUserAddressDetail(String userAddressDetail) {
		this.userAddressDetail = userAddressDetail;
	}


	public String getUserAgree() {
		return userAgree;
	}


	public void setUserAgree(String userAgree) {
		this.userAgree = userAgree;
	}


	public Date getUserJoinDate() {
		return userJoinDate;
	}


	public void setUserJoinDate(Date userJoinDate) {
		this.userJoinDate = userJoinDate;
	}


	public String getUserMailAuthkey() {
		return userMailAuthkey;
	}


	public void setUserMailAuthkey(String userMailAuthkey) {
		this.userMailAuthkey = userMailAuthkey;
	}


	public String getUserMailAuth() {
		return userMailAuth;
	}


	public void setUserMailAuth(String userMailAuth) {
		this.userMailAuth = userMailAuth;
	}


	public String getUserGrade() {
		return userGrade;
	}


	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}


	public String getUserOrigin() {
		return userOrigin;
	}


	public void setUserOrigin(String userOrigin) {
		this.userOrigin = userOrigin;
	}


	public String getUserStored() {
		return userStored;
	}


	public void setUserStored(String userStored) {
		this.userStored = userStored;
	}
	
}
