/**
 * 
 */
package com.swapnil.learning.interceptor;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author dangoswa
 *
 */
public class LoggingInterceptor implements HandlerInterceptor{

	Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//Check for valid request header
		/*String authCode = request.getHeader("SAMPLE_AUTH_TYPE");
		
		if(authCode==null || !"TEST".equals(authCode)) {
			logger.debug("============= UNAUTHORISED REQUEST==========");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return false;
		}*/
		logger.debug("================= Authorized request=========");
		request.setAttribute("REQUEST_START_TIME", System.currentTimeMillis());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//Calculate request processing time
		long startTime = (Long) request.getAttribute("REQUEST_START_TIME");
		
		//long requestStartTime = Long.parseLong(startTime!=null?startTime:"0");
		logger.debug("Time taken to process = "+(System.currentTimeMillis()-startTime));
	}

}
