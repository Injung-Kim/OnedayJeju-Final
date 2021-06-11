package jeju.dao.face;

import java.util.List;

import jeju.dto.Review;
import jeju.dto.RvComment;

public interface RvCommentDao {

	public List<RvComment> selectComment(Review review);

	public void insertComment(RvComment rvcomment);

	
}
