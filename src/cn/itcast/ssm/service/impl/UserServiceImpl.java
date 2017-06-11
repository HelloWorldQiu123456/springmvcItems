package cn.itcast.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.ssm.mapper.UserMapperCustom;
import cn.itcast.ssm.po.UsersCustom;
import cn.itcast.ssm.po.UsersQueryVo;
import cn.itcast.ssm.service.UserService;

public class UserServiceImpl implements UserService
{
	@Autowired
	private UserMapperCustom userMapperCustom;

	@Override
	public List<UsersCustom> findUsersList(UsersQueryVo usersQueryVo) throws Exception
	{
		
		return userMapperCustom.findUsersList(usersQueryVo);
	}

	
	

	
	

}
