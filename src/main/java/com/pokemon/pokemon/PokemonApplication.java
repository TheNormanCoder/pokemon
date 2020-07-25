package com.pokemon.pokemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PokemonApplication extends SpringBootServletInitializer {

		public static void main(String[] args) {
				SpringApplication.run(PokemonApplication.class, args);
		}


		@Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
				return builder.sources(PokemonApplication.class);
		}

}

/*public class PokemonApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonApplication.class, args);
	}

}*/
