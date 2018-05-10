package cn.netinnet.nna.controller;


import cn.netinnet.nna.domain.LearnResource;
import cn.netinnet.nna.service.LearnResourceService;
import cn.netinnet.nna.util.ServletUtil;
import cn.netinnet.nna.util.StringUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @ClassName:    LearnController
 * @Description:  learn控制层
 *
 * @author         ${user}
 * @version        V1.0
 * @Date           ${date} ${time}
 *
 **/
@RestController
@RequestMapping("/learn")
public class LearnController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LearnResourceService learnResourceService;

    @GetMapping("")
    public ModelAndView main(){

        return new ModelAndView("learnResource.jsp");
    }


    // @RequestMapping(value = "/queryLeanList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/queryLeanList", produces = "application/json;charset=UTF-8")
    public void queryLearnList(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
        String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", page);
        params.put("rows", rows);
        params.put("author", author);
        params.put("title", title);
        List<LearnResource> learnList = learnResourceService.queryLearnResouceList(params);
        PageInfo<LearnResource> pageInfo = new PageInfo<LearnResource>(learnList);

        JSONObject jo = new JSONObject();
        jo.put("rows", learnList);
        jo.put("total", pageInfo.getPages());//总页数
        jo.put("records", pageInfo.getTotal());//查询出的总记录数
        ServletUtil.createSuccessResponse(200, jo, response);
    }

    /**
     * 新添教程
     *
     * @param request
     * @param response
     */
    @PostMapping(value = "/add")
    public void addLearn(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String url = request.getParameter("url");
        if (StringUtil.isNullOrEmpty(author)) {
            result.put("message", "作者不能为空!");
            result.put("flag", false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if (StringUtil.isNullOrEmpty(title)) {
            result.put("message", "教程名称不能为空!");
            result.put("flag", false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if (StringUtil.isNullOrEmpty(url)) {
            result.put("message", "地址不能为空!");
            result.put("flag", false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        LearnResource learnResource = new LearnResource();
        learnResource.setAuthor(author);
        learnResource.setTitle(title);
        learnResource.setUrl(url);
        int index = learnResourceService.add(learnResource);
        if (index > 0) {
            result.put("message", "教程信息添加成功!");
            result.put("flag", true);
        } else {
            result.put("message", "教程信息添加失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }

    /**
     * 修改教程
     *
     * @param request
     * @param response
     */
    @PostMapping(value = "/update")
    public void updateLearn(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String id = request.getParameter("id");
        LearnResource learnResource = learnResourceService.queryLearnResouceById(Long.valueOf(id));
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String url = request.getParameter("url");
        if (StringUtil.isNullOrEmpty(author)) {
            result.put("message", "作者不能为空!");
            result.put("flag", false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if (StringUtil.isNullOrEmpty(title)) {
            result.put("message", "教程名称不能为空!");
            result.put("flag", false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if (StringUtil.isNullOrEmpty(url)) {
            result.put("message", "地址不能为空!");
            result.put("flag", false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        learnResource.setAuthor(author);
        learnResource.setTitle(title);
        learnResource.setUrl(url);
        int index = learnResourceService.update(learnResource);
        System.out.println("修改结果=" + index);
        if (index > 0) {
            result.put("message", "教程信息修改成功!");
            result.put("flag", true);
        } else {
            result.put("message", "教程信息修改失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }

    /**
     * 删除教程
     *
     * @param request
     * @param response
     */
    @PostMapping(value = "/delete")
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        String ids = request.getParameter("ids");
        System.out.println("ids===" + ids);
        JSONObject result = new JSONObject();
        //删除操作
        int index = learnResourceService.deleteByIds(ids.split(","));
        if (index > 0) {
            result.put("message", "教程信息删除成功!");
            result.put("flag", true);
        } else {
            result.put("message", "教程信息删除失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }
}
