package jeju.service.face;

import java.util.List;
import java.util.Map;

import jeju.util.Paging;

public interface AdminPlanBoardService {
	
	/**
	 * 페이징 객체 생성 (검색어 적용)
	 * @param param - 검색어
	 * @return 페이징객체
	 */
	public Paging getPaging(Paging param);
	
	/**
	 * 일정공유 게시글을 검색어 적용, 페이징처리 하여 조회
	 * @param paging - 검색어 정보, 페이지 정보
	 * @return 게시글 정보를 담은 Map 리스트
	 */
	public List<Map<String, Object>> getList(Paging paging);
	
	/**
	 * 리스트에 있는 게시글번호에 해당하는 게시글을 모두 삭제한다.
	 * @param pbNo - 게시글번호 리스트
	 */
	public void remove(List<Integer> pbNo);

}
