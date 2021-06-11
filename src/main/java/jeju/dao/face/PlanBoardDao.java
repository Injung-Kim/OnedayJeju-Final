package jeju.dao.face;

import java.util.List;
import java.util.Map;

import jeju.dto.Bookmark;
import jeju.dto.DetailPlan;
import jeju.dto.PBComment;
import jeju.dto.Plan;
import jeju.dto.PlanBoard;
import jeju.dto.PlanLike;
import jeju.util.Paging;

public interface PlanBoardDao {
	
	/**
	 * 전체 게시글 수를 조회한다 (검색어 적용하여)
	 * @param inData - 검색어
	 * @return 총 게시글 수 (검색어 적용)
	 */
	public int selectCntAll(Paging inData);
	
	/**
	 * 정렬, 검색어에 맞게 페이징 처리하여 게시글을 조회한다 
	 * @param paging - 정렬, 검색어, 페이지 정보
	 * @return 게시글 정보 리스트
	 */
	public List<Map<String, Object>> selectList(Paging paging);
	
	/**
	 * 유저번호로 해당유저의 모든 일정 조회
	 * @param uno - 유저번호
	 * @return 해당 유저의 모든 일정
	 */
	public List<Plan> selectPlanByUserno(int uno);
	
	/**
	 * 작성된 일정게시글을 DB에 삽입
	 * @param inData - 일정게시글 정보
	 */
	public void insertPlanBoard(PlanBoard inData);
	
	/**
	 * 일정공유게시글번호로 공유일정게시글 관련 기본정보 조회(공유일정 게시글 TB + 좋아요수)
	 * @param pbNo - 일정공유 게시글 번호
	 * @return 게시글 관련 기본 정보 (게시판 TB + 좋아요수)
	 */
	public Map<String, Object> selectBoardByPbno(int pbNo);
	
	/**
	 * 일정번호로 상세일정 리스트 조회
	 * @param pNo - 일정번호
	 * @return 상세일정 리스트
	 */
	public List<DetailPlan> selectDpListByPno(int pNo);
	
	/**
	 * 상세일정번호로 상세일정의 세부정보들 조회
	 * @param dpNo - 상세일정번호
	 * @return 상세일정의 세부정보들 (여행순서, 장소, 장소에 관한 정보들)
	 */
	public List<Map<String, Object>> selectDetailsByDpno(int dpNo);
	
	/**
	 * 상세조회한 게시글의 조회수 1 증가 (업데이트)
	 * @param pbno - 게시글번호
	 */
	public void updateViews(int pbno);
	
	/**
	 * 게시글 번호로 해당 게시글의 모든 댓글을 조회한다.
	 * @param inData - 게시글 번호
	 * @return 해당 게시글의 모든 댓글
	 */
	public List<PBComment> selectCmtByPbno(PlanBoard inData);
	
	/**
	 * 일정공유 게시글에 댓글을 작성 (댓글 데이터 삽입)
	 * @param inData - 삽입할 댓글 정보
	 */
	public void insertComment(PBComment inData);
	
	/**
	 * 댓글번호로 댓글정보 조회
	 * @param inData - 댓글번호
	 * @return 댓글번호에 해당하는 댓글 정보
	 */
	public PBComment selectCmtByPbcno(PBComment inData);
	
	/**
	 * 댓글번호에 해당하는 댓글데이터 삭제
	 * @param inData - 댓글번호
	 */
	public void deleteComment(PBComment inData);
	
	/**
	 * 게시글번호, 유저번호에 해당하는 좋아요 데이터가 있는지 확인
	 * @param inData - 게시글번호, 유저번호
	 * @return 좋아요 상태 반환 (0 or 1), 데이터가 아예 없으면 0 반환
	 */
	public Integer selectLikePresence(PlanLike inData);
	
	/**
	 * 게시글번호, 유저번호, 좋아요상태 1로 데이터 삽입
	 * @param inData - 유저번호, 게시글번호
	 */
	public void insertLike(PlanLike inData);
	
	/**
	 * 좋아요 상태 변경 
	 * presence == 1 -> pl_status = 0
	 * presence == 0 -> pl_status = 1
	 * @param param - presence, userNo, pbNo
	 */
	public void updateLike(Map<String, Object> param);
	
	/**
	 * 해당 게시글의 좋아요 갯수를 조회
	 * @param inData - 게시글 번호
	 * @return 좋아요 갯수
	 */
	public int selectLikeCnt(PlanLike inData);
	
	/**
	 * 게시글번호, 유저번호에 해당하는 좋아요 상태 조회
	 * @param pl - 게시글번호, 유저번호
	 * @return 좋아요 상태면 1, 아니면 0
	 */
	public int selectLikeStatus(PlanLike pl);
	
	/**
	 * 게시글번호, 유저번호에 해당하는 북마크 데이터가 있는지 확인
	 * @param inData - 게시글번호, 유저번호
	 * @return 북마크 상태 반환 (0 or 1), 데이터가 아예 없으면 0 반환
	 */
	public Integer selectBmPresence(Bookmark inData);
	
	/**
	 * 게시글번호, 유저번호, 북마크상태 1로 데이터 삽입
	 * @param inData - 유저번호, 게시글번호
	 */
	public void insertBookmark(Bookmark inData);
	
	/**
	 * 북마크 상태 변경 
	 * presence == 1 -> bm_status = 0
	 * presence == 0 -> bm_status = 1
	 * @param param - presence, userNo, pbNo
	 */
	public void updateBookmark(Map<String, Object> param);
	
	/**
	 * 게시글번호, 유저번호에 해당하는 북마크 상태 조회
	 * @param pl - 게시글번호, 유저번호
	 * @return 북마크 상태면 1, 아니면 0
	 */
	public int selectBmStatus(Bookmark bm);
	
	/**
	 * 게시글번호로 대표이미지파일명 조회
	 * @param inData - 게시글 번호
	 * @return 이미지파일명 반환
	 */
	public String selectFilenameByPbno(PlanBoard inData);
	
	/**
	 * 게시글번호에 해당하는 공유일정의 이미지파일명을 수정한다.
	 * @param inData - 게시글번호, 새로운 이미지파일명 포함
	 */
	public void updatePlanBoard(PlanBoard inData);
	
	/**
	 * 게시글번호로 게시글정보를 조회한다.
	 * @param inData - 게시글번호
	 * @return 게시글번호에 해당하는 게시글정보
	 */
	public PlanBoard selectPlanBoardByPbno(PlanBoard inData);
	
	/**
	 * 게시글번호로 해당 게시글 삭제
	 * @param inData - 게시글번호
	 */
	public void deletePlanBoardByPbno(PlanBoard inData);

}
