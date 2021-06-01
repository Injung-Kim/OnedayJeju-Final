package jeju.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AdminInterceptor implements HandlerInterceptor {
	
	public static final Logger logger = LoggerFactory.getLogger(AdminInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.info(" + + + 관리자 페이지 인터셉터 시작 + + + ");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("login") == null ) { //비로그인 상태
			logger.info(" >> 접속 불가: 비 로그인 상태");
			
			response.sendRedirect("/admin/fail");
			
			return false;
		
		} else { //로그인 상태
			logger.info(" >> 로그인 상태");
			
			if ("2".equals(session.getAttribute("grade")) ) {
				logger.info(" >> 접속 불가: 일반 사용자 로그인");
				
				response.sendRedirect("/admin/fail");
				
				return false;
			}
			
		}
		logger.info(" >> 정상 접근: 관리자 로그인");
		
		return true;
	}
	
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		logger.info(" + + + 관리자 페이지 인터셉터 종료 + + + ");
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
}
