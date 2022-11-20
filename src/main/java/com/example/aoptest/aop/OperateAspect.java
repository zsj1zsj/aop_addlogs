package com.example.aoptest.aop;

import com.alibaba.fastjson2.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Aspect
public class OperateAspect {

    @Pointcut("@annotation(com.example.aoptest.aop.RecordOperate)")
    public void pointcut() {
    }

    private ExecutorService executorService = Executors.newFixedThreadPool(1);


    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();

        executorService.execute(() -> {
            try {
                MethodSignature signature = (MethodSignature) pjp.getSignature();
                RecordOperate annotation = signature.getMethod().getAnnotation(RecordOperate.class);

                // 取到Convert的实现方法
                Class<? extends Convert> convert = annotation.convert();
                Convert logConvert = convert.newInstance();

                // 返回一个带id的OperateLogDo对象
                OperateLogDo operateLogDo = logConvert.convert(pjp.getArgs()[0]);
                operateLogDo.setDesc(annotation.desc());
                operateLogDo.setResult(result.toString());

                System.out.println("insert operating" + JSON.toJSONString(operateLogDo));

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        });
        return result;
    }
}
