package jeju.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;


public class ReviewInterceptor implements HandlerInterceptor{
	
	public static final Logger logger = LoggerFactory.getLogger(ReviewInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.info(" + 인터셉터 + ");
		
		HttpSession session = request.getSession();
		if( null == session.getAttribute("login")) {
			logger.info("접속불가 : 비로그인 상태");
			
			response.sendRedirect("/review/list");
			
			return false;
		}
		
		return true;
	}
}
