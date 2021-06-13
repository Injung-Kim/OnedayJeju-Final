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
		int totalCount = adminUserDao.selectCountAll(); //총 회원 수 조회
		Paging paging = new Paging(totalCount, inData.getCurPage()); //페이징 계산
		return paging;
	}

	@Override
	public List<JejuUser> getList(Paging paging) {
		return adminUserDao.selectPageList(paging); //사용자 목록 조회
	}

	@Override
	public JejuUser getInfo(int userNo) {
		return adminUserDao.selectInfoByUserNo(userNo); //사용자번호로 사용자정보 조회
	}

	@Override
	@Transactional
	public void delete(int userNo) {
		adminUserDao.delete(userNo); //사용자계정 삭제
	}

}
