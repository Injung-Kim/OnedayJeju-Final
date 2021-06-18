package jeju.dao.face;

import java.util.List;

import jeju.dto.Review;
import jeju.dto.RvLike;
import jeju.util.Paging;
import jeju.util.RvPaging;

public interface ReviewDao {
	
	/**
	 * 전체 게시글 수 조회
	 * 
	 * @return 총 게시글 수
	 */
	public int selectCntAll(RvPaging inData);
	
	
	/**
	 * 페이징을 적용하여 게시글 목록 조회
	 * 
	 * @param paging - 페이징 정보객체
	 * @return - 페이징이 적용된 게시글 목록
	 */
	public List<Review> selectPageList(RvPaging paging);

	/**
	 * 게시글 조회수 1 증가
	 * 
	 * @param viewReview - 게시글 번호 가진 객체
	 */
	public void updateHit(Review viewReview);
	
	
	/**
	 * 게시글 번호를 이용하여 여행후기 게시글 조회
	 * 
	 * @param viewReview - 게시글 번호를 가진 객체
	 * @return 조회된 게시글
	 */
	public Review selectReviewByrvNo(Review viewReview);

	/**
	 * 입력한 게시글 여행후기 삽입
	 * 
	 * @param review - 게시글 객체
	 */

	public void insert(Review review);


	/**
	 * 여행후기 게시글 수정
	 * 
	 * @param review - 게시글 객체
	 */
	public void update(Review review);

	/**
	 * 여행후기 게시글 삭제
	 * 
	 * @param review - 게시글 객체
	 */
	public void delete(Review review);

	
	/**
	 * 사용자가 해당 게시글을 추천한 적 있는지 조회
	 * 
	 * @param rvlike - 사용자와 게시글 정보 개체
	 * @return 추천정보 확인
	 */
	public int selectCntRecommend(RvLike rvlike);


	/**
	 * 추천 상태 지우기
	 * 
	 * @param rvlike - 추천 정보 객체
	 */
	public void deleteRecommend(RvLike rvlike);


	/**(
	 * 추천 상태 추가
	 * 
	 * @param rvlike - 추천 객체
	 */
	public void insertRecommend(RvLike rvlike);


	/**
	 * 게시글 전체 추천 수 조회
	 * 
	 * @param rvlike - 추천수 조회할 게시글 정보
	 * @return 전체 추천수
	 */
	public int selectTotalCntRecommend(RvLike rvlike);


	

}
