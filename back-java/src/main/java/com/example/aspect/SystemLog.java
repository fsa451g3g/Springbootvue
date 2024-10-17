//package com.example.aspect;
//
//import com.example.modules.log.dao.SystemLogDao;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import java.util.Arrays;
//
//@Slf4j
//@Aspect
//@Component
//public class SystemLog {
//
//    SystemLogDao systemLogDao;
//
//    //切入点：待增强的方法
//    @Pointcut("execution(public * com.example.modules.*.controller.*.*(..))")
//    //切入点签名
//    public void systemLog() {
//    }
//
//    //前置通知
//    @Before("systemLog()")
//    public void deBefore(JoinPoint jp) throws Throwable {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        com.example.modules.log.entity.SystemLog systemLog = new com.example.modules.log.entity.SystemLog();
//        systemLog.setArgs(Arrays.toString(jp.getArgs()));
//        systemLog.setUrl(request.getRequestURL().toString());
//        systemLog.setMethod(request.getMethod());
//        systemLog.setClassMethod(jp.toString());
//        systemLog.setResult(true);
//        systemLog.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//        systemLogDao.insert(systemLog);
//        request.setAttribute("id", systemLog.getId());
//    }
//
//    //异常通知
//    @AfterThrowing(throwing = "e", pointcut = "systemLog()")
//    public void exception(JoinPoint jp, Exception e) {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        Object id = request.getAttribute("id");
//        com.example.modules.log.entity.SystemLog systemLog = systemLogDao.selectById(id.toString());
//        systemLog.setResult(false);
//        systemLog.setMessage(e.getMessage());
//        systemLogDao.updateById(systemLog);
//    }
//
//    @Autowired
//    public void setSystemLogDao(SystemLogDao systemLogDao) {
//        this.systemLogDao = systemLogDao;
//    }
//}
