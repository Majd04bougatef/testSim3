package tn.esprit.sim3.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    private static final String POINTCUT = "execution(* tn.esprit.sim3.services.*.*(..))";

    @Before(POINTCUT)
    public void beforeAdvice(JoinPoint joinPoint) {
        logger.info("[AOP][BEFORE] Entering {} with args={}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @After(POINTCUT)
    public void afterAdvice(JoinPoint joinPoint) {
        logger.info("[AOP][AFTER] Exiting {}", joinPoint.getSignature());
    }

    @AfterReturning(pointcut = POINTCUT, returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        logger.info("[AOP][AFTER_RETURNING] {} returned={}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(pointcut = POINTCUT, throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable ex) {
        logger.error("[AOP][AFTER_THROWING] {} threw {}", joinPoint.getSignature(), ex.toString());
    }

    @Around(POINTCUT)
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long elapsed = System.currentTimeMillis() - start;
            logger.info("[AOP][AROUND] {} executed in {} ms", joinPoint.getSignature(), elapsed);
            return result;
        } catch (Throwable t) {
            long elapsed = System.currentTimeMillis() - start;
            logger.info("[AOP][AROUND] {} threw {} after {} ms", joinPoint.getSignature(), t.toString(), elapsed);
            throw t;
        }
    }
}
