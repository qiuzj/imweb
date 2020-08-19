package cn.javaee.imweb.service;

import cn.javaee.imweb.controller.JsonResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录业务逻辑
 */
@Service
public class LoginService extends BaseService {

    /* 模拟DB用户数据 */
    public static final List<UserInfo> USER_INFO_LIST;
    public static final Map<String, UserInfo> USER_INFO_MAP;
    static {
        USER_INFO_LIST = new ArrayList<>();
        USER_INFO_MAP = new HashMap<>();
        UserInfo user1 = new UserInfo(1L, "qiuzj", "123");
        UserInfo user2 = new UserInfo(2L, "hfyan", "123");
        UserInfo user3 = new UserInfo(3L, "tony", "123");
        USER_INFO_LIST.add(user1);
        USER_INFO_LIST.add(user2);
        USER_INFO_LIST.add(user3);
        USER_INFO_LIST.stream().forEach(u -> {
            USER_INFO_MAP.put(u.getUsername(), u);
        });
    }

    /**
     * 登录认证
     *
     * @param username
     * @param password
     * @return
     */
    public JsonResult<String> authenticate(String username, String password) {
        if (!USER_INFO_MAP.containsKey(username)) {
            LOGGER.error("[loginError] username not exists: {}", username);
            return JsonResult.error("用户名或密码错误");
        }
        if (!USER_INFO_MAP.get(username).getPassword().equals(password)) {
            LOGGER.error("[loginError] password is incorrect: {}", password);
            return JsonResult.error("用户名或密码错误");
        }
        return JsonResult.success();
    }

}
