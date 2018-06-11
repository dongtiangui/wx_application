package com.spring.boot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping(value = "/error")
public class expretionController implements ErrorController {
    private static final Logger log = LoggerFactory.getLogger(expretionController.class);
    @RequestMapping(value = "error", produces = "text/html")
    public String errorHtml(HttpServletRequest request) {
        log.info("不存在");
        return getErrorPath();
    }
    @RequestMapping
    @Override
    public String getErrorPath() {
        return "errorlocal";
    }
}
