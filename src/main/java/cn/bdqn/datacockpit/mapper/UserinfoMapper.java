package cn.bdqn.datacockpit.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import cn.bdqn.datacockpit.entity.Userinfo;

public interface UserinfoMapper {

    List<Userinfo> selectAllUserinfo();

    int deleteByPrimaryKey(Integer id);

    int insert(Userinfo record);
    //添加管理员账号
    int insertSelective(Userinfo record);
    int insertAdminRole(Userinfo record);
    //修改userinfo密码
    int updatePassword(HashMap map);
    Userinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);
    
    int insertByprimaryKey(int id);
    
    /**
     * shiro通过电话号查询用户
     * @param userName
     * @return
     */
    public Userinfo selectByPhone(String phone);
    //修改用户登录状态
    public int updateLoginstateByPhone(HashMap map);
    /**
     * shiro通过电话号查询角色信息
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
