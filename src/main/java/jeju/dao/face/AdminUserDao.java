package jeju.dao.face;

import java.util.List;

import jeju.dto.JejuUser;
import jeju.util.Paging;

public interface AdminUserDao {

	/**
	 * 전체 회원 수 조회
	 * 
	 * @return 총 회원 수
	 */
	public int selectCountAll();

	/**
	 * 페이징 적용한 회원 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return 페이징이 적용된 회원 목록
	 */
	public List<JejuUser> selectPageList(Paging paging);
	
	/**
	 * userNo를 이용한 사용자정보 조회
	 * 
	 * @param userNo - 선택된 사용자아이디의 사용자번호
	 * @return 조회된 사용자정보
	 */
	public JejuUser selectInfoByUserNo(int userNo);

	/**
	 * 사용자정보 삭제
	 * 
	 * @param userNo - 삭제할 사용자의 번호
	 */
	public void delete(int userNo);
	
}
