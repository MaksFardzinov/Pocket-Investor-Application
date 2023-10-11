package com.example.FirstApp;

import com.example.FirstApp.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FirstAppApplicationTests {
	/*@Autowired
	private UserService service;
	@Test
	void addUser() {
		User user = new User();
		user.setUsername("maksf");
		user.setFirstname("Максим");
		user.setLastname("Фардзинов");
		user.setEmail("maksfardzinov@bk.ru");
		user.setPassword("root");
		service.save_user(user);
	}

	@Test
	void deleteUser(){
		List<User> users = service.get_users();
		for (User user : users) {
			if (user.getUsername().equals("maksf")) {
				service.delete_user(user);
			}
		}}*/
}
