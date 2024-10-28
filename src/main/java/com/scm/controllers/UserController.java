package com.scm.controllers;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.helpers.Helper;
import com.scm.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;



@Controller
@RequestMapping("/user")
public class UserController {
  private Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

 

  // user dashboard page
  @RequestMapping(value = "/dashboard")
  public String userDashboard() {
    return "user/dashboard";
  }

  // User profile page
  @RequestMapping(value = "/profile")
  public String userProfile(Model model, Authentication authentication) {
    return "user/profile";
  }

  // user add contacts page
  // user view contacts
  // user edit contact
  // user delete contact
  // user search contact

}
