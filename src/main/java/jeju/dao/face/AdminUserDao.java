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



}
