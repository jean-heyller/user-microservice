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

	/*@Bean
	CommandLineRunner init(IUserRepository userRepository, IRolRepository rolRepository) {
		return (args) -> {
			RolEntity rolEntity = RolEntity.builder().
					name("ADMIN").
					description("Administrator")
					.build();

			// Guarda la entidad RolEntity en la base de datos
			rolEntity = rolRepository.save(rolEntity);

			UserEntity userEntity = UserEntity.builder().
					name("John").
					lastName("Doe").
					identification("123456789").
					email("jhon@hotmail.com").
					password("$2a$10$Oa7fcVim9W6VeJmWfD/1be0qeh5N77rJBD6DKQx.VkM1HivNjIaOK").
					isEnabled(true).
					isAccountNonExpired(true).
					isAccountNonLocked(true).
					isCredentialsNonExpired(true).
					rol(rolEntity) // Ahora rolEntity es una entidad persistente
					.build();

			userRepository.save(userEntity);
		};
	}*/

}
