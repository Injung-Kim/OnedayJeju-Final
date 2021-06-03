package jeju.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jeju.dao.face.DetailPlanDao;
import jeju.dto.DetailPlan;
import jeju.dto.Dppm;
import jeju.dto.Plan;
import jeju.service.face.DetailPlanService;

@Service
public class DetailPlanServiceImpl implements DetailPlanService {
	
	private static final Logger logger = LoggerFactory.getLogger(DetailPlanServiceImpl.class);
	
	@Autowired private DetailPlanDao detailPlanDao;
	
	@Override
	public List<DetailPlan> getDpListByPno(Plan inData) {
		logger.debug("getDpListByPno() 호출");
		return detailPlanDao.selectDpListByPno(inData);
	}
	
	@Override
	public List<Map<String, Object>> getDpInfoListByDpno(DetailPlan dpno) {
		logger.debug("getDpListByDpno() 호출");
		return detailPlanDao.selectDpInfoListByDpno(dpno);
	}
	
	@Override
	@Transactional
	public void modify(List<Dppm> inData) {
		logger.debug("modify() 호출");
		
		//기존 데이터 삭제
		detailPlanDao.deleteAllByDpno(inData.get(0));
		//상세일정을 전부 삭제한 경우
		if(inData.get(0).getContentid() == null) return;
		//새로운 데이터 삽입
		for(Dppm newData : inData) {
			detailPlanDao.insertDppm(newData);
		}
	}
}
