package jeju.dao.face;


import jeju.dto.JejuUser;

public interface JoinDao {

	/**
	 * 아이디 중복 조회
	 * 
	 * @param userId - 조회할 아이디
	 * @return 중복조회된 결과값
	 */
	public int selectCountId(String userId);
	
	/**
	 * 닉네임 중복 조회
	 * 
	 * @param userNick - 조회할 닉네임
	 * @return - 중복조회된 결과값
	 */
	public int selectCountNick(String userNick);
	
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
