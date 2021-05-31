package jeju.dao.face;

import jeju.dto.JejuUser;

public interface LoginDao {
	
	
	/**
	 * 일치하는 회원 수 조회
	 * 
	 * @param login - 입력된 id, pw 정보
	 * @return 1: 존재, 0: 존재하지않음
	 */
	public int selectCntUser(JejuUser login);
	
	
	/**
	 * 로그인한 회원의 닉네임 가져오기
	 * 
	 * @param login - 로그인한 회원 정보 객체
	 * @return 로그인한 회원의 닉네임
	 */
	public String selectUserNick(JejuUser login);
	
	/**
	 * 로그인한 회원의 등급 정보 가져오기
	 * 
	 * @param login - 로그인한 회원 정보 객체
	 * @return 로그인한 회원의 등급 정보
	 */
	public String selectUserGrade(JejuUser login);
	
	/**
	 * 로그인한 회원의 회원번호 가져오기
	 * 
	 * @param login - 로그인한 회원 정보 객체
	 * @return 로그인한 회원의 회원번호
	 */
	public int selectUserNo(JejuUser login);
	
	
	/**
	 * 이름과 휴대폰 번호를 이용한 회원 id 찾기
	 * 
	 * @param userData - 입력된 이름, 휴대폰 번호
	 * @return 이름, 휴대폰 번호가 일치하는 아이디 객체
	 */
	public String selectSearchId(JejuUser userData);
	
	
	/**
	 * 아이디와 이메일을 이용한 회원 찾기
	 * 
	 * @param userData - 입력된 아이디, 이메일
	 * @return 아이디, 이메일이 일치하는 회원 객체
	 */
	public boolean selectCntMem(JejuUser userData);
	
	
	/**
	 * UUID로 생성된 임시 비밀번호로 비밀번호 변경하기
	 * 
	 * @param userData - 회원 id, email 정보
	 */
	public void updatePw(JejuUser userData);
	
	
}
