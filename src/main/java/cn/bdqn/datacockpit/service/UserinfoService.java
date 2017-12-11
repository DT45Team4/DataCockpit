/**
 * Project Name:DataCockpit
 * File Name:UserinfoService.java
 * Package Name:service
 * Date:2017年8月21日下午1:57:45
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.bdqn.datacockpit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import cn.bdqn.datacockpit.entity.Userinfo;

/**
 * Description: <br/>
 * Date: 2017年8月21日 下午1:57:45 <br/>
 * 
 * @author caoS
 * @version
 * @see
 */
public interface UserinfoService {
    
    List<Userinfo> selectAllUserinfo();

    int deleteByPrimaryKey(Integer id);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);
    //给上述账号绑定管理员角色
    int insertAdminRole(Userinfo record);

    Userinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);
    
    /**
     * shiro通过电话号查询用户
     * @param userName
     * @return
     */
    public Userinfo selectByPhone(String phone);
    //修改用户登录状态
    public int updateLoginstateByPhone(HashMap map);
    /**
     * shiro通过电话号查询对应角色编号
     * @param userName
     * @return
     */
    public List<Integer> selectRid(String phone);
    
    /**
     * shiro通过电话号查询权限信息
     * @param userName
     * @return
     */
    public List<String> selectPermissions(Integer rid);
}
