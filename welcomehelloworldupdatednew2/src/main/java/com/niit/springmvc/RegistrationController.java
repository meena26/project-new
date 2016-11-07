package com.niit.springmvc;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.BillingAddress;
import com.spring.model.Customer;
import com.spring.model.ShippingAddress;
import com.spring.model.Users;
import com.spring.services.CustomerServices;


@Controller
public class RegistrationController {
@Autowired
//dependency injection
private CustomerServices customerServices;



public CustomerServices getCustomerServices() {
	return customerServices;
}
public void setCustomerServices(CustomerServices customerServices) {
	this.customerServices = customerServices;
}
//To display registration form
	//when the user makes the request by the url  /customer/registration (get)
@RequestMapping("/customer/registration")
public ModelAndView getRegistrationForm(){
	Customer customer=new Customer();
	Users users=new Users();
	BillingAddress billingAddress=new BillingAddress();
	ShippingAddress shippingAddress=new ShippingAddress();
	
	customer.setUsers(users);
	customer.setBillingAddress(billingAddress);
	customer.setShippingAddress(shippingAddress);
	
	return new ModelAndView("registerCustomer","customer",customer);
	
}
//to insert the data 
@RequestMapping(value="/customer/registration",method=RequestMethod.POST)
public String registerCustomer(@Valid @ModelAttribute(value="customer")Customer customer,
		Model model,
		BindingResult result){
	if(result.hasErrors())
		return "registerCustomer";
	List <Customer> customerList=customerServices.getAllCustomers();
	for(Customer c:customerList){
		if(c.getUsers().getUsername().equals(customer.getUsers().getUsername()))
		{
			model.addAttribute("duplicateUname","Username already exists");
			return"registerCustomer";
		}
		if(c.getCustomerEmail().equals(customer.getCustomerEmail())){
			model.addAttribute("duplicateEmail","Email Id already exists");
			return"registerCustomer";
		}
	}

	
	customerServices.addCustomer(customer);
	model.addAttribute("registrationSuccess","Registered Successfully. Login using username and password");
	return "login";
}
}


