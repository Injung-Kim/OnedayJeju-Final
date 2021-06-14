package jeju.service.face;


import jeju.dto.JejuUser;

public interface JoinService {
	
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
