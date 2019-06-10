package com.winconlab.clams.aop.logRecord;

import com.winconlab.clams.utils.LogBackUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class LogRecordAcpect {

    @Pointcut("execution(* com.winconlab.clams.service..*.add*(..))")
    public void addServicePointcut() {
    }

    @Pointcut("execution(* com.winconlab.clams.service..*.update*(..))")
    public void updateServicePointcut() {
    }

    @Pointcut("execution(* com.winconlab.clams.service..*.del*(..))")
    public void delServicePointcut() {
    }

//    @Pointcut("execution(* com.winconlab.clams.service..*.find*(..))")
//    public void findServicePointcut() {
//    }

    @AfterReturning(pointcut = "addServicePointcut()", returning = "ret")
    public void addServiceAdvice(JoinPoint joinPoint, Object ret) {
        //处理添加操作日志
        LogBackUtil.info(joinPoint.getSignature().getDeclaringTypeName());
    }

    @AfterReturning(pointcut = "updateServicePointcut()", returning = "ret")
    public void updateServiceAdvice(JoinPoint joinPoint, Object ret) {
        //处理修改操作日志
    }

    //不记录查找操作
//    @AfterReturning(pointcut = "findServicePointcut()", returning = "ret")
//    public void findServiceAdvice(JoinPoint joinPoint, Object ret) {
//        String username = "";
//        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
//        if (user != null && !StringUtils.isEmpty(username = user.getUsername())) {
//            LogBackUtil.info(username + "--" + user.getUsername());
//        }
//        //处理查找操作日志
//        LogBackUtil.info(joinPoint.getSignature().getDeclaringTypeName());
//        if (joinPoint.getSignature().getDeclaringTypeName().contains("SysUserServiceImpl")) {
//            LogBackUtil.info("用户查找操作");
//        }
//    }

    //TODO 删除

    /**
     * 使用Java反射来获取被拦截方法(insert、update)的参数值，
     * 将参数值拼接为操作内容
     */
    public String getOptionContent(Object[] args, String mName) throws Exception {

        if (args == null) {
            return null;
        }
        StringBuffer rs = new StringBuffer();
        rs.append(mName);
        String className = null;
        int index = 1;
        // 遍历参数对象
        for (Object info : args) {
            //获取对象类型
            className = info.getClass().getName();
            className = className.substring(className.lastIndexOf(".") + 1);
            rs.append("[参数" + index + "，类型：" + className + "，值：");
            // 获取对象的所有方法
            Method[] methods = info.getClass().getDeclaredMethods();
            // 遍历方法，判断get方法
            for (Method method : methods) {
                String methodName = method.getName();
                // 判断是不是get方法
                if (methodName.indexOf("get") == -1) {// 不是get方法
                    continue;// 不处理
                }
                Object rsValue = null;
                try {
                    // 调用get方法，获取返回值
                    rsValue = method.invoke(info);
                    if (rsValue == null) {//没有返回值
                        continue;
                    }
                } catch (Exception e) {
                    continue;
                }
                //将值加入内容中
                rs.append("(" + methodName + " : " + rsValue + ")");
            }
            rs.append("]");
            index++;
        }
        return rs.toString();
    }
}
