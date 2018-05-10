package cn.netinnet.nna.controller;

import cn.netinnet.nna.domain.DemoInfo;
import cn.netinnet.nna.service.DemoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: TestRedisController
 *
 * @version ${version}
 * @Description: TestRedis控制层
 * @author: ${user}
 * @date: ${date} ${time}
 * <p>
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * ${date}      ${user}         1.0             1.0
 * Why & What is modified: <修改原因描述>
 */
@RestController
public class TestRedisController {

    @Autowired
    DemoInfoService demoInfoService;

    @PostMapping("/test")
    public String test(){
        DemoInfo loaded = demoInfoService.queryDemoInfoById(1);
        System.out.println("loaded="+loaded);
        DemoInfo cached = demoInfoService.queryDemoInfoById(1);
        System.out.println("cached="+cached);
        loaded = demoInfoService.queryDemoInfoById(2);
        System.out.println("loaded2="+loaded);
        return "ok";
    }

    @PostMapping("/delete")
    public String delete(long id){
        demoInfoService.deleteFromCache(id);
        return "ok";
    }

    @GetMapping("/test1")
    public String test1(){
        demoInfoService.test();
        System.out.println("DemoInfoController.test1()");
        return "ok";
    }
}
