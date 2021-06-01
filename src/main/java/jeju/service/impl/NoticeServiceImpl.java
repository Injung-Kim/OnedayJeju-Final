package jeju.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jeju.dao.face.NoticeDao;
import jeju.dto.Notice;
import jeju.service.face.NoticeService;
import jeju.util.Paging;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired NoticeDao noticeDao;
	
	@Override
	public Paging getPaging(Paging inData) {
		
		//총 게시글 수 조회
		int totalCount = noticeDao.selectCntAll();
		
		//페이징 계산
		Paging paging = new Paging( totalCount, inData.getCurPage() );
		
		return paging;
	}

	@Override
	public List<Notice> getNoticeList(Paging paging) {
		return noticeDao.noticePageSelect(paging);
	}

	@Override
	public Notice viewNotice(Notice nt) {
		
		noticeDao.updateHit(nt);
		
		return noticeDao.selectNoticeByNotice(nt);
	}

}
