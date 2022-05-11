package com.pokemoncardmarkt.pokemoncardmarkt_backend;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.controller.CardController;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.repository.PokemonCardRepository;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.services.CardService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;

@SpringBootTest
class PokemonCardMarktBackEndApplicationTests {

    @Test
    void GetCardTest() {
        // arrange
        PokemonCardRepository cardRepo = mock(PokemonCardRepository.class);
        CardService cardService = new CardService();
        CardController controller = new CardController(cardService);

        // act


        // assert

    }

}
