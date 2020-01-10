package ca.qc.plachanc73.restws.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestProcessingTimeInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestProcessingTimeInterceptor.class);

	private static final String TEXT_REQUEST_URL = "Request URL::";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long startTime = System.currentTimeMillis();
		LOGGER.debug(
				TEXT_REQUEST_URL + request.getRequestURL().toString() + ":: Start Time=" + System.currentTimeMillis());
		request.setAttribute("startTime", startTime);
		// if returned false, we need to make sure 'response' is sent
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOGGER.debug(TEXT_REQUEST_URL + request.getRequestURL().toString() + " Sent to Handler :: Current Time="
				+ System.currentTimeMillis());
		// we can add attributes in the modelAndView and use that in the view page
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		LOGGER.debug(
				TEXT_REQUEST_URL + request.getRequestURL().toString() + ":: End Time=" + System.currentTimeMillis());
		LOGGER.debug(TEXT_REQUEST_URL + request.getRequestURL().toString() + ":: Time Taken="
				+ (System.currentTimeMillis() - startTime));
	}
}