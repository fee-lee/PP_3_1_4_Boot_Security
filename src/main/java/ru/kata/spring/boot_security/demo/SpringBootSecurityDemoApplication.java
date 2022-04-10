package ru.kata.spring.boot_security.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.sevice.UserService;

import java.util.Collections;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner initUser(UserService userService) {
//		return args -> {
//			if (userService.getAllUsers().size() < 1) {
//				User user = new User();
//				user.setName("admin");
//				user.setLastName("admin");
//				user.setPassword("admin");
//				user.setEmail("admin@mail");
//				user.setRoles(Collections.singleton(new Role("ROLE_ADMIN")));
//				userService.saveUsers(user);
//			}
//		};
//	}
}
