package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class PageController {

@Autowired
private UserService userService;

@RequestMapping("/")
public String index(){
    return "redirect:/home";
}
    // Home Route
@RequestMapping("/home")
public String home(Model model){
    System.out.println("Home page handler");

    // Sending data to view
    model.addAttribute("name", "Substring Technologies");
    model.addAttribute("nima", "Learn Spring Boot with nima");
return "home";
}
// About Route
@RequestMapping("/about")
public String aboutPage(Model model){
    model.addAttribute("isLogin",true);
    System.out.println("About page loading..");
    return "about";
}

// Services Route
@RequestMapping("/services")
public String servicesPage(){
    System.out.println("Service page loading");
    return "services";
}

// Contact Us Route
@RequestMapping("/contact")
public String contactPage(){
    System.out.println("Contact page loading");
    return "contact";

}

// This is showing login page
// Login Route
@RequestMapping("/login")
public String loginPage(){
    System.out.println("Login page loading");
    return "login";
}

// This is registration page
// Sign Up Route
@RequestMapping("/register")
public String signupPage(Model model){
    UserForm userForm = new UserForm();
    // userForm.setName("Tenzin Wangmo");
    // userForm.setAbout("All is Well");
    model.addAttribute("userForm", userForm);
    return "register";
  
}

// Processing register/signup
@RequestMapping(value = "/do-register", method = RequestMethod.POST)
public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session){
    System.out.println("Processing Data");
    // fetch form data -- UserForm
  
    System.out.println(userForm);

    // validate form data

    if(rBindingResult.hasErrors()){
        return "register";
    }

    // save to database - create userService
    // UserForm --> User
    // User user = User.builder()
    // .name(userForm.getName())
    // .email(userForm.getEmail())
    // .password(userForm.getPassword())
    // .about(userForm.getAbout())
    // .phoneNumber(userForm.getPhoneNumber())
    // .profilePic("https://www.google.com/url?sa=i&url=https%3A%2F%2Fservices.bhutan.travel%2Fsearch%2Ftour-operator%2Fatsara-tours&psig=AOvVaw1Cx0vVRKwFKyBMi2kMun6r&ust=1728108665842000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCNCj4s2I9IgDFQAAAAAdAAAAABAS")
    // .build();

    User user =new User();
    user.setName(userForm.getName());
    user.setEmail(userForm.getEmail());
    user.setPassword(userForm.getPassword());
    user.setAbout(userForm.getAbout());
    user.setPhoneNumber(userForm.getPhoneNumber());
    user.setProfilePic("https://www.google.com/url?sa=i&url=https%3A%2F%2Fservices.bhutan.travel%2Fsearch%2Ftour-operator%2Fatsara-tours&psig=AOvVaw1Cx0vVRKwFKyBMi2kMun6r&ust=1728108665842000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCNCj4s2I9IgDFQAAAAAdAAAAABAS");


   User savedUser =  userService.saveUser(user);
   System.out.println("user saved");
    // message = "Registration Successful"

  Message message =   Message.builder().content("Registration Successful").type(MessageType.green).build();
session.setAttribute("message", message);

    // redirect to login page
    return "redirect:/register";
}

}
