package jeju.service.impl;

import java.util.Calendar;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jeju.dao.face.PlanDao;
import jeju.dto.Plan;
import jeju.service.face.PlanService;

@Service
public class PlanServiceImpl implements PlanService {
	
	private static final Logger logger = LoggerFactory.getLogger(PlanServiceImpl.class);

	@Autowired private PlanDao planDao;
	
	@Override
	@Transactional
	public Plan create(Plan inData) {
		 logger.info("create() 호출");
		 
		 //일정 삽입
		 planDao.insert(inData);
		 logger.debug("데이터 삽입 후 inData : {}", inData);
		 
		 //일정 정보에서 여행 일수 만큼 상세일정 만들기(삽입)
		 logger.debug("출발일 : {}", inData.getpDepartureDate());
		 
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(inData.getpDepartureDate());
		 int pNo = inData.getpNo();
		 HashMap<String, Object> map = null;
		 
		 //상세일정 생성 [여행일수 만큼]
		 for(int i=0; i<inData.getpNumDays(); i++) {
			 map = new HashMap<String, Object>();
			 cal.add(Calendar.DATE, 1);
			 map.put("pNo", pNo);
			 map.put("dpDate", cal.getTime());
			 
			 planDao.insertDetail(map);
		 }
		 
		 return inData;
	}
	
	@Override
	public Plan getPlan(Plan inData) {
		logger.info("getPlan() 호출");
		
		return planDao.selectPlanByPno(inData);
	}
	
	@Override
	public void remove(Plan inData) {
		logger.info("remove() 호출");
		//일정 삭제
		planDao.deletePlanByPno(inData);
	}
}
