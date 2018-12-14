package com.javaee.artastic.Artastic.config.websocket;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestListener implements ServletRequestListener{
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
	    //将所有request请求都携带上httpSession
	    HttpSession httpSession= ((HttpServletRequest) sre.getServletRequest()).getSession();
	    System.out.println("将所有request请求都携带上httpSession " + httpSession.getId());
	}

	public RequestListener() {
		
	}

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
	  
	}

}
