package com.codegym.configuration;

import com.codegym.common.Constants;
import com.codegym.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.codegym.model.User;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private IUserService userServiceImpl;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        initDatabaseEntities();
    }


    private void initDatabaseEntities() {

        if( userServiceImpl.getAllUsers().size() == 0) {
            userServiceImpl.addNew(new User("Mr. Admin", "admin", "admin", Constants.ROLE_ADMIN));
            userServiceImpl.addNew(new User("Mr. Librarian", "librarian", "librarian", Constants.ROLE_LIBRARIAN));
        }
    }
}