package cn.itcast.ssm.po;

import java.util.List;

public class UsersQueryVo
{
   private User users;
	
	
	private UsersCustom usersCustom;
	
	
	private List<UsersCustom> usersList;


	public User getUsers()
	{
		return users;
	}


	public void setUsers(User users)
	{
		this.users = users;
	}


	public UsersCustom getUsersCustom()
	{
		return usersCustom;
	}


	public void setUsersCustom(UsersCustom usersCustom)
	{
		this.usersCustom = usersCustom;
	}


	public List<UsersCustom> getUsersList()
	{
		return usersList;
	}


	public void setUsersList(List<UsersCustom> usersList)
	{
		this.usersList = usersList;
	}
	
	

}
