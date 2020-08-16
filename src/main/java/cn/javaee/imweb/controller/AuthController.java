package cn.javaee.imweb.controller;

import cn.javaee.imweb.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Value("${imserver.websocket.url}")
    private String wsURL;

    @GetMapping("/")
    public String index(@RequestParam(name = "username", required = false)
                                      String username, HttpSession session) {
        if (session.getAttribute(Constants.SESSION_KEY) != null) {
            return "index"; // 首页
        } else {
            return "login"; // 登录页
        }
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam String username, String password, Model model, HttpSession session) {
        if (!StringUtils.hasText(username)) {
            return "login";
        }

        model.addAttribute("username", username);
        model.addAttribute("wsURL", wsURL);
        session.setAttribute(Constants.SESSION_KEY, username);

        return "index";
    }

    /**
     * 退出
     *
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String login(HttpSession session) {
        session.removeAttribute(Constants.SESSION_KEY);
        return "login";
    }

}