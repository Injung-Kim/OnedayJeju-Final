package jeju.dao.face;

import java.util.List;

import jeju.dto.Review;
import jeju.dto.RvComment;

public interface RvCommentDao {

	/**
	 * 게시글 번호를 이용한 게시글 댓글 전체 객체
	 * 
	 * @param review - 게시글 번호를 이용한 객체
	 * @return 게시글 댓글 리스트 반환
	 */
	public List<RvComment> selectComment(Review review);

	/**
	 * 게시글 댓글 추가
	 * 
	 * @param rvcomment - 게시글 댓글번호 담긴 객체
	 */
	public void insert(RvComment rvcomment);


	/**
	 * 게시글 댓글 수정
	 * 
	 * @param rvcomment - 게시글 댓글 번호 객체
	 */
	public void commentUpdateCommit(RvComment rvcomment);

	/**
	 * 게시글 댓글 삭제
	 * 
	 * @param rvcomment - 게시글 댓글 번호 객체
	 */
	public void commentCancel(RvComment rvcomment);

	
}
