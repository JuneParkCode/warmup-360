package org.kernel360.simpleboard.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OpenApiInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		Exception {
		log.info(">>> OpenApiInterceptor");

		var handlerMethod = (HandlerMethod)handler;
		var methodLevel = handlerMethod.getMethodAnnotation(OpenApi.class);
		if (methodLevel != null) {
			log.info("OpenApi Method: {}", handlerMethod.getMethod().getName());
			return true;
		}
		var classLevel = handlerMethod.getBeanType().getAnnotation(OpenApi.class);
		if (classLevel != null) {
			log.info("OpenApi Class: {}", handlerMethod.getBeanType().getName());
			return true;
		}
		log.info("Not OpenAPI Request");
		return false;
		// controller 로 내용을 보내지 않음.
		// NOTE: Controller 에서 buffer 를 읽지 않기에 Filter 측에서도 기록을 하지 않는 것을 볼 수 있음.
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
		log.info("<<< OpenApiInterceptor");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
		Exception ex) throws Exception {
		log.info(">>> After Completion");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
