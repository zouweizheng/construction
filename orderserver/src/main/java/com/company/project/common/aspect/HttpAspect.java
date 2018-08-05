package com.company.project.common.aspect;


import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    private String requestPath = null ; // 请求地址
    private String userName = null ; // 用户名
    private String ip = null;  //来访ip
    private Map<?,?> inputParamMap = null ; // 传入参数
    private Map<String, Object> outputParamMap = null; // 存放输出结果
    private long startTimeMillis = 0; // 开始时间
    private long endTimeMillis = 0; // 结束时间


    @Pointcut("execution(public * com.company.project.common.web.*.*(..))")
    public void log(){
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        startTimeMillis = System.currentTimeMillis(); // 记录方法开始执行的时间
        //获取值
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        inputParamMap = request.getParameterMap();
        requestPath = request.getRequestURI();
        //校验token
        //String token = inputParamMap.get("token").toString();

        //插入参数

        request.setAttribute("tid","898989898");

    }

    @After("log()")
    public void doAfter(){
        endTimeMillis = System.currentTimeMillis(); // 记录方法执行完成的时间
        this.printOptLog();
    }

    /*@AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object){
        logger.info("respont={}", object);
    }*/

    @Around("log()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        Map userInfo=new HashMap();
        inputParamMap = request.getParameterMap();
        requestPath = request.getRequestURI();
        request.setAttribute("tid","898989898");
        ip = request.getRemoteAddr();
        outputParamMap = new HashMap<String, Object>();
        Object[] args = pjp.getArgs();
        Object result = pjp.proceed(args);
        outputParamMap.put("result", result);
        return result;
    }

    /**
     * 打印日志
     */
    private void printOptLog() {
        String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);
        logger.info("\n user："+userName
                +";url："+requestPath+";ip："+ip+";op_time：" + optTime + ";pro_time：" + (endTimeMillis - startTimeMillis) + "ms"
                +";param："+JSONObject.toJSONString(inputParamMap)+";result："+JSONObject.toJSONString(outputParamMap));
        logger.debug("debug test");
    }


}
