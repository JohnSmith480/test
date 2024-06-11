package com.yc;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {
	//重写父类提供的跨域请求处理的接口
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//添加映射路径
		registry.addMapping("/**")
				//放行哪些原始域
				//2.4.3后改为
				.allowedOriginPatterns("*")
				//是否发送Cookie信息
				.allowCredentials(true)
				//放行哪些请求方式
				.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name())
				//放行哪些原始域(头部信息)
				.allowedHeaders(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE, "accessToken", "CorrelationId", "source")
				//暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
				.exposedHeaders(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE, "accessToken", "CorrelationId", "source")
				.maxAge(4800);
		}
	}