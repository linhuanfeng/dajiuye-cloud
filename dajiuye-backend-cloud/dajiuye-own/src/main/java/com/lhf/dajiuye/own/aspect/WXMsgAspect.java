package com.lhf.dajiuye.own.aspect;

import com.alibaba.fastjson.JSON;
import com.lhf.dajiuye.own.domain.User;
import com.lhf.dajiuye.own.service.MyUserService;
import com.lhf.dajiuye.own.utils.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * 处理handleWXMsg解密后的用户数据并更新到数据库
 * Spring AOP就是基于动态代理的，如果要代理的对象，实现了某个接⼝，那么Spring AOP会使⽤JDK代理，去创建代理对象，
 * ⽽对于没有实现接⼝的对象，就⽆法使⽤ JDK代理去进⾏代理了，这时候Spring AOP会使⽤Cglib ，
 * 这时候Spring AOP会使⽤ Cglib代理 ⽣成⼀个被代理对象的⼦类来作为代理
 */
@Slf4j
@Aspect
@Component
public class WXMsgAspect {

    @Autowired
    MyUserService userService;

    /**
     * 加密解密用户的openId
     */
    @Pointcut(value = "execution(public * com.lhf.dajiuye.own.controller.UserController.toString(..))")
    public void access(){

    }

    @After("access()")  // After finally advice  所有发生异常也会执行
    public void doAfter(){
        System.out.println("doAfter___postSave..........");
    }

    /**
     *
     @Override
     public Object invoke(MethodInvocation mi) throws Throwable {
        Object retVal = mi.proceed();
        // 这里目标方法已经执行完了，所以不能修改目标方法
        this.advice.afterReturning(retVal, mi.getMethod(), mi.getArguments(), mi.getThis());
        return retVal;
        应该只有around可以改变返回值
     }
     *
     */

    /**
     * 对com.lhf.dajiuye.controller.UserController.handleWXMsg解密后的用户数据进行处理再返回
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "access()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知执行");
        Object[] args = joinPoint.getArgs(); // 想要修改参数的话这里就可以修改
        Object obj = joinPoint.proceed();
        if(obj instanceof Map){
            System.out.println("环绕通知：返回值是map类型");
            Map ret=(Map) obj;
            String realMsg = (String) ret.get("realMsg");
            String sessionKey = (String)ret.get("session_key");
            String openId=(String) ret.get("openid");

            if(realMsg==null){
                // 解析失败
                System.out.println("===========msg为null,获取数据失败");
                return "msg为null";
            }
            // 存储解密后的用户数据的map
            Map jsonMap = JSON.parseObject(realMsg, Map.class);

            if(jsonMap.getOrDefault("watermark","null")!=null){
                // watermark里面有appId,出于安全剔除它
                jsonMap.remove("watermark");
            }

            if(StringUtils.hasLength(openId)){
                // openId进行加密返回客户端
                String encryptId = EncryptUtil.encryptUtil(openId);
                jsonMap.put("myEncrypt",encryptId);
            }

            String id=null;
            String nickName = (String)jsonMap.get("nickName");
            int gender = (int)jsonMap.get("gender");
//        String language = (String)jsonMap.get("language");
            String city = (String)jsonMap.get("city");
            String province = (String)jsonMap.get("province");
//        String country = (String)jsonMap.get("country");
            String avatarUrl = (String)jsonMap.get("avatarUrl");
//        Date lastLoginTime=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());
            Date lastLoginTime=new Date();

            User user = userService.getUserByOpenId(openId);

            log.info("查询到openId为{}的user是:{}===============",openId,user);

            if(user!=null){
                id = user.getId();
                String resume = user.getResume();
                String comId = user.getComId();
                // jsonMap的数据添加用户id和简历，方便发送简历的时候获取
                jsonMap.put("resume",resume);
                jsonMap.put("comId",comId);

                user.setSessionKey(sessionKey);
                user.setNickName(nickName);
                user.setGender(gender);
                user.setCity(city);
                user.setProvince(province);
                user.setAvatar(avatarUrl);
                int i = userService.updateUser(user);
                System.out.println("com.lhf.dajiuye.own.aspect.PostSaveAspect.around：更新user后i=========="+i);
            }else {
                // 当前为新用户
                User newUser = new User();
//                newUser.setId(id);
                newUser.setOpenId(openId);
                newUser.setSessionKey(sessionKey);
                newUser.setNickName(nickName);
                newUser.setGender(gender);
                newUser.setCity(city);
                newUser.setProvince(province);
                newUser.setAvatar(avatarUrl);
                id = String.valueOf(userService.saveUser(newUser));
            }
            // 添加id到返回结果中，方便前端获取id
            jsonMap.put("id",id);
            obj=jsonMap;
        }
        return obj;
    }

//    @AfterReturning(returning = "ret",value = "PostSaveAspect()") // After returning advice ,argNames = "args"有什么用
    public void doAfterReturning(Map ret){
        // AfterReturning并不能改变返回值，因为目标方法已经执行
    }
}
