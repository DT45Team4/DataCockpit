/**
 * Project Name:DataCockpit
 * File Name:UserinfoServiceImpl.java
 * Package Name:service.impl
 * Date:2017年8月21日下午1:58:39
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.bdqn.datacockpit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bdqn.datacockpit.entity.Userinfo;
import cn.bdqn.datacockpit.mapper.UserinfoMapper;
import cn.bdqn.datacockpit.service.UserinfoService;

/**
 * Description: <br/>
 * Date: 2017年8月21日 下午1:58:39 <br/>
 * 
 * @author caoS
 * @version
 * @see
 */
@Service
public class UserinfoServiceImpl implements UserinfoService {

    @Autowired
    UserinfoMapper userinfoMapper;

    @Override
    public List<Userinfo> selectAllUserinfo() {
        return userinfoMapper.selectAllUserinfo();
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        int flag = userinfoMapper.deleteByPrimaryKey(id);
        return flag;
    }

    @Override
    public int insert(Userinfo record) {
        int flag = userinfoMapper.insert(record);
        return flag;
    }

    @Override
    public int insertSelective(Userinfo record) {
        int flag = userinfoMapper.insertSelective(record);
        return flag;
    }
    @Override
	public int insertAdminRole(Userinfo record) {
		// TODO Auto-generated method stub
		return userinfoMapper.insertAdminRole(record);
	}
    @Override
    public Userinfo selectByPrimaryKey(Integer id) {
        return userinfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Userinfo record) {
        int flag = userinfoMapper.updateByPrimaryKeySelective(record);
        return flag;
    }

    @Override
    public int updateByPrimaryKey(Userinfo record) {
        int flag = userinfoMapper.updateByPrimaryKey(record);
        return flag;
    }
    //根据用户名获取用户信息
    @Override
    public Userinfo selectByPhone(String phone) {
        return userinfoMapper.selectByPhone(phone);
    }
    //根据用户名获取用户角色
	@Override
	public List<Integer> selectRid(String phone) {
		// TODO Auto-generated method stub
		return userinfoMapper.selectRid(phone);
	}
	//根据角色id获取角色权限
	@Override
	public List<String> selectPermissions(Integer rid) {
		// TODO Auto-generated method stub
		return userinfoMapper.selectPermissions(rid);
	}

	@Override
	public int updateLoginstateByPhone(HashMap map) {
		// TODO Auto-generated method stub
		return userinfoMapper.updateLoginstateByPhone(map);
	}
}
