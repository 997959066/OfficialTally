package cn.xiaoyu.aop;

import cn.xiaoyu.common.*;
import cn.xiaoyu.util.DateUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AspectHandlerToken extends Base {


    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around(Constants.TALLY)
    public Object doTally(ProceedingJoinPoint pjp) throws Throwable {
        return token(pjp);
    }

    @Around(Constants.SYSTEM)
    public Object doSystem(ProceedingJoinPoint pjp) throws Throwable {
        return token(pjp);
    }


    private Object token(final ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String g_token = request.getParameter("g_token");
        String g_time = request.getParameter("g_time");
        String g_userId = request.getParameter("g_userId");
        final String currentTime = DateUtil.getSysDate();
        if (g_token != null && g_token.length() > 0 && g_time != null && g_time.length() > 0 && g_userId != null && g_userId.length() > 0) {
            if (MD5.md5(g_userId, g_time, xiaoyu_key).equals(g_token)) {
                if (DateUtil.compareTimeOut(g_time, currentTime)) {
                    g_token = MD5.md5(g_userId, currentTime, xiaoyu_key);
                    g_time = currentTime;
                }
                ResponseMessage responseMessage = (ResponseMessage) pjp.proceed();
                if (null == responseMessage) return new ResponseMessage();//avoid exception for download and check task

                responseMessage.setTokenMessage(defaultTokenHandler(g_token, g_time, g_userId));
                //返回
                return responseMessage;

            }
        }
        //返回
        return responseMessage(MessageCode.USER_NEED_RELOGIN);

    }


    public TokenMessage defaultTokenHandler(final String token, final String currentTime, final String userID) {
        TokenMessage entity = new TokenMessage();
        entity.setG_token(token);
        entity.setG_time(currentTime);
        entity.setG_userId(userID);
        return entity;
    }

}