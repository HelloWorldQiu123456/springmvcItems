package cn.itcast.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.ssm.po.UsersCustom;
import cn.itcast.ssm.service.UserService;

@Controller
public class UserController
{
	@Autowired
	private UserService userService;
	
	@RequestMapping("/queryUsers")
	public ModelAndView queryUsers() throws Exception{
		
	List<UsersCustom>  usersList=userService.findUsersList(null);
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject("usersList",usersList);
	modelAndView.setViewName("items/userList");
	return modelAndView;
	
	}
}
