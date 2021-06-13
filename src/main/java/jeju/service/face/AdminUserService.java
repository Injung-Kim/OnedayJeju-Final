package jeju.service.face;

import java.util.List;

import jeju.dto.JejuUser;
import jeju.util.Paging;

public interface AdminUserService {

	/**
	 * 회원 목록을 위한 페이징 객체 생성
	 * 
	 * @param inData - curPage(현재 페이지)를 저장하고 있는 Paging 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public Paging getPaging(Paging inData);

	/**
	 * 페이징이 적용된 게시글 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return 페이징이 적용된 회원 목록
	 */
	public List<JejuUser> getList(Paging paging);

	/**
	 * userNo를 이용하여 사용자정보 상세조회
	 * 
	 * @param userNo - 상세조회할 사용자번호
	 * @return 조회된 사용자정보 객체
	 */
	public JejuUser getInfo(int userNo);
	
	/**
	 * 사용자정보 삭제 처리
	 * 
	 * @param userNo - 삭제할 사용자의 사용자번호
	 */
	public void delete(int userNo);

}
