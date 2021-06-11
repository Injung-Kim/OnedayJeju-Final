package jeju.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jeju.dao.face.QnADao;
import jeju.dto.qna.FileTB;
import jeju.dto.qna.Hashtag;
import jeju.dto.qna.Question_original;
import jeju.service.face.QnAService;
import jeju.util.Paging;
@Service
public class QnAServiceImpl implements QnAService{
	Logger logger = LoggerFactory.getLogger(QnAServiceImpl.class);
	@Autowired QnADao qnaDao;
	@Autowired ServletContext context;
	//질문글 게시글 수 구하기
	@Override
	public int getCntQuestions(String word) {
		String tagName = "";
		if(word != null && !"".equals(word)) {
			tagName = word;
		}
		
		int num = qnaDao.selectCntQustion(tagName);
		logger.info("조회한 갯수 : {}", num);
		return num;
	}
	//페이징 생성하기
	@Override
	public Paging getPaging(Paging paging) {
		paging.setListCount(5);
		Paging listPaging = new Paging(paging.getTotalCount(), paging.getCurPage(), paging.getListCount());
		return listPaging;
	}
	//전체 질문글 리스트 불러오기
	@Override
	public List<HashMap<String, Object>> getQuestionList(Paging paging, String word) {
		
		HashMap<String, Object> param = new HashMap<>();
//		param.put("tag_name", "내맘");
		param.put("tag_name", word);
		param.put("paging", paging);
		
		List<HashMap<String, Object>> result = qnaDao.selectQ(param);
		logger.info("result {}", result);
		
		return result;
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("paging", paging);
	
//		String tagName = "";
//		if(word != null && !"".equals(word)) {
//			tagName = word;
//			map.put("tagName", tagName);
//			map.put("tagName", word);
//		}
		
		
//		return qnaDao.selectQuestions(map);
	}
	
	//질문글 상세정보 전달받기
	@Override
	public HashMap<String, Object> getQuestion(int qstNo) {
		//조회수 증가
		qnaDao.updateCntQuestion(qstNo);
		return qnaDao.selectQuestionByqstNo(qstNo);
	}
	//질문글 해시태그 정보
	@Override
	public List<HashMap<String, Object>> getHashtags(int qstNo) {
		return qnaDao.selectHashTagByqstNo(qstNo);
	}
	//질문글 첨부파일 정보
	@Override
	public List<HashMap<String, Object>> getFiles(int qstNo) {
		List<HashMap<String, Object>> files = qnaDao.selectFiles(qstNo);
		return files;
	}
	// 답변글 갯수 조회하기
	@Override
	public int getCntAnswers(int qstNo) {
		
		return qnaDao.selectCntAnswer(qstNo);
	}
	//답변글 리스트 불러오기
	@Override
	public List<HashMap<String, Object>> getAnswers(Map<String, Object> ansParam) {
		return qnaDao.selectAnswers(ansParam);
	}
	//해시태그 문자열 존재여부 검사
	@Override
	public boolean isExist(String tagName) {
		//문자열과 일치하는 태그 갯수 조회
		int num = qnaDao.cntTags(tagName);
		logger.info("일치하는 태그 수 : {} ", num);
		if(num == 0) { //일치하는 태그가 없을 경우
			logger.info("태그 생성 성공");
			return false;
		}else {
			logger.info("태그명 DB존재");
			return true;
		}		
	}
	//태그 생성하기
	@Override
	public void addTagName(String tagName) {
		qnaDao.insertTag(tagName);
	}
	//생성된 태그 조회하기
	@Override
	public Hashtag getTagInfo(String tagName) {
		return qnaDao.selectTag(tagName);
	}
	
