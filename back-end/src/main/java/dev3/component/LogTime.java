package dev3.component;

import static java.util.concurrent.CompletableFuture.runAsync;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogTime {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("mm:ss.SSS");

    @Around("execution(* br.com.crescer.social.*.*.*(..))")
    public Object around(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final long currentTimeMillis = System.currentTimeMillis();
        final Object object = proceedingJoinPoint.proceed();
        final long time = System.currentTimeMillis() - currentTimeMillis;
        runAsync(() -> Optional.ofNullable(proceedingJoinPoint.getSignature()).map(MethodSignature.class::cast)
                .filter(methodSignature -> methodSignature.getMethod() != null).ifPresent(methodSignature -> {
                    final String format = String.format("%s.%s: %s", methodSignature.getDeclaringTypeName(),
                            methodSignature.getMethod().getName(), FORMAT.format(new Date(time)));
                    Logger.getLogger(LogTime.class.getName()).info(format);
                }));
        return object;
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }
    
    
}
