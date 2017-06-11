package cn.itcast.ssm.mapper;

import java.util.List;

import cn.itcast.ssm.po.UsersCustom;
import cn.itcast.ssm.po.UsersQueryVo;

public interface UserMapperCustom
{
	
	public List<UsersCustom> findUsersList(UsersQueryVo usersQueryVo) throws Exception;

}
