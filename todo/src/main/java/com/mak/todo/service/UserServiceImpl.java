package com.mak.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mak.todo.domain.User;
import com.mak.todo.jpa.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;

	@Override
	public List<User> findAllUsers() {
		return repository.findAll();
	}

	@Override
	public Optional<User> findUserById(int id) {
		return repository.findById(id);
	}

	@Override
	public void deleteUser(int id) {
		repository.deleteById(id);

	}

	@Override
	public User save(User user) {
		return repository.save(user);
	}

}
