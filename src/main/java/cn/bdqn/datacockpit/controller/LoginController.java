/**
 * Project Name:DataCockpit
 * File Name:LoginController.java
 * Package Name:cn.bdqn.datacockpit.controller
 * Date:2017年8月23日上午9:44:48
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.bdqn.datacockpit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bdqn.datacockpit.entity.ActiveUser;
import cn.bdqn.datacockpit.entity.Companyinfo;
import cn.bdqn.datacockpit.entity.Info;
import cn.bdqn.datacockpit.entity.Userinfo;
import cn.bdqn.datacockpit.realm.CustomException;
import cn.bdqn.datacockpit.realm.MyRealm;

import cn.bdqn.datacockpit.service.CompanyinfoService;
import cn.bdqn.datacockpit.service.InfoService;
import cn.bdqn.datacockpit.service.UserinfoService;
import cn.bdqn.datacockpit.utils.LoggerUtils;
import cn.bdqn.datacockpit.utils.VerifyCodeUtils;

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
    private static final Exception IncorrectCredentialsException = null;

    @Autowired
    private CompanyinfoService companyinfo;

    @Autowired
    private UserinfoService userinfo;

    @Autowired
    private InfoService infoService;

    @RequestMapping(value = "getYzm")
    public @ResponseBody List<String> getYzm(HttpServletResponse response, HttpServletRequest request) {
        List<String> lists = new ArrayList<String>();
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");

            // 生成随机字串
            String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
            // 存入会话session
            HttpSession session = request.getSession(true);
            session.setAttribute("truecode", verifyCode.toLowerCase());
            // 生成图片
            int w = 146, h = 33;
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
            lists.add("OK");
            return lists;
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "获取验证码异常：%s", e.getMessage());
        }
        return lists;

    }

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
    public String login(HttpSession session,String phone,String password,String randomcode,String rememberMe, HttpServletRequest req) {    	     	
        try {
        	//校验验证码
        	String truecode=(String)session.getAttribute("truecode");
    		//获取用户输入的验证码
    		String code2=(String)req.getParameter("code2");    		
    		if(!truecode.equals(code2)){
    			//如果校验失败   存储异常信息返回页面			    			
    			session.setAttribute("exception","验证码错误！" );
    			throw new CustomException("验证码错误！");
    		}
    		//判断用户是否已经登录				
    		Userinfo user=userinfo.selectByPhone(phone);
		    if(user!=null){
		    	int loginstate =user.getLoginstate();
		    	if(loginstate==1){
		    		session.setAttribute("exception", "该账号已登录！");
		    		throw new CustomException("账号重复登录异常");
		    	}
		    }    		
    		//开始登录
        	Subject subject = SecurityUtils.getSubject();//建立主体
            UsernamePasswordToken token = new UsernamePasswordToken(phone, password);//获取令牌
            String rem=rememberMe;
            token.setRememberMe(Boolean.parseBoolean(rememberMe));
            System.out.println(phone+"\t"+password);
            subject.login(token);//主体和令牌验证 
            String exceptionClassName = (String) req.getAttribute("shiroLoginFailure"); 
            //如果登录成功就可以获取subject中存储的用户信息
            ActiveUser activeuser=(ActiveUser)subject.getPrincipals().getPrimaryPrincipal();
            //改变用户登录状态
            /*HashMap map1=new HashMap();
            map1.put("loginstate", 1);
            map1.put("phone", phone);
            userinfo.updateLoginstateByPhone(map1);
            super.getApplicationAttr(Constant.LOGIN_USER_MAP);*/
            //传递到跳转页面中 的信息 判断是否需要加new标志                
            List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();       
            List<Info> infoList = infoService.selectAllInfo();           
            Date time = new Date();
            Date ti1 = new Date(time.getTime() - 7 * 24 * 60 * 60 * 1000); //判断通知是否是一周之内的，加状态位置
            for (Info info : infoList) {
                Date date = info.getPublishDate();
                Map<String, Object> map = new HashMap<String, Object>();
                if (ti1.before(date)) {
                    map.put("date", 1);
                } else {
                    map.put("date", 0);
                }
                map.put("info", info);
                lists.add(map);
            }
            //根据角色id判断跳转页面
            int rid=activeuser.getRid();//获取用户角色id
            if (rid==1) {//如果该用户是企业用户
            	Companyinfo compi = companyinfo.selectByPhone(phone);//得到公司信息列表
                session.setAttribute("infos", compi);
                session.setAttribute("flag", lists);
                return "redirect:/user_index.shtml";
            }
            if (rid==2||rid==3) {//该用户是平台管理员
            	Userinfo ui = userinfo.selectByPhone(phone);
                session.setAttribute("infos", ui);
                session.setAttribute("flag", lists);
                return "redirect:/selectAllCompanyinfo.shtml";
            }
        } catch (Exception e) {        	
    			if (UnknownAccountException.class.getName().equals(e.getClass().getName())) {
    				//最终会抛给异常处理器
    				session.setAttribute("exception", "账号错误！");
    			} else if (IncorrectCredentialsException.class.getName().equals(
    					e.getClass().getName())) {
    				session.setAttribute("exception", "密码错误！");
    			}
    			e.printStackTrace();        	
        }
        	return "redirect:/login.jsp";
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
            return "front/shenqing.jsp";
        }

        return "front/error.jsp";
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
        Companyinfo compi = (Companyinfo) session.getAttribute("infos");
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

    /**
     * 把密码带到页面
     * 
     * @param req
     * @return
     */
    @RequestMapping("/updatePassword")
    public String updatePassword(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Companyinfo compi = (Companyinfo) session.getAttribute("infos");
        session.setAttribute("comp", compi);
        return "redirect:/user_pass.shtml";
    }

    /**
     * 修改密码
     * 
     * @param company
     * @return
     */
    @RequestMapping("/updatePassword1")
    public String updatePassword1(Companyinfo company,HttpSession session) {
        int flag = companyinfo.updateByPrimaryKeySelective(company);
        if (flag >= 1) {
            return "redirect:/login.shtml";
        }
        String failure="密码修改失败！";
        return "redirect:/admin_uppassword.shtml";
    }

    /**
     * 检验注册的手机号码是否存在
     * 
     * @param phone
     * @return
     */
    @RequestMapping("/testPhone")
    @ResponseBody
    public Map<String, Object> testPhone(String phone) {
        int flag = companyinfo.selectPhoneNum(phone);
        Map<String, Object> hm = new HashMap<String, Object>();
        if (flag >= 1) {
            hm.put("num", 1);
            hm.put("error", "*您输入的手机号码已存在！");
        } else {
            hm.put("num", 0);
            hm.put("error", "");
        }
        return hm;
    }

    /**
     * 退出登录
     * 
     * @param req
     * @return
     */
    @RequestMapping("/exit")
    public String exit(HttpServletRequest req,HttpServletResponse res,HttpSession session) {
    	Subject subject=SecurityUtils.getSubject();
    	//改变用户的登录状态为0 未登录  	
    	/*ActiveUser activeUser=(ActiveUser)subject.getPrincipals().getPrimaryPrincipal();
    	String phone=activeUser.getPhone();
    	HashMap map=new HashMap();
    	map.put("loginstate", 0);
    	map.put("phone", phone);
    	userinfo.updateLoginstateByPhone(map);*/
    	subject.logout();  	            
        return "front/exit.jsp";
    }

    /**
     * 公告详情
     * 
     * @param req
     * @return
     */
    @RequestMapping("/gongGao")
    public String gongGao(Integer id, Model model) {
        // System.out.println(id);
        Info info = infoService.selectByPrimaryKey(id);
        model.addAttribute("gg", info);
        return "user_gongGao.pages";
    }

    /**
     * 公告详情
     * 
     * @param req
     * @return
     */
    @RequestMapping("/selectTongzhi")
    public String selectTongzhi(Model model) {
        List<Info> lists = infoService.selectAllInfo();
        model.addAttribute("infoList", lists);
        return "user_tongzhi.pages";
    }
}
