package com.framework.helix.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

/**
 * Created by nuwan.n.panane on 12/10/2014.
 */
@Aspect
public class ClientServiceLoggingAspect {
    private Log log = LogFactory.getLog(this.getClass());

    /*@Before("execution(* ArithmeticCalculator.add(..))")
    public void logBefore() {
        log.info("The method getClients() begins");
    }*/

    /*
    @Before("execution(* ClientService.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("The method " + joinPoint.getSignature().getName()
                + "() begins with " + Arrays.toString(joinPoint.getArgs()));
    }
    */

    /*@AfterReturning("execution(* ClientService.*(..))")
    public void logAfterReturning(JoinPoint joinPoint) {
        log.info("The method " + joinPoint.getSignature().getName()
                + "() ends");
    }*/
}
