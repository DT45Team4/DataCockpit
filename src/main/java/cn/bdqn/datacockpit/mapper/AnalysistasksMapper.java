package cn.bdqn.datacockpit.mapper;

import java.util.HashMap;
import java.util.List;

import cn.bdqn.datacockpit.entity.Analysistasks;

public interface AnalysistasksMapper {
    List<Analysistasks> selectAllTasks(Integer id);
    
    int deleteByPrimaryKey(Integer id);

    int insert(Analysistasks record);

    int insertSelective(Analysistasks record);

    Analysistasks selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Analysistasks record);

    int updateByPrimaryKey(Analysistasks record);
    
    List<Analysistasks> selectdataBycid(Integer cid);
    
    int insertanalysistasksbyid(Analysistasks as);
    
    List<HashMap> selectALLanalysistasksbydid(int id);
    
    List<HashMap> selectALLAnalysistasks();
    
}