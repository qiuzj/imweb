package cn.javaee.imweb.controller;

import cn.javaee.imweb.common.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    @GetMapping("/")
    public String index(@RequestParam(name = "username", required = false)
                                      String username, HttpSession session) {
        if (session.getAttribute(Constants.SESSION_KEY) != null) {
            return "index"; // 首页
        } else {
            return "login"; // 登录页
        }
    }

    @RequestMapping("/login")
    public String login(@RequestParam String username, String password, Model model, HttpSession session) {
        if (!StringUtils.hasText(username)) {
            return "login";
        }
        model.addAttribute("username", username);
        session.setAttribute(Constants.SESSION_KEY, username);
        return "index";
    }

    @RequestMapping("/logout")
    public String login(HttpSession session) {
        session.removeAttribute(Constants.SESSION_KEY);
        return "login";
    }

}