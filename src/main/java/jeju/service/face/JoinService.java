package jeju.service.face;


import jeju.dto.JejuUser;

public interface JoinService {
	
	/**
	 * 전달 받은 아이디 중복값 조회하기
	 * 
	 * @param userId - 전달 받은 아이디
	 * @return 중복조회된 값
	 */
	public int checkId(String userId);
	
	/**
	 * 전달 받은 닉네임 중복값 조회하기
	 * 
	 * @param userNick - 전달 받은 닉네임
	 * @return 중복조회된 값
	 */
	public int checkNick(String userNick);
	
	/**
	 * 신규 회원가입 처리
	 * 
	 * @param user - 신규 회원정보를 가진 객체
	 */
	public void join(JejuUser user);

	/**
	 * 이메일 인증 상태 변경
	 * 
	 * @param authkey - 전달 받은 사용자 인증키
	 */
	public void checkMailAuth(String authkey);
	
}
