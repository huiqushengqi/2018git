package cn.netinnet.nna.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 测试多视图支持
 *
 * 目前支持解析html和jsp
 */
@RestController
@RequestMapping(value = "/demo")
public class TestViewController {

    @GetMapping("/toJsp")
    public ModelAndView toJsp() {

        return new ModelAndView("demo.jsp");
    }

    @GetMapping("/toHtml")
    public ModelAndView toHtml() {

        return new ModelAndView("demo.html");

    }
}
