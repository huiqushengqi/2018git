package cn.netinnet.nna.controller;

import cn.netinnet.nna.domain.User;
import cn.netinnet.nna.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @ClassName:    LoginController
 * @Description:  login控制层
 *
 * @author         ${user}
 * @version        V1.0
 * @Date           ${date} ${time}
 *
 **/
@RestController
@RequestMapping(value = "/")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private Environment env;

    @GetMapping("/")
    public ModelAndView toLogin(){

        return new ModelAndView("login.jsp");
    }

    @PostMapping("/login")
    public Map<String,Object> login(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map =new HashMap<String,Object>();
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        if(!userName.equals("") && password!=""){
            User user = this.userService.queryUserByObject(new User(userName,password));
           // User user = getUserFromApi(userName,password);
            if (user != null) {
                request.getSession().setAttribute("user",user);
                map.put("result","1");
            }else {
                map.put("result","0");
            }
        }else{
            map.put("result","0");
        }
        return map;
    }

    // public User getUserFromApi(String username, String pwd) {
    //     String url = env.getProperty("api.99cj.url");
    //     String route = env.getProperty("api.99cj.route");
    //     String route2 = env.getProperty("api.99cj.route2");
    //     String key = env.getProperty("api.99cj.key");
    //     return new User();
    // }

}
