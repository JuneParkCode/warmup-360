package org.kernel360.simpleboard.common.config;

import org.kernel360.simpleboard.common.interceptor.LoggerInterceptor;
import org.kernel360.simpleboard.common.interceptor.OpenApiInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
	private final OpenApiInterceptor openApiInterceptor;
	private final LoggerInterceptor loggerInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(openApiInterceptor)
		// 	.addPathPatterns("/api/**");
		registry.addInterceptor(loggerInterceptor)
			.addPathPatterns("/**")
			.order(-1);
	}
}
