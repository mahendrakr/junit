package com.mak.todo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mak.todo.domain.User;
import com.mak.todo.jpa.UserRepository;
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
@InjectMocks
private UserServiceImpl service;
@Mock
private UserRepository repository;
@Before
public void setUp() {
	List<User> users =new ArrayList<>();
	users.add(new User(1, "Alok"));
	users.add(new User(2, "Mak"));
	users.add(new User(3, "Tulsi"));
	Optional<User> user = Optional.of(new User(1, "Alok"));
	when(repository.findAll()).thenReturn(users);
	when(repository.findById(1)).thenReturn(user);
}
	@Test
	public void testFindAllUsers() {
		List<User> result = service.findAllUsers();
		assertThat(result).hasSize(3);
	}

	@Test
	public void testFindUserById() {
		Optional<User> user = service.findUserById(1);
		assertThat(user.get()).hasFieldOrProperty("name");
	}

	@Test
	public void testDeleteUser() {
	}

	@Test
	public void testSave() {
	}

}