	//파일 처리하기
	@Override
	public List<FileTB> createFile(List<MultipartFile> file) {
		logger.info("파일 여부 검사 :{}", file.get(0).getSize());
		//파일이 있을 경우
		if(file.get(0).getSize() != 0) {
			//저장 경로 설정
			String storedPath = context.getRealPath("/qna");
			//저장 폴더 생성하기
			File created = new File(storedPath);
			if(!created.exists()) {
				created.mkdir();
			}
			//결과를 저장할 리스트 생성
			List<FileTB> list = new ArrayList<>();
			
			for(int i = 0; i<file.size(); i++) {
			//파일 저장시 생성할 랜덤 번호
			String UID = UUID.randomUUID().toString().split("-")[4];
				
			//원본이름
			String origin = file.get(i).getOriginalFilename();
			logger.info("원본이름 : {}", origin );
			//저장될 이름
			String name = FilenameUtils.getBaseName(origin); //파일명
			name += UID;
			String stored = name + "." + FilenameUtils.getExtension(origin); //파일명 + 확장자
			
			logger.info("저장된 이름 : {}", stored );
			
			//폴더에 저장될 이름의 파일 생성하기
			File upload = new File(created, stored);
			//파일 저장하기
			try {
				file.get(i).transferTo(upload);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//파일명 dto 저장하기
			FileTB table = new FileTB();
			table.setOrigin(origin);
			table.setStored(stored);
			
			list.add(table);
			}
			//파일명들 전달하기
			return list;
		}
		//파일이 없을 경우
		logger.info("파일 없음");
		return null;
	}
	//질문글 추가하기
	@Override
	public void createQuestion(Question_original question, List<FileTB> filetable, int[] tagNo) {
		//작성한 질문글 DB 저장
		qnaDao.insertQuestion(question);
		
		//첨부파일 DB저장
		if(filetable != null) {
			for(FileTB file : filetable) {
				file.setQstNo(question.getQstNo());
				qnaDao.insertFiles(file);
				logger.debug("첨부파일 정보 : {}",file);
			}
		}
		
		//태그번호 DB저장
		for(int tagNum : tagNo) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("qstNo", question.getQstNo());
			map.put("tagNo", tagNum);
			qnaDao.insertTagIntoQustion(map);
		}
	}
	//질문글에 등록된 파일 삭제
	@Override
	public void removeFiles(int qstNo) {
		//질문글에 등록된 파일 정보 가져오기
		List<HashMap<String, Object>> files = qnaDao.selectFiles(qstNo);
		//파일이 저장된 경로 가져오기
		String storedPath = context.getRealPath("/qna");
		File file;
		for(HashMap<String, Object> stored : files) {
			logger.info("등록된 파일이름  : {}", stored.get("QST_STORED"));
			// 등록된 이름으로 파일 생성
			file = new File(storedPath+"/"+stored.get("QST_STORED"));
			
			// 수정 전 파일이 경로에 존재할 경우 파일 삭제
			if(file.exists()) {
				logger.info("저장된 파일 존재 : {}", stored.get("QST_STORED"));
				file.delete();
			}else {
				logger.info("파일 없음 ");
			}
		}
	}
	@Override
	public void deleteQuestion(int qstNo) {
		//질문글 전체 삭제하기
		qnaDao.deleteQuestion(qstNo);
	}
	@Override
	public void updateQuestion(Question_original question, List<FileTB> filetable, int[] tagNo) {
		//작성한 질문글 DB 저장
		qnaDao.updateQuestion(question);
		//변경할 파일이 있는 경우
		if(filetable != null) {
			//기존의 첨부파일 목록 삭제
			qnaDao.deleteFiles(question.getQstNo());
			
			//첨부파일 DB저장
			for(FileTB file : filetable) {
				file.setQstNo(question.getQstNo());
				qnaDao.insertFiles(file);
				logger.debug("첨부파일 정보 : {}",file);
			}
		}
		
		if(tagNo != null) {
			//태그번호 삭제
			qnaDao.deleteTags(question.getQstNo());
		
			//태그번호 DB저장
			for(int tagNum : tagNo) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("qstNo", question.getQstNo());
				map.put("tagNo", tagNum);
				qnaDao.insertTagIntoQustion(map);
			}
		}
	}
}
