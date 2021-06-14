package jeju.service.face;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import jeju.dto.Bookmark;
import jeju.dto.DetailPlan;
import jeju.dto.PBComment;
import jeju.dto.Plan;
import jeju.dto.PlanBoard;
import jeju.dto.PlanLike;
import jeju.util.Paging;

public interface PlanBoardService {
	
	/**
	 * 일정공유 게시글을 정렬, 검색, 페이징처리 하여 조회
	 * @param paging - 페이지 정보, 정렬정보, 검색어 정보
	 * @return 게시글 정보 Map
	 */
	public List<Map<String, Object>> getList(Paging paging);
	
	/**
	 * 페이징 객체 생성 (정렬, 검색어에 맞게)
	 * @param inData
	 * @return 페이징객체
	 */
	public Paging getPaging(Paging inData);
	
	/**
	 * 유저번호로 해당 유저의 모든 일정 조회
	 * @param uno - 유저번호
	 * @return 유저의 모든 일정 
	 */
	public List<Plan> getPlanList(int uno);
	
	/**
	 * 일정공유게시판에 일정 등록하기 (데이터 삽입)
	 * @param inData - 작성자가 입력한 일정정보와 글제목
	 * @param imgfile - 작성자가 업로드한 대표이미지파일
	 * @return 데이터삽입이 이루어지고 일정게시판번호가 포함되어 반환된 일정게시글
	 */
	public PlanBoard write(PlanBoard inData, MultipartFile imgfile);
	
	/**
	 * 공유일정 상세보기에 필요한 정보들을 조회한다.
	 * @param pbNo - 공유일정 게시글 번호
	 * @return 공유일정 정보들
	 */
	public Map<String, Object> getPlanBoardInfo(String pbNo);
	
	/**
	 * 일정번호로 상세일정번호 리스트 조회
	 * @param pNo - 게시글번호
	 * @return 상세일정 리스트
	 */
	public List<DetailPlan> getDpList(int pNo);
	
	/**
	 * 상세일정번호로 상세일정 정보들 조회(여행 순서, 장소관련정보(좌표, 장소명, contentid ...))
	 * @param dpNo - 상세일정번호
	 * @return 상세일정의 세부정보
	 */
	public List<Map<String, Object>> getDetails(int dpNo);
	
	/**
	 * 일정공유 게시글 번호로 해당 게시글에 작성된 댓글 전체 조회
	 * @param inData - 게시글 번호
	 * @return 게시글에 작성된 모든 댓글
	 */
	public List<PBComment> getCmtList(PlanBoard inData);
	
	/**
	 * 일정공유 게시글에 댓글 작성 및 작성한 댓글 반환
	 * @param inData - 댓글 정보 (게시글번호, 유저번호, 댓글내용)
	 * @return 작성한 댓글 정보
	 */
	public PBComment writeComment(PBComment inData);
	
	/**
	 * 일정공유 게시글의 댓글을 삭제한다.
	 * @param inData - 댓글번호
	 */
	public void removeComment(PBComment inData);
	
	/**
	 * 좋아요 테이블에 유저 정보 없으면 데이터 삽입, 좋아요 상태: 1
	 * 좋아요 테이블에 유저 정보 있으면 1) 좋아요 상태 0 이였으면 1로 변경, 2) 좋아요 상태 1 이였으면 0으로 변경
	 * @param inData - 게시글번호, 유저번호
	 * @return 데이터 변경후 유저의 좋아요 상태  O -> 1, X -> 0
	 */
	public int modifyLike(PlanLike inData);
	
	/**
	 * 해당 게시글의 좋아요 갯수 조회
	 * @param inData - 게시글번호
	 * @return 해당 게시글의 좋아요 갯수
	 */
	public int selectLikeCnt(PlanLike inData);
	
	/**
	 * 유저의 좋아요 상태 확인
	 * @param pl - 게시글번호, 유저번호
	 * @return 좋아요 상태면 1, 아니면 0
	 */
	public int getLikeStatus(PlanLike pl);
	
	/**
	 * 북마크 테이블에 유저 정보 없으면 데이터 삽입, 좋아요 상태: 1
	 * 북마크 테이블에 유저 정보 있으면 1) 북마크 상태 0 이였으면 1로 변경, 2) 북마크 상태 1 이였으면 0으로 변경
	 * @param inData - 게시글번호, 유저번호
	 * @return 데이터 변경후 유저의 북마크 상태  O -> 1, X -> 0
	 */
	public int modifyBookmark(Bookmark inData);
	
	/**
	 * 유저의 북마크 상태 확인
	 * @param bm - 게시글번호, 유저번호
	 * @return 북마크 상태면 1, 아니면 0
	 */
	public int getBmStatus(Bookmark bm);
	
	/**
	 * 대표이미지를 수정한다( 기존파일명 조회 -> 새로운 이미지파일 저장 -> 이미지파일명 컬럼 수정 -> 기존 이미지파일 삭제 )
	 * @param inData - 게시글번호
	 * @param imgfile - 새로운 이미지파일
	 */
	public void modifyImage(PlanBoard inData, MultipartFile imgfile);
	
	/**
	 * 게시글번호로 일정공유게시글을 조회한다.
	 * @param inData - 게시글번호
	 * @return 게시글번호에 해당하는 게시글 정보
	 */
	public PlanBoard getPlanBoard(PlanBoard inData);
	
	/**
	 * 게시글 번호에 해당하는 게시글을 삭제한다
	 * @param inData - 게시글번호
	 */
	public void remove(PlanBoard inData);
	
	/**
	 * 로그인한 유저가 북마크한 일정공유게시글을 조회한다.
	 * @return 북마크한 일정공유게시글 정보
	 */
	public List<Map<String, Object>> getBookmarkList(int userNo);
}
