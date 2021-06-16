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
		
		//총 사용자 수 조회
		int totalCount = adminUserDao.selectCountAll(inData);
		
		//페이징 계산
		Paging paging = new Paging(totalCount, inData.getCurPage());
		
		//페이징 결과값 리턴
		return paging;
	}

	@Override
	public List<JejuUser> getList(Paging paging) {
		//페이징 적용된 목록 조회 결과값 리턴
		return adminUserDao.selectPageList(paging);
	}

	@Override
	public JejuUser getInfo(JejuUser userInfo) {
		//정보 조회 결과값 리턴
		return adminUserDao.selectInfo(userInfo);
	}

	@Override
	@Transactional
	public void modify(JejuUser user) {
		//정보 수정
		adminUserDao.update(user);
	}
	
	@Override
	@Transactional
	public void delete(JejuUser user) {
		//계정 삭제
		adminUserDao.delete(user);
	}

	@Override
	public int checkId(String userId) {
		//아이디 중복 조회
		return adminUserDao.selectCountId(userId);
	}

	@Override
	public int checkNick(String userNick) {
		//닉네임 중복 조회
		return adminUserDao.selectCountNick(userNick);
	}
	
	@Override
	public void createAdmin(JejuUser user) {
		//계정 생성
		adminUserDao.insert(user);
	}

}
