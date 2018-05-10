package cn.netinnet.nna.controller;

import cn.netinnet.nna.domain.User;
import cn.netinnet.nna.service.UserService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 测试数据源读写分离
 *
 * 数据源模块化之配置读写分离
 *
 * config目录中的dbconfig目录下的DataSourceAopInService、DataSourceConfiguration、
 * MybatisConfiguration的注解去掉注释
 *
 * 配置多服务器，mysql数据库设置主从关系（配置主从关系：http://369369.blog.51cto.com/319630/790921/）
 *
 */
// @RestController
// @RequestMapping("/user")
public class TestReadAndWrite {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;


    /**
     * 测试插入
     * @return
     */
    @PostMapping("/add")
    public String add(String id,String userName){
        User u = new User();
        u.setId(id);
        u.setUsername(userName);
        this.userService.insert(u);
        return u.getId()+" " + u.getUsername();
    }

    /**
     * 测试读
     * @param id
     * @return
     */
    @PostMapping("/get/{id}")
    public String findById(@PathVariable("id") String id){
        User u = this.userService.findById(id);
        return u.getId()+"    " + u.getUsername();
    }

    /**
     * 测试读然后写
     * @param id
     * @param userName
     * @return
     */
    @PostMapping("/readAndAdd")
    public String readAndWrite(String id,String userName){
        User u = new User();
        u.setId(id);
        u.setUsername(userName);
        this.userService.readAndWirte(u);
        return u.getId()+"    " + u.getUsername();
    }

    /**
     * 测试写然后读
     * @param id
     * @param userName
     * @return
     */
    @PostMapping("/addAndRead")
    public String addAndRead(String id,String userName){
        User u = new User();
        u.setId(id);
        u.setUsername(userName);
        this.userService.wirteAndRead(u);
        return u.getId()+"    " + u.getUsername();
    }

    /**
     * 测试分页插件
     * @return
     */
    @PostMapping("/queryPage")
    public String queryPage(){
        PageInfo<User> page = this.userService.queryPage("tes", 1, 2);
        StringBuilder sb = new StringBuilder();
        sb.append("<br/>总页数=" + page.getPages());
        sb.append("<br/>总记录数=" + page.getTotal()) ;
        for(User u : page.getList()){
            sb.append("<br/>" + u.getId() + "      " + u.getUsername());
        }
        System.out.println("分页查询....\n" + sb.toString());
        return sb.toString();
    }
}
