package jeju.view;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.AbstractView;


public class GetImageView extends AbstractView {
	
	private static final Logger logger = LoggerFactory.getLogger(GetImageView.class);
	
	@Autowired ServletContext context;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("응답코드 작성 영역");
		logger.info(model.toString());
		
		//저장된 파일이름
		String filename = (String) model.get("filename");
		
		//확장자 얻어오기
		int dotIdx = filename.lastIndexOf("."); //가장 마지막 "."의 인덱스 찾기
	
		//확장자
		String ext = filename.substring(dotIdx+1);
//		logger.debug("[test]ext : {}", ext);
//		logger.debug("[test]filename : {}", filename);
		
		response.setContentType("image/" + ext);

		byte[] by = new byte[1024]; //한번에 읽어올 파일크기 1024 바이트

		//출력을위한 OutputStream 객체
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream in = null;
		try {
			//이미지 주소 저장
			String imagePath = getServletContext().getRealPath("upload") + "/"+ filename;
			
//			logger.debug("[test] imagePath : {}", imagePath);

			//inputStream : 파일을 1바이트씩 읽어옴

			//BufferedInputStream : inputStream객체로 버퍼객체를 생성

			in = new BufferedInputStream(new FileInputStream(imagePath));

			//버퍼(in)에 있는 데이터를 1024바이트(by) 만큼 읽어오고 데이터가 없을경우 반복문 종료
			int len = 0;
			while((len = in.read(by)) > 0) {
				out.write(by, 0, len); //1024바이트씩 출력
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
			out.close();
		}
		
	}
}
