package com.kelly_ann.shoppingcartclient;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.kelly_ann.shoppingcart.ShoppingCartSession;

public class Main {
	
	public static void main(String[] args) throws NamingException {
		
		// create props
		Properties jndiProps = new Properties();
		
		// add jboss contexts to props
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		jndiProps.put("jboss.naming.client.ejb.context", true);
		
		// create context obj using initial context
		Context jndiContext = new InitialContext(jndiProps);
		
		// use JNDI to get an instance of the ShoppingCartSession
		ShoppingCartSession shoppingCart = (ShoppingCartSession)jndiContext.lookup("shoppingcart/ShoppingCartSessionImpl!com.kelly_ann.shoppingcart.ShoppingCartSession");
		
		// add items to shopping cart
		shoppingCart.addItem("Books");
		shoppingCart.addItem("Laptop");
		shoppingCart.addItem("Cell phone");
		
		
		List<String> items = shoppingCart.getCartContents();
		for(String c : items ) {
			System.out.println(c.toString());
		}
		
		shoppingCart.checkout();
	}
	
}
