package cn.itcast.ssm.service;


import java.util.List;


import cn.itcast.ssm.po.UsersCustom;
import cn.itcast.ssm.po.UsersQueryVo;

public interface UserService
{
	public List<UsersCustom> findUsersList(UsersQueryVo usersQueryVo) throws Exception;
	
	
	

}
