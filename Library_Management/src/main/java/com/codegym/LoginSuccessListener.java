package com.codegym;

import javax.servlet.http.HttpSession;

import com.codegym.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private HttpSession httpSession;
	
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
        User user = (User) event.getAuthentication().getPrincipal();
        String displayName = userServiceImpl.getByUsername( user.getUsername() ).getDisplayName();
        httpSession.setAttribute("loggedInUserName", displayName);
	}
	
}
