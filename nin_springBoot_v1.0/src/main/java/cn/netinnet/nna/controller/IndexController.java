package cn.netinnet.nna.controller;

import cn.netinnet.nna.domain.DemoInfo;
import cn.netinnet.nna.domain.LearnResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ClassName:    IndexController
 * @Description:  index控制层
 *
 * @author         ${user}
 * @version        V1.0
 * @Date           ${date} ${time}
 *
 **/
@RestController
@RequestMapping("/index") // 规范，类名必须添加映射，避免下面方法的映射和其它类冲突
public class IndexController {

    @GetMapping("/")
    public ModelAndView index(){

        return new ModelAndView("login.jsp");
    }

    @GetMapping("/toIndex")
    public ModelAndView toIndex(){

        return new ModelAndView("index.jsp");
    }

    @GetMapping("/main")
    public ModelAndView main(){

        return new ModelAndView("main.jsp");
    }

    /************************* 以下为测试部分 *************************/

    @GetMapping("/getDemoInfo")
    public DemoInfo getDemoInfo(){
        DemoInfo demoInfo= new DemoInfo();
        demoInfo.setId(new Integer("111"));
        demoInfo.setName("张三");
        demoInfo.setPwd("aaaaa");
        return demoInfo;
    }

    @GetMapping("/testError")
    public String testError(){
        int temp = 2/0;
        String testStr = "aaa";
        return String.valueOf(temp);
    }

    @GetMapping("/index2")
    public ModelAndView index2(){

        List<LearnResource> learnList =new ArrayList<LearnResource>();
        LearnResource bean =new LearnResource("官方参考文档","Spring Boot Reference Guide","http://docs.spring.io/spring-boot/docs/1.5.1.RELEASE/reference/htmlsingle/#getting-started-first-application");
        learnList.add(bean);
        bean =new LearnResource("官方SpriongBoot例子","官方SpriongBoot例子","https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples");
        learnList.add(bean);
        ModelAndView modelAndView = new ModelAndView("index2.jsp");
        modelAndView.addObject("learnList", learnList);
        return modelAndView;
    }

}
