package com.aniview.aniview;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class AniviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(AniviewApplication.class, args);
	}

	@Bean
	public CommandLineRunner testDatabaseConnection(DataSource dataSource) {
		return args -> {
			try (Connection connection = dataSource.getConnection()) {
				if (connection.isValid(1)) {
					System.out.println("Conexión a la base de datos exitosa!");
				} else {
					System.out.println("Fallo en la conexión a la base de datos.");
				}
			} catch (SQLException e) {
				System.out.println("Error al conectar a la base de datos: " + e.getMessage());
			}
		};
	}
}
