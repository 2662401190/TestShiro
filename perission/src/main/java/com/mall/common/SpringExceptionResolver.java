package com.mall.common;
import com.mall.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 *  定义全局异常处理类
 */
@Slf4j
public class SpringExceptionResolver  implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(javax.servlet.http.HttpServletRequest Request, javax.servlet.http.HttpServletResponse Response, Object o, Exception e) {
        String url = Request.getRequestURI().toString();
        ModelAndView mv;
        String defaultMsg = "System error";
        //  要求项目中所有json请求数据，都以json 结尾
        if (url.endsWith(".json")) {
            if (e instanceof PermissionException) {
                JsonData result = JsonData.fail(e.getMessage());
                mv=new ModelAndView("jsonView",result.toMap());
            }else {
                log.error("unknown json exception",url,e);
                JsonData result = JsonData.fail(defaultMsg);
                mv=new ModelAndView("jsonView",result.toMap());
            }
            // 要求项目中所有page请求数据，都以json 结尾
        } else if (url.endsWith(".page")) {
            log.error("unknown page exception",url,e);
            JsonData jsonData = JsonData.fail(defaultMsg);
            mv=new ModelAndView("exception",jsonData.toMap());
        }else {
            log.error("unknown  exception",url,e);
            JsonData result = JsonData.fail(defaultMsg);
            mv=new ModelAndView("jsonView",result.toMap());
        }
        return mv;
    }
}
