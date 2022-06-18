package com.pokemoncardmarkt.pokemoncardmarkt_backend;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.controller.CardController;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.Expansion;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.PokemonCard;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.repository.PokemonCardRepository;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.services.CardService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
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
        PokemonCard definedCard = new PokemonCard(1, "Pikachu", 20, PokemonTypes.electric, expansion, new ArrayList<>());


        // act
        when(cardRepo.findById(1L)).thenReturn(Optional.of(definedCard));
        foundCard = controller.GetCardById(1L);

        // assert
        Assert.assertEquals(definedCard, foundCard);
    }

    @Test
    void GetAllCardsTest(){
        // arrange
        PokemonCardRepository cardRepo = mock(PokemonCardRepository.class);
        CardService cardService = new CardService(cardRepo);
        CardController cardController = new CardController(cardService);
        List<PokemonCard> foundCards;

        List<PokemonCard> testCardList = new ArrayList<>();
        testCardList.add(new PokemonCard(1, "Pikachu", 20, PokemonTypes.electric, new Expansion(1), new ArrayList<>()));
        testCardList.add(new PokemonCard(2, "Bidoof", 100, PokemonTypes.normal, new Expansion(4), new ArrayList<>()));
        testCardList.add(new PokemonCard(3, "Shinx", 30, PokemonTypes.electric, new Expansion(3), new ArrayList<>()));

        // act
        when(cardRepo.findAll()).thenReturn(testCardList);
        foundCards = cardController.GetAllCards();

        // assert
        Assert.assertEquals(testCardList.size(), testCardList.size());

        for(int i = 0; i < testCardList.size(); i++){
            Assert.assertEquals(testCardList.get(i), foundCards.get(i));
        }
    }

    @Test
    void CreateCardTest(){
        // arrange
        PokemonCardRepository cardRepo = mock(PokemonCardRepository.class);
        CardService cardService = new CardService(cardRepo);
        CardController cardController = new CardController(cardService);
        PokemonCard testCard = new PokemonCard(3, "Pichu", 20, PokemonTypes.electric, new Expansion(1), new ArrayList<>());

        // act
        when(cardRepo.save(testCard)).thenReturn(testCard);
        PokemonCard receivedCard =  cardController.CreateCard(testCard);

        // assert
        Assert.assertNotNull(receivedCard);

        Assert.assertEquals(testCard.getId(), receivedCard.getId());
        Assert.assertEquals(testCard.getName(), receivedCard.getName());
        Assert.assertEquals(testCard.getHealthPoints(), receivedCard.getHealthPoints());
        Assert.assertEquals(testCard.getType(), receivedCard.getType());
        Assert.assertEquals(testCard.getExpansion(), receivedCard.getExpansion());
    }

}
