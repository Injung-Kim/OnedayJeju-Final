package jeju.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class NoticeInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(QnAInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//로그인 시에만 접근 허용
		HttpSession session = request.getSession();
		if(session.getAttribute("login") == null) {
			logger.info("비 로그인중 -  notice 진입불가능");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>");
			out.println("alert('로그인이 필요합니다')");
			out.println("location.href='/member/login'");
			out.println("</script>");
			out.close();
			return false;
		}
		logger.info("로그인중 - notice 진입허용");
		return true;
	}
}
