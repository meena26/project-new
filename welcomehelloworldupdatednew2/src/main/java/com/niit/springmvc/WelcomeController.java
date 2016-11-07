package com.niit.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
	public class WelcomeController {
	//home.htm
	@RequestMapping("/welcome")
	public ModelAndView sayHello(){
	     return  new ModelAndView("welcome","greet","greeting!Hello World");
	         

	}

}
