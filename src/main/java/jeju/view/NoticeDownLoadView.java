package jeju.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import jeju.dto.NoticeFile;



public class NoticeDownLoadView extends AbstractView{
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeDownLoadView.class);
	
	//서블릿컨텍스트 객체
	@Autowired ServletContext context;


	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//모델값 가져오기
		NoticeFile noticeFile = (NoticeFile) model.get("NtfdownFile");
		logger.info("모델값 : {}", noticeFile);
		
		//파일의 경로
		String path = context.getRealPath("noticeUpLoad");
		
		//저장된 파일의 이름
		String filename = noticeFile.getNtfStname();
		
		//업로드된 실제 파일에 대한 객체
		File src = new File(path, filename);
		
		//서버에 저장된 파일 입력스트림
		FileInputStream fis = new FileInputStream(src);
		
		//------------------------------------------------------------------
				
		//파일을 전송하는 컨텐트타입으로 설정한다 (이진데이터 형식)
		response.setContentType("application/octet-stream");
		
		//응답 데이터의 크기 설정
		response.setContentLength( (int) src.length() );

		//응답 데이터의 인코딩 설정
		response.setCharacterEncoding("UTF-8");
		
		//클라이언트가 파일을 저장할 때 사용할 이름에 대한 인코딩 설정(UTF-8)
		String outputName = URLEncoder.encode( noticeFile.getNtfOgname(), "UTF-8" );
		logger.info("변환된 파일명 : {}", outputName);
		
		//브라우저가 다운로드에 사용할 이름을 지정하기
		response.setHeader("Content-Disposition"
				, "attachment; filename=\"" + outputName + "\"");

		//서버 응답 출력 스트림
		OutputStream out = response.getOutputStream();

		//서버->클라이언트 파일 복사
		FileCopyUtils.copy(fis, out);
		
	}

}
