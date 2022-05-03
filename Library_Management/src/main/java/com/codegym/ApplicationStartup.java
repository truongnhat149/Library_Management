package com.codegym;

import com.codegym.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.codegym.model.User;
import com.codegym.service.impl.UserServiceImpl;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        initDatabaseEntities();
    }


    private void initDatabaseEntities() {

        if( userServiceImpl.getAllUsers().size() == 0) {
            userServiceImpl.addNew(new User("Mr. Admin", "admin", "admin", Constants.ROLE_ADMIN));
            userServiceImpl.addNew(new User("Mr. Librarian", "librarian", "librarian", Constants.ROLE_LIBRARIAN));
            userServiceImpl.addNew(new User("Mr. User", "user", "user", Constants.ROLE_USER));
        }

    }
}