package com.mak.todo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mak.todo.domain.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
