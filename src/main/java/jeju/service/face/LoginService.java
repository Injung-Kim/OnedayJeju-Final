package jeju.service.face;

import jeju.dto.JejuUser;

public interface LoginService {

	/**
	 * 회원 존재 유무 확인
	 * 
	 * @param login - 입력된 id, pw 정보
	 * @return 1: 존재하는 회원, 0: 존재하지 않는 회원
	 */
	public boolean login(JejuUser login);
	
	
	/**
	 * 로그인한 회원의 닉네임 가져오기
	 * 
	 * @param login - 로그인한 회원 정보 객체
	 * @return 로그인한 회원의 닉네임
	 */
	public String getNickData(JejuUser login);
	
	
	/**
	 * 로그인한 회원의 등급 정보 가져오기
	 * 
	 * @param login - 로그인한 회원 정보 객체
	 * @return 로그인한 회원의 등급 정보
	 */
	public String getGradeData(JejuUser login);
	
	
	/**
	 * 이름과 휴대폰 번호를 이용한 회원 id 찾기
	 * 
	 * @param name - 입력된 회원 이름
	 * @param phone - 입력된 회원 휴대폰 번호
	 * @return 이름, 휴대폰 번호가 일치하는 아이디 객체
	 */
	public String getSearchId(String name, String phone);
	
	
	/**
	 * id와 이메일을 이용한 회원 찾기
	 * 
	 * @param id - 입력된 id
	 * @param mail - 입력된 mail
	 * @return id, mail이 일치하는 회원 객체
	 */
	public String getSearchPw(String id, String email);
	
}
