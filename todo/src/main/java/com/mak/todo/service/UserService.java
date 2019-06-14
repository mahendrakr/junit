package com.mak.todo.service;

import java.util.List;
import java.util.Optional;

import com.mak.todo.domain.User;

public interface UserService {
public List<User> findAllUsers();
public Optional<User> findUserById(int id);
public void deleteUser(int id) ;
public User save(User user);

}
