/**
 * Project Name:DataCockpit
 * File Name:LoginController.java
 * Package Name:cn.bdqn.datacockpit.controller
 * Date:2017年8月23日上午9:44:48
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */
/**
 * 
 */

package cn.bdqn.datacockpit.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.bdqn.datacockpit.entity.Companyinfo;
import cn.bdqn.datacockpit.service.CompanyinfoService;

/**
 * Description: <br/>
 * Date: 2017年8月23日 上午9:44:48 <br/>
 * 
 * @author jiaoHJ
 * @version
 * @see
 */
@Controller
@Scope("prototype")
public class LoginController {
    @Autowired
    private CompanyinfoService companyinfo;

    /**
     * 登录
     * 
     * @param phone
     * @param password
     * @param onLine
     * @param res
     * @param req
     * @return
     */
    @RequestMapping("/login")
    public String login(String phone, String password, String onLine, HttpServletResponse res, HttpServletRequest req) {
        Companyinfo compi = companyinfo.selectByPhone(phone);
        HttpSession session = req.getSession();
        // 判断账号密码是否正确
        if (phone.equals(compi.getPhone()) && password.equals(compi.getPassword())) {

            session.setAttribute("info", compi);
            return "redirect:/user_index.shtml";

        } else {
            session.setAttribute("mess", "*账号或者密码输入有误！");
            return "redirect:/login.jsp";
        }

        // if ("18313184517".equals(phone) && "12345678".equals(password)) {
        // if (onLine != null) {
        // Cookie cookie = new Cookie("login", phone);
        // Integer time = Integer.parseInt(onLine);
        // cookie.setMaxAge(time * 20);
        // res.addCookie(cookie);
        // }
        // return "front/success";
        // }
        // return "front/error";
    }

    @RequestMapping("/testLogin")
    public String testLogin(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if ("login".equals(cookie.getName())) {
                return "front/success";
            }
        }
        HttpSession session = req.getSession();
        session.setAttribute("mess", "");
        return "front/error";
    }

    /**
     * 注册（申请合作）
     * 
     * @param cominfo
     * @return
     */
    @RequestMapping("/register")
    public String register(Companyinfo cominfo) {
        int flag = companyinfo.insert(cominfo);
        if (flag >= 1) {
            return "front/shenqing";
        }

        return "front/error";
    }

    /**
     * 修改资料，先获取存在session里面的实体类
     * 
     * @param req
     * @return
     */
    @RequestMapping("/updateInfo")
    public String updateInfo(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Companyinfo compi = (Companyinfo) session.getAttribute("info");
        session.setAttribute("comp", compi);

        return "redirect:/user_update.shtml";
    }

    /**
     * 动态修改资料，修改密码不在此页面
     * 
     * @param company
     * @param req
     * @return
     */
    @RequestMapping("/updateInfo1")
    public String updateInfo1(Companyinfo company, HttpServletRequest req) {
        // System.out.println(company);
        HttpSession session = req.getSession();
        int flag = companyinfo.updateByPrimaryKeySelective(company);
        if (flag >= 1) {
            return "redirect:/user_index.shtml";
        }
        session.setAttribute("message", "*修改失败！");
        return "redirect:/user_update.shtml";
    }

    @RequestMapping("/updatePassword")
    public String updatePassword(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Companyinfo compi = (Companyinfo) session.getAttribute("info");
        System.out.println(compi);
        session.setAttribute("comp", compi);
        return "redirect:/user_pass.shtml";
    }

    @RequestMapping("/updatePassword1")
    public String updatePassword1(Companyinfo company) {
        int flag = companyinfo.updateByPrimaryKeySelective(company);
        if (flag >= 1) {
            return "redirect:/user_index.shtml";
        }
        return "redirect:/user_pass.shtml";
    }
}