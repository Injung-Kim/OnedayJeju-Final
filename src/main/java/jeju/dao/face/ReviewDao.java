package jeju.dao.face;

import java.util.List;

import jeju.dto.Review;
import jeju.util.Paging;

public interface ReviewDao {
	
	/**
	 * 전체 게시글 수 조회
	 * 
	 * @return 총 게시글 수
	 */
	public int selectCntAll(Paging inData);
	
	
	/**
	 * 페이징을 적용하여 게시글 목록 조회
	 * 
	 * @param paging - 페이징 정보객체
	 * @return - 페이징이 적용된 게시글 목록
	 */
	public List<Review> selectPageList(Paging paging);

	/**
	 * 게시글 조회수 1 증가
	 * 
	 * @param viewReview - 게시글 번호 가진 객체
	 */
	public void updateHit(Review viewReview);
	
	public Review selectReviewByrvNo(Review viewReview);


	public void insert(Review review);

}
