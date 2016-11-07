package com.niit.springmvc;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.spring.model.Book;
import com.spring.model.Cart;
import com.spring.model.CartItem;
import com.spring.model.Customer;
import com.spring.services.BookServices;
import com.spring.services.CartItemService;
import com.spring.services.CartService;
import com.spring.services.CustomerServices;



@Controller
public class CartItemController {
	//it needs CartItemService
	@Autowired	
	private CustomerServices customerServices;
	@Autowired
	private BookServices bookServices;

@Autowired
private CartItemService cartItemService;
@Autowired
private  CartService cartservice;

public CartService getCartservice() {
	return cartservice;
}


public void setCartservice(CartService cartservice) {
	this.cartservice = cartservice;
}


public CartItemService getCartItemService() {
	return cartItemService;
}


public void setCartItemService(CartItemService cartItemService) {
	this.cartItemService = cartItemService;
}


public CustomerServices getCustomerServices() {
	return customerServices;
}


public void setCustomerServices(CustomerServices customerServices) {
	this.customerServices = customerServices;
}


public BookServices getBookServices() {
	return bookServices;
}


public void setBookServices(BookServices bookServices) {
	this.bookServices = bookServices;
}

@RequestMapping("/cart/add/{isbn}")
@ResponseStatus(value=HttpStatus.NO_CONTENT)
public void addItem(@PathVariable(value = "isbn") int isbn){
	//Is to get the username of the customer
	//User object contains details about the user -username , password, activeuser or not
	User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	String username=user.getUsername();
	Customer customer=customerServices.getCustomerByUsername(username);
	System.out.println("Customer is " + customer.getCustomerEmail() );
	Cart cart=customer.getCart();
	
	List<CartItem>cartItems=cart.getCartItems();
	Book book=bookServices.getBookByIsbn(isbn);
	for(int i=0;i<cartItems.size();i++){
		CartItem  cartItem=cartItems.get(i);
		if(book.getIsbn()==cartItem.getBook().getIsbn()){
			cartItem.setQuantity(cartItem.getQuantity() + 1);
			cartItem.setTotalPrice(cartItem.getQuantity() * cartItem.getBook().getPrice());
			cartItemService.addCartItem(cartItem);
			return;
		}
	}
	CartItem cartItem=new CartItem();
	cartItem.setQuantity(1);
	cartItem.setBook(book);
	cartItem.setTotalPrice(book.getPrice() * 1);
	cartItem.setCart(cart);
	cartItemService.addCartItem(cartItem);
	
	
}

@RequestMapping("/cart/removecartitem/{cartItemId}")
@ResponseStatus(value=HttpStatus.NO_CONTENT)
public void removeCartItem(
	@PathVariable(value="cartItemId") int cartItemId){
	cartItemService.removeCartItem(cartItemId);
}
@RequestMapping("/cart/removeAllItems/{cartId}")
@ResponseStatus(value=HttpStatus.NO_CONTENT)
public void removeAllCartItems(@PathVariable(value="cartId") int cartId){
	Cart cart=cartservice.getCartByCartId(cartId);

	cartItemService.removeAllCartItems(cart);
}

//@RequestMapping("/cart/removeAllItems/{cartId}")
//@ResponseStatus(value=HttpStatus.NO_CONTENT)
//public void removeAllCartItems(@PathVariable(value="cartId") int cartId){
//	Cart cart=cartService.getCartByCartId(cartId);
	//cartItemService.removeAllCartItems(cart);
}







