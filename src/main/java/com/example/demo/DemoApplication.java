package com.example.demo;

import com.example.demo.Entities.Role;
import com.example.demo.Entities.User;
import com.example.demo.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


//	@Bean
//	CommandLineRunner run(UserService userService){
//		return args -> {
//			userService.saveRole(new Role(null,"ROLE_USER",new ArrayList<>()));
//			userService.saveRole(new Role(null,"ROLE_MANAGER",new ArrayList<>()));
//			userService.saveRole(new Role(null,"ROLE_ADMIN",new ArrayList<>()));
//
////			userService.saveUser(new User(null,"Rukshan","1234","male",new ArrayList<>()));
////			userService.saveUser(new User(null,"Dion","2222","male",new ArrayList<>()));
////			userService.saveUser(new User(null,"Fathima","3333","female",new ArrayList<>()));
////
////			userService.addRoleToUser("Rukshan","ROLE_USER");
////			userService.addRoleToUser("Dion","ROLE_ADMIN");
////			userService.addRoleToUser("Fathima","ROLE_MANAGER");
////
////			userService.addRoleToUser("Fathima","ROLE_USER");
////			userService.addRoleToUser("Dion","ROLE_USER");
////			userService.addRoleToUser("Dion","ROLE_MANAGER");
//		};
//	}

}
