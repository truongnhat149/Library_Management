package com.codegym.service;

import com.codegym.model.User;
import java.util.List;

public interface IUserService {

     List<User> getAllUsers();

     List<User> getAllActiveUsers();

     User getByUsername(String username);

     User getById(Long id);

     User addNew(User user);

     User update(User user);

     void delete(User user);

     void delete(Long id);
}
