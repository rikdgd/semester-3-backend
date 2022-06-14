package com.pokemoncardmarkt.pokemoncardmarkt_backend;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.controller.CardController;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.Expansion;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.PokemonCard;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.repository.PokemonCardRepository;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.services.CardService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PokemonCardMarktBackEndApplicationTests {

    @Test
    void GetCardTest() {
        // arrange
        PokemonCardRepository cardRepo = mock(PokemonCardRepository.class);
        CardService cardService = new CardService(cardRepo);
        CardController controller = new CardController(cardService);
        PokemonCard foundCard;

        Expansion expansion = new Expansion();
        PokemonCard definedCard = new PokemonCard(null, "Pikachu", 20, PokemonTypes.electric, expansion, null);


        // act
        when(cardRepo.findById(1L)).thenReturn(Optional.of(definedCard));
        foundCard = controller.GetCardById(1L);

        // assert
        Assert.assertEquals(definedCard, foundCard);
    }

}
