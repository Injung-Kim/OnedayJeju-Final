package jeju.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jeju.dao.face.JoinDao;
import jeju.service.face.JoinService;

@Service
public class JoinServiceImpl implements JoinService {

	private static final Logger logger = LoggerFactory.getLogger(JoinServiceImpl.class);
	
	@Autowired JoinDao joinDao; 
	
}
