package jeju.service.face;

import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import jeju.dto.Review;
import jeju.dto.RvComment;
import jeju.util.Paging;
import jeju.util.RvPaging;

public interface ReviewService {

	public RvPaging getPaging(RvPaging inData);

	public List<Review> list(RvPaging paging);

	public Review view(Review viewReview);

	public void write(Review review, MultipartFile file);

	public List getCommentList(Review viewReview);
	
	public void insert(RvComment rvcomment);


	public void commentUpdateCommit(RvComment rvcomment);

	public void commentCancel(RvComment rvcomment);

	public void update(Review review, MultipartFile file);

	public void delete(Review review);



}
