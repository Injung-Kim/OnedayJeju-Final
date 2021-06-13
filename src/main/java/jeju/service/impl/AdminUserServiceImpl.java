package jeju.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jeju.dao.face.AdminUserDao;
import jeju.dto.JejuUser;
import jeju.service.face.AdminUserService;
import jeju.util.Paging;

@Service
public class AdminUserServiceImpl implements AdminUserService {
	
	@Autowired private AdminUserDao adminUserDao;

	@Override
	public Paging getPaging(Paging inData) {
		int totalCount = adminUserDao.selectCountAll(); //총 사용자 수 조회
		Paging paging = new Paging(totalCount, inData.getCurPage()); //페이징 계산
		return paging;
	}

	@Override
	public List<JejuUser> getList(Paging paging) {
		return adminUserDao.selectPageList(paging); //페이징 적용된 사용자목록 조회
	}

	@Override
	public JejuUser getInfo(JejuUser userInfo) {
		return adminUserDao.selectInfo(userInfo); //사용자정보 조회
	}

	@Override
	@Transactional
	public void modify(JejuUser user) {
		adminUserDao.update(user); //사용자정보 수정
	}
	
	@Override
	@Transactional
	public void delete(JejuUser user) {
		adminUserDao.delete(user); //사용자계정 삭제
	}

}
