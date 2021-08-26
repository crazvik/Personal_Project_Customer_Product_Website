package com.hobby.webApp;

import com.hobby.webApp.entities.ActivationKey;
import com.hobby.webApp.entities.User;
import com.hobby.webApp.repositories.UserRepo;
import com.hobby.webApp.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;

@SpringBootApplication
@EnableMongoRepositories
public class WebAppApplication implements CommandLineRunner {
	@Autowired
	private UserServiceImpl userService;

	public static void main(String[] args) {
		SpringApplication.run(WebAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ArrayList<ActivationKey> keys = new ArrayList<>();
		keys.add(new ActivationKey("1234abcd"));
		userService.register("Name", "LastName", 30, "email@email.com", "123sendhelp", keys);
	}
}
