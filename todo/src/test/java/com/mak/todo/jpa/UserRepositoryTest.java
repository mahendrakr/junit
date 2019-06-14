package com.mak.todo.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mak.todo.domain.User;
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	@Autowired
	private UserRepository repository;
	
	@Test
	public void testFindAllUser() {
		List<User> result = repository.findAll();	
		assertThat(result).hasSize(3);
		assertThat(result).anyMatch(i->i.getName().equals("Alok"));
	}
	@Test
	public void testSaveUser() {
		User user =new User(null, "Anuj");
	  repository.save(user);
	  List<User> result = repository.findAll();	
		assertThat(result).hasSize(4);
		assertThat(result).anyMatch(i->i.getName().equals("Anuj"));
	}

}
