package com.example.usermicroservice;

import com.example.usermicroservice.adapters.driven.jpa.mysql.entity.RolEntity;
import com.example.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IRolRepository;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMicroserviceApplication.class, args);
	}

	@Bean
	CommandLineRunner init(IUserRepository userRepository, IRolRepository rolRepository) {
		return (args) -> {
			String[] roles = {"ADMIN", "STUDENT", "TUTOR"};
			for (String role : roles) {
				RolEntity rolEntity = RolEntity.builder()
						.name(role)
						.description(role + " role")
						.build();
				rolEntity = rolRepository.save(rolEntity);

				for (int i = 1; i <= 4; i++) {
					UserEntity userEntity = UserEntity.builder()
							.name(role + "User" + i)
							.lastName(role + "LastName" + i)
							.identification(role + "Id" + i)
							.email(role.toLowerCase() + "user" + i + "@hotmail.com")
							.password("$2a$10$Oa7fcVim9W6VeJmWfD/1be0qeh5N77rJBD6DKQx.VkM1HivNjIaOK")
							.isEnabled(true)
							.isAccountNonExpired(true)
							.isAccountNonLocked(true)
							.isCredentialsNonExpired(true)
							.rol(rolEntity)
							.build();
					userRepository.save(userEntity);
				}
			}
		};
	}


}
