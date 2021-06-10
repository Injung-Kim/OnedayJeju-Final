package jeju.dao.face;


import jeju.dto.JejuUser;

public interface JoinDao {

	/**
	 * 신규 회원정보 삽입
	 * 
	 * @param user - 신규 회원가입 정보
	 */
	public void insertUser(JejuUser user);

	/**
	 * 이메일 인증 상태 업데이트
	 * 
	 * @param authkey - 인증키 정보
	 */
	public void updateMailAuth(String authkey);

}
