package jeju.dao.face;

import java.util.List;
import java.util.Map;

import jeju.util.Paging;

public interface AdminReviewBoardDao {
	
	/**
	 * 검색어를 적용해서 일정공유게시글의 갯수를 조회한다.
	 * @param param - 검색어
	 * @return 게시글 수
	 */
	public int selectCntAll(Paging param);
	
	/**
	 * 검색어에 맞게 페이징 처리하여 게시글을 조회한다 
	 * @param paging -  검색어, 페이지 정보
	 * @return 게시글 정보 리스트
	 */
	public List<Map<String, Object>> selectList(Paging paging);
	
	/**
	 * 리스트에 있는 게시글번호에 해당하는 게시글을 모두 삭제한다.
	 * @param pbNo - 게시글번호 리스트
	 */
	public void delete(List<Integer> pbNo);
	
}
