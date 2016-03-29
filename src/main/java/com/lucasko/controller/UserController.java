package com.lucasko.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lucasko.model.Count;
import com.lucasko.model.User;
import com.lucasko.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userserivce;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("index");

		List<User> users = userserivce.getUsers();
		model.addObject("users", users);
		return model;
	}

	@RequestMapping(value = { "/add"  }, method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request) {
		return new ModelAndView("add") ;
	}
	
	@RequestMapping(value = { "/update"  }, method = RequestMethod.GET)
	public ModelAndView update(HttpServletRequest request , @RequestParam String username) {
		ModelAndView model = new ModelAndView("update");
		model.addObject("user",userserivce.getUser(username));
		return model ;
	}
	
	
	@RequestMapping(value = { "/add", "/update" }, method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String update(HttpServletRequest request, @ModelAttribute("user") User user) {
		userserivce.addOrUpdate(user);
		return new JSONObject().put("msg", "ok").toString();
	}
	

	@RequestMapping(value = { "/delete" }, method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String delete(HttpServletRequest request, @RequestParam String username) {
		int delete = userserivce.deleteUser(username);
		return new JSONObject().put("msg", delete).toString();
	}
	
	@RequestMapping(value = {"/count" }, method = RequestMethod.GET)
	public ModelAndView count(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("count");

		List<Count> counts = userserivce.getCount();
		
		
		model.addObject("counts", counts);
		return model;
	}

}
