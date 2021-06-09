package jeju.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;


public class PlanInterceptor implements HandlerInterceptor {
	//로깅 객체
	private static final Logger logger = LoggerFactory.getLogger(PlanInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.debug("+ + + Plan interceptor + + +");
		
		HttpSession session = request.getSession();
		
		if( session.getAttribute("login") == null ) {
			logger.debug("비 로그인 상태 - 접근 불가");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>");
			writer.println("alert('로그인 후 이용해주세요')");
			writer.println("location.href='/'");
			writer.println("</script>");
			writer.close();
			return false;
		}
		
		return true; // 컨트롤러에 요청 보내줌
	}
	
	
}
