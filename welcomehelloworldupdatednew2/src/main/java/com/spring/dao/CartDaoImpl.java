package com.spring.dao;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.Cart;
import com.spring.services.CustomerOrderService;
@Repository
public class CartDaoImpl implements CartDao{
	
	@Autowired
	CustomerOrderService customerOrderService;
	
	@Autowired
private SessionFactory sessionFactory;
	
	

	public SessionFactory getSessionFactory() {
	return sessionFactory;
}
public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}
public Cart getCartByCartId(int cartId) {
		Session session=sessionFactory.openSession();
		Cart cart=(Cart)session.get(Cart.class, cartId);
		System.out.println(cart.getCartId() + " " + cart.getCartItems());
		return cart;
}
public void update(Cart cart){
    int cartId = cart.getCartId();
   
    double grandTotal = customerOrderService.getCustomerOrderGrandTotal(cartId);
    cart.setGrandTotal(grandTotal);

    Session session = sessionFactory.openSession();
    session.saveOrUpdate(cart);
    session.flush();
    session.close();
}

public Cart validate(int cartId) throws IOException{
    Cart cart = getCartByCartId(cartId);
    if(cart == null || cart.getCartItems().size() == 0){
        throw new IOException(cartId + "");
    }

    update(cart);
    return cart;
}

}


