package com.lq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lq.entity.SjxqYhgl;
import com.lq.service.UserService;


//ע��controller
@Controller
@RequestMapping("/page")
public class PageController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(SjxqYhgl user) {
		SjxqYhgl temp=userService.findByName(user.getYhm());
		if (temp==null) {
			System.out.println("û�и��û�");
			return "no user";
		}
		System.out.println("�����������룺"+user.getMm()+"  ���ݿ����룺"+temp.getMm());
		if (!temp.getMm().equals(user.getMm())) {
			System.out.println("�û������������");
			return "password wrong";
		}
		return "success";
	}
	@RequestMapping("/register")
	@ResponseBody
	public String register(SjxqYhgl user) {
		if (user.getYhm()==null||user.getYhm().equals("")
				||user.getMm()==null||user.getMm().equals("")	
					) {
				System.out.println("�û��������벻��Ϊ��");
				return "username or password can not be null";
			}
		SjxqYhgl temp=userService.findByName(user.getYhm());
		if (temp!=null) {
			System.out.println("�û��ѱ�ע��");
			return "this username has bean registered";
		}
		System.out.println(user.getYhm());
		if (userService.add(user)) {
			System.out.println("ע��ɹ�");
			return "success";
		}else {
			System.out.println("ע��ʧ��");
			return "failed";
		}
	}
}
