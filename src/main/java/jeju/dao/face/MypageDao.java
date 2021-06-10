package jeju.dao.face;

import java.util.HashMap;

import jeju.dto.JejuUser;

public interface MypageDao {

	
	/**
	 * 로그인 한 유저의 데이터 가져오기
	 * 
	 * @param userno - 로그인한 유저의 userno
	 * @return 유저 정보 객체
	 */
	public JejuUser selectUserByUserno(int userno);
	
	
	/**
	 * 존재하는 닉네임인지 확인하기
	 * 
	 * @param nick - DB의 닉네임 객체
	 * @return 해당 닉네임이 존재하는 수
	 */
	public int selectCntNick(String nick);
	
	
	/**
	 * 로그인 한 유저의 닉네임 변경하기
	 * 
	 * @param userno - 로그인한 유저의 userno
	 * @return 유저 정보 객체
	 */
	public void updateUserNick(JejuUser user);


	/**
	 * 프로필 사진 보여주기
	 * 
	 * @param userno - userno를 가진 회원의 프로필 사진
	 * @return - userno를 가진 회원 객체
	 */
	public JejuUser selectByProfile(int userno);

	
	/**
	 * 파일 정보 업데이트 전 null값 채워주기
	 * 
	 * @param setfile
	 */
	public void deleteProfile(JejuUser user);

	
	/**
	 * 유저 테이블(DB)에 업로드된 파일 정보 업데이트 하기
	 * 
	 * @param userfile - 업로드된 파일 정보 객체
	 */
	public void updateProfile(JejuUser user);


	/**
	 * 프로필 사진 삭제하기
	 * 
	 * @param user - 로그인한 회원 객체
	 */
	public void deletePhoto(JejuUser user);


	/**
	 * 유저 정보 변경하기
	 * 
	 * @param userno - 정보 변경을 요청한 유저 번호 객체
	 */
	public void updateUser(JejuUser user);


	/**
	 * 기존 비밀번호 일치 여부 확인
	 * 
	 * @param oldpw - 얻어온 현재 비밀번호 정보
	 * @return - true: 변경 가능 / false: 변경 불가능
	 */
	public int checkPw(HashMap<String, Object> map);


	/**
	 * 기존 비밀번호 일치했을 경우 비밀번호 변경
	 * 
	 * @param user - 비밀번호를 변경할 회원 객체
	 */
	public void updatePw(JejuUser user);




}
