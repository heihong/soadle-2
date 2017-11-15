package fr.soat.soadle.aop.services.impl;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import fr.soat.soadle.aop.services.AopTraceService;



/**
 * @author abenchabana
 *
 */
@Aspect
@Service
public class AopTraceServiceImpl implements AopTraceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AopTraceServiceImpl.class);

	/**
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("invocationPointcut()")
	public Object intercept(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
		
		LOGGER.info(" ** start " + method.getName() + " **");
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();		

		Object result = proceedingJoinPoint.proceed();

		stopWatch.stop();
		LOGGER.info(" ** end " + proceedingJoinPoint.getThis().getClass().getName()+"."+method.getName() + " : " + stopWatch.getTotalTimeMillis() + " millis **");

		return result;
	}

	/**
	 * 
	 */
	@Pointcut("execution(* fr.soat.soadle.services.*..*(..))||execution(* fr.soat.soadle.doodle.services.*..*(..))||execution(* fr.soat.soadle.web.rest.*..*(..))")
	public void invocationPointcut() {

	}

}
