package org.kernel360.simpleboard.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class TimerAop {

	// Spring Bean 에 대해서만 적용됨.
	// Spring Bean 이 아닌 경우에는 AspectJ 를 활용할 것.
	@Pointcut(value = "within(org.kernel360.simpleboard.board.controller.BoardApiController)")
	public void timerPointcut() {

	}

	@Before("timerPointcut()")
	public void before() {
		log.info("===== AOP Before =====");
	}

	@After("timerPointcut()")
	public void after() {
		log.info("===== AOP After =====");
	}

	@AfterReturning(value = "timerPointcut()", returning = "returnObj")
	public void afterReturning(JoinPoint joinPoint, Object returnObj) {
		log.info("===== AOP AfterReturning =====");
	}

	@AfterThrowing(value = "timerPointcut()", throwing = "throwable")
	public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
		log.info("===== AOP AfterThrowing =====");
	}

	@Around("timerPointcut()")
	public void around(ProceedingJoinPoint joinPoint) {
		log.info("===== AOP AROUND =====");
		long start = System.currentTimeMillis();
		try {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			long end = System.currentTimeMillis();
			log.info("===== AOP End =====");
			log.info("===== AOP Time: " + (end - start) + "ms =====");
		}
		log.info("===== AOP AROUND =====");
	}

}
