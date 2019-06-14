package com.mak.todo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.mak.todo.domain.User;
import com.mak.todo.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserService userService;

	@Before
	public void setUp() {
		List<User> users = new ArrayList<>();
		users.add(new User(1, "Alok"));
		users.add(new User(2, "Mak"));
		users.add(new User(3, "Tulsi"));

		when(userService.findAllUsers()).thenReturn(users);
	}

	@Test
	public void testRetrieveAllUsers() throws Exception {

		RequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/users")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk())
				.andExpect(content().json("[{id:1,name:Alok}," + "{id:2,name:Mak}," + "{id:3,name:Tulsi}]"))
				.andReturn();
		JSONAssert.assertEquals("[{id:1,name:Alok},{id:2,name:Mak},{id:3,name:Tulsi}]",
				result.getResponse().getContentAsString(), false);
		DocumentContext context = JsonPath.parse(result.getResponse().getContentAsString());
		int length = context.read("$.length()");
		assertThat(length).isEqualTo(3);
		List<Integer> ids = context.read("$..id");
		assertThat(ids).containsExactly(1, 2, 3);

	}

}
