/**
 * Project Name:DataCockpit
 * File Name:TableinfoServiceImpl.java
 * Package Name:cn.bdqn.datacockpit.service.impl
 * Date:2017年8月25日上午10:53:43
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.bdqn.datacockpit.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bdqn.datacockpit.entity.Datarelation;
import cn.bdqn.datacockpit.entity.Tableinfo;
import cn.bdqn.datacockpit.entity.Tablerelation2;
import cn.bdqn.datacockpit.mapper.TableinfoMapper;
import cn.bdqn.datacockpit.service.TableinfoService;

/**
 * Description: <br/>
 * Date: 2017年8月25日 上午10:53:43 <br/>
 * 
 * @author yuanX
 * @version
 * @see
 */
@Service
public class TableinfoServiceImpl implements TableinfoService {
    @Autowired
    private TableinfoMapper tm;

    @Override
    public List<Tableinfo> selectAll(Integer id) {

        // Auto-generated method stub
        return tm.selectAll(id);
    }

    @Override
    public int insert(Tableinfo record) {

        return tm.insert(record);
    }


	@Override
	public List<Tableinfo> selecttableByName(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return tm.selecttableByName(map);
	}

	@Override
	public List<Tablerelation2> selecttablerelation(Integer cid) {
		// TODO Auto-generated method stub
		return tm.selecttablerelation(cid);
	}

	@Override
	public List<Tableinfo> selectallbyid(Integer cid) {
		// TODO Auto-generated method stub
		return tm.selectallbyid(cid);
	}

	@Override
	public String selectnamebyid(Integer id) {
		// TODO Auto-generated method stub
		return tm.selectnamebyid(id);
	}

	@Override
	public int updaterestate(HashMap map) {
		// TODO Auto-generated method stub
		return tm.updaterestate(map);
	}

	@Override
	public List<Datarelation> listreonlyone(HashMap map) {
		// TODO Auto-generated method stub
		return tm.listreonlyone(map);
	}

	@Override
	public int myinsert(HashMap map) {
		// TODO Auto-generated method stub
		return tm.myinsert(map);
	}

	@Override
	public List<Datarelation> selectstate(HashMap map) {
		// TODO Auto-generated method stub
		return tm.selectstate(map);
	}

	@Override
	public List<Datarelation> selectname(HashMap map) {
		// TODO Auto-generated method stub
		return tm.selectname(map);
	}

	
	
    
	
}
