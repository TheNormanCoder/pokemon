package com.pokemon.pokemon;

import com.pokemon.pokemon.com.pokemon.bean.PokemonBean;
import com.pokemon.pokemon.com.pokemon.controller.PokemonController;
import com.pokemon.pokemon.com.pokemon.service.PokemonService;

import org.aspectj.lang.annotation.Before;
import org.awaitility.Awaitility;
import org.awaitility.Duration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PokemonApplicationTests {

	@Autowired
	PokemonController controller;

	@Autowired
	PokemonService service;

	@PostConstruct
	@Transactional
	public  void init(){
		List<PokemonBean> pokemons =  service.findAllByName("pickachu");
		if(pokemons.size()>0){
		service.deleteAll(pokemons);

		}

		pokemons =  service.findAllByName("charizad");
		if(pokemons.size()==0){
			PokemonBean charizad = new PokemonBean();
			charizad.setName("charizad");
			charizad.setDescription("Charizard is a draconic, " +
					"bipedal Pokémon. It is primarily orange with " +
					"a cream underside from the chest to the tip of its tail. " +
					"It has a long neck, small blue eyes, slightly raised nostrils," +
					" and two horn-like structures protruding from the back of its rectangular head ");
			service.save(charizad);
		}



	}

	@Test
	void contextLoads() {
	}

	@Test
	void withExistingPokemonResponseIsOk(){
		 ResponseEntity<Object> resp =  controller.getPokemon("charizad");
		 assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void withNoexistingPockemnResponseIsNotFoud(){
		ResponseEntity<Object> resp =  controller.getPokemon("bob");
		assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	void addingNewPockemonResponseIsCreated(){
		PokemonBean bean = new PokemonBean();
		bean.setName("pickachu");
		bean.setDescription("Pikachu are small," +
				" mouse-like Pokemon that have short," +
				" yellow fur with brown markings covering their backs and parts of their tails. " +
				"They have black-tipped, " +
				"pointy ears and red circles on their cheeks, which are said to contain electrical sacs ");

		ResponseEntity<Object> resp =  controller.createPokemon(bean);
		Awaitility.await().pollDelay(Duration.TWO_SECONDS).until(() -> true);
		assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	@Test
	void addingNewDescriptionPockemonResponseIsUpdated(){
		PokemonBean bean = new PokemonBean();
		bean.setName("pickachu");
		bean.setDescription("Pikachu are small");

		ResponseEntity<Object> resp =  controller.updatePokemon(bean);
		Awaitility.await().pollDelay(Duration.TWO_SECONDS).until(() -> true);
		assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	@Test
	void deletingPockemonResponseIsRemoved(){
		ResponseEntity<Object> resp =  controller.deletePokemon("pikcachu");
		Awaitility.await().pollDelay(Duration.TWO_SECONDS).until(() -> true);
		assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}
}
