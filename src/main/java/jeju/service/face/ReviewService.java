package jeju.service.face;

import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import jeju.dto.Review;
import jeju.dto.RvComment;
import jeju.dto.RvLike;
import jeju.util.Paging;
import jeju.util.RvPaging;

public interface ReviewService {
	

	/**
	 * 게시글 페이징 객체 생성
	 * 
	 * @param inData - curPage(현재 페이지)를 저장하고 있는 Paging객체
	 * @return 페이징 계산이 완료된 Paging객체
	 */
	public RvPaging getPaging(RvPaging inData);
	
	
	/**
	 * 페이징이 적용된 게시 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return 페이징이 적용된 게시글 목록
	 */
	public List<Review> list(RvPaging paging);

	/**
	 * 게시글 번호를 이용한 게시글 상세보기
	 * 
	 * @param viewReview 게시글 번호를 가진 게시글 객체
	 * @return 조회된 게시글 상세보기 객체
	 */
	public Review view(Review viewReview);

	/**
	 * 게시글 객체와 파일처리
	 * 
	 * @param review - 게시글 객체
	 * @param file - 첨부 파일 객체
	 */
	public void write(Review review, MultipartFile file);

	/**
	 * 여행후기 객체와 첨부파일 수정
	 * 
	 * @param review - 게시글 번호가 담긴 객체
	 * @param file - 첨부파일 번호
	 * @return 게시글 수정
	 */
	public void update(Review review, MultipartFile file);
	
	/**
	 * 게시글 번호로 이용하여 게시글 삭제
	 * 
	 * @param review - 게시글 번호가 담긴 객체
	 * @return 게시글 삭제
	 */
	public void delete(Review review);
	
	/**
	 * 여행후기 게시글 댓글 전체 조회
	 * 
	 * @param viewReview - 게시글  객체
	 * @return 게시글 리스트
	 */
	public List getCommentList(Review viewReview);
	
	/**
	 * 여행 후기 게시글 댓글 입력
	 * 
	 * @param rvcomment - 댓글 정보 입력 객체
	 * 
	 */
	public void insert(RvComment rvcomment);

	/**
	 * 여행 후기 게시글 댓글 수정
	 * 
	 * @param rvcomment - 댓글 정보 내용 객체
	 * 
	 */
	public void commentUpdateCommit(RvComment rvcomment);

	/**
	 * 댓글 삭제
	 * 
	 * @param rvcomment - 댓글 정보 내용 객체
	 */
	public void commentCancel(RvComment rvcomment);


	/**
	 * 추천 상태 확인
	 * 
	 * @param rvlike - 추천 상태 확인 게시글 정보
	 * @return true - 추천 상태 확인
	 */
	public boolean isRvLike(RvLike rvlike);
	
	/**
	 * 추천 상태 확인 후 추천 작업
	 * 
	 * @param rvlike - 추천 대상 정보
	 * @return true - 추천 정보
	 */
	public boolean recommend(RvLike rvlike);


	/**
	 * 총 추천수 구하기
	 * 
	 * @param rvlike - 추천수 게시글 정보
	 * @return 총 추천 수
	 */
	public int getTotalCntRecommend(RvLike rvlike);





}
