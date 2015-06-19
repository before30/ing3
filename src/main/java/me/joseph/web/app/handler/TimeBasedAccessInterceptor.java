package me.joseph.web.app.handler;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import me.joseph.common.util.LogUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

public class TimeBasedAccessInterceptor extends HandlerInterceptorAdapter{
    private int openingTime = 13;
    private int closingTime = 14;

    public void setOpeningTime(int openingTime){
        this.openingTime = openingTime;
    }

    public void setClosingTime(int closingTime){
        this.closingTime = closingTime;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        LogUtils.debugLog.info("REQUEST TIME {}", hour);


        if (StringUtils.contains(request.getRequestURI(), "closed")) {
            return true;
        }


        if (openingTime <= hour && hour < closingTime){
            return true;
        }

        response.sendRedirect("closed.html");
        return false;
    }
}
