package jeju.editor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileUploadController {
   private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

   @RequestMapping(value="/file_uploader_html5")
   public void file_uploader_html5(HttpServletRequest request, HttpServletResponse response) {
      logger.info("업로드 컨트롤러 진입12");
      try {
         
      String sFileInfo = "";
      //원본 파일명 
      String filename = request.getHeader("file-name");
      //파일 확장자  
      String filename_ext = filename.substring(filename.lastIndexOf(".")+1);
      //확장자를 소문자로 변경
      filename_ext = filename_ext.toLowerCase();
         
      //이미지 검증 배열  
      String[] allow_file = {"jpg","png","bmp","gif"};

      //확장자가 이미지 인지 확인
      int cnt = 0;
      for(int i=0; i<allow_file.length; i++) {
         if(filename_ext.equals(allow_file[i])){
            cnt++;
         }
      }

      //이미지가 아닐경우  
      if(cnt == 0) {
         PrintWriter print = response.getWriter();
         print.print("NOTALLOW_"+filename);
         print.flush();
         print.close();
      } else {
      //신규 디렉토리 설정과 업로드   
      //파일 기본경로 
      String dftFilePath = request.getSession().getServletContext().getRealPath("/");
      //임시 파일 경로 
      String filePath = dftFilePath + "emp" + File.separator + "editor" + File.separator;
      File file = new File(filePath);
      if(!file.exists()) {
         file.mkdirs();
      }
      
      //저장될 파일명 설정
      String realFileNm = "";
      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
      String today= formatter.format(new java.util.Date());
      realFileNm = today+UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
      String rlFileNm = filePath + realFileNm;
      
      //임시파일 저장(글작성할동안만 저장되고 등록시 삭제후 upload파일에 저장되도록 하기)
      InputStream is = request.getInputStream();
      
      OutputStream os=new FileOutputStream(rlFileNm);
      int numRead;
      byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
      while((numRead = is.read(b,0,b.length)) != -1){
         os.write(b,0,numRead);
      }
      if(is != null) {
         is.close();
      }
      os.flush();
      os.close();

      
      //에디터에 이미지 출력
      sFileInfo += "&bNewLine=true";
      
      //img태그의 title속성에 원본파일명 적용
      sFileInfo += "&sFileName="+ filename;
      sFileInfo += "&sFileURL="+"/emp/editor/"+realFileNm;
      PrintWriter print = response.getWriter();
      logger.info(sFileInfo);
      print.print(sFileInfo);
      print.flush();
      print.close();
      }
      
   } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
   }
      
      
   }
}