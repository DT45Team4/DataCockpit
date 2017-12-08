/**
 * Project Name:DataCockpit
 * File Name:RelevanceTableServiceImpl.java
 * Package Name:cn.bdqn.datacockpit.service.impl
 * Date:2017年8月29日上午9:49:05
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */
/**
 * 
 */

package cn.bdqn.datacockpit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.bdqn.datacockpit.mapper.RelevanceTable;
import cn.bdqn.datacockpit.mapper.RelevanceTableMapper;
import cn.bdqn.datacockpit.service.RelevanceTableService;

/**
 * Description: <br/>
 * Date: 2017年8月29日 上午9:49:05 <br/>
 * 
 * @author jiaoHJ
 * @version
 * @see
 */
@Service
public class RelevanceTableServiceImpl implements RelevanceTableService {
    private RelevanceTableMapper rtm = new RelevanceTable();

	@Override
	public List<String> selectAll(String name1, int i, String name2, int j)
			throws Exception {
		// TODO Auto-generated method stub
		return rtm.selectAll(name1, i, name2, j);
	}

	@Override
	public HashMap<Integer, Object> selectallname(String name)throws Exception  {
		// TODO Auto-generated method stub
		return rtm.selectallname(name);
	}


}
