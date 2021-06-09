package jeju.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jeju.dao.face.ExpensesDao;
import jeju.dto.Expenses;
import jeju.dto.Plan;
import jeju.service.face.ExpensesService;

@Service
public class ExpensesServiceImpl implements ExpensesService {
	
	@Autowired private ExpensesDao expensesDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ExpensesServiceImpl.class);

	
	@Override
	public List<Plan> getPlanList() {
		return expensesDao.selectPlanList();
	}


	@Override
	public List<HashMap<String, Object>> getExpList(int pNo) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pNo", pNo);
		
		List<HashMap<String, Object>> eList = expensesDao.selectExpByPno(map);
		
		return eList;
	}


	@Override
	public List<HashMap<String, Object>> getPlanData(Plan pNo) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pNo", pNo.getpNo());
		
		List<HashMap<String, Object>> pData = expensesDao.selectPlanData(map);
		
		return pData;
	}


	@Override
	public void addExp(Expenses add) {
		
		if ( "".equals(add.getMemo()) ) {
			add.setMemo("( 내 용 없 음 )");
		}
		
		expensesDao.insertExp(add);
	}

	
	@Override
	public Expenses viewExp(int eno) {
		return expensesDao.viewExp(eno);
	}
	

	@Override
	public void delExp(int expno) {
		expensesDao.deleteExp(expno);
	}


	@Override
	public List<HashMap<String, Object>> getExpStat(int pNo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pNo", pNo);
		
		List<HashMap<String, Object>> data = expensesDao.selectAllExp(map);
		
		return data;
	}


	


	

	

}
