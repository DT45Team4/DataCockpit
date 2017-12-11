package cn.bdqn.datacockpit.realm;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import cn.bdqn.datacockpit.entity.ActiveUser;
import cn.bdqn.datacockpit.entity.Userinfo;
import cn.bdqn.datacockpit.service.UserinfoService;



public class MyRealm extends AuthorizingRealm{

	@Autowired
	private UserinfoService userService;
	
	public UserinfoService getUserService() {
		return userService;
	}

	public void setUserService(UserinfoService userService) {
		this.userService = userService;
	}

	/**
	 * 为当前登录的用户授予角色和权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ActiveUser activeuser=(ActiveUser)principals.getPrimaryPrincipal();
		//获取数据库中的权限集合
		List<String> listp=activeuser.getListp();
		//存储权限信息
		SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addStringPermissions(listp);
		return simpleAuthorizationInfo;
	}

	/**
	 * 验证当前登录的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取token中存的用户名
		String phone=(String)token.getPrincipal();	
		Userinfo user=null;
		Integer rid=null;
		List<String> listp=null;
			try {
				//根据用户名获取用户信息、角色、权限				
			     user=userService.selectByPhone(phone);
			    if(user==null){
			    	return null;
			    }
			    List<Integer> listi=userService.selectRid(phone);
			     rid=listi.get(0);//角色id 1：企业用户 2：普通管理员 3：超级管理员
			     listp=userService.selectPermissions(rid);//权限集合
			    
            } catch (Exception e) {              
                e.printStackTrace();
                
                System.out.println("登录验证出错");
            }
			ActiveUser activeUser=new ActiveUser();//建立验证通过的角色
		    activeUser.setPhone(phone);
		    activeUser.setPassword(user.getPassword());
		    activeUser.setRid(rid);
		    activeUser.setListp(listp);
		    //存储验证通过的用户信息
            AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(activeUser,user.getPassword(),this.getName());
            return authcInfo;
	}

}
