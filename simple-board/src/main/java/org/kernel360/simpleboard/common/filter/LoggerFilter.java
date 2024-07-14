package org.kernel360.simpleboard.common.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoggerFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
		IOException,
		ServletException {
		log.info(">>> Logging Filter ");

		// stream 을 사용하기 때문에 미리 읽어내면 이후에 사용할 수 없어 ContentCachingWrapper 사용.
		// Buffer 에 별도로 기록해둠.
		var req = new ContentCachingRequestWrapper((HttpServletRequest)servletRequest);
		var res = new ContentCachingResponseWrapper((HttpServletResponse)servletResponse);

		filterChain.doFilter(req, res); // servlet req / res 를 교체함.

		var reqJson = new String(req.getContentAsByteArray());
		log.info("Request Body: {}", reqJson);

		var resJson = new String(res.getContentAsByteArray());
		log.info("Response Body: {}", resJson);
		// 기존 로직 종료 후 Request / Response 기록.

		res.copyBodyToResponse(); // 앞에서 읽었으니까, 마지막에 전달하기 전에 body 에 다시 기록할 수 있어야함.
		log.info("<<< Logging Filter ");
	}
}
