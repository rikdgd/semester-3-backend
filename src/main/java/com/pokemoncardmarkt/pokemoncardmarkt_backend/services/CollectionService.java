package com.pokemoncardmarkt.pokemoncardmarkt_backend.services;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.CardCollection;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.CollectionCardPair;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.PokemonCard;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.AppUser;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CardService cardService;

    public CardCollection GetById(long id){
        return collectionRepository.findById(id).orElseThrow();
    }

    public CardCollection GetByUserId(long userId){
        AppUser foundAppUser = userService.GetUserById(userId);
        return collectionRepository.findCardCollectionByAppUser(foundAppUser);
    }

    public CardCollection CreateCollection(long userId){
        AppUser requestedAppUser = userService.GetUserById(userId);
        CardCollection newCardCollection = new CardCollection(requestedAppUser);
        return collectionRepository.save(newCardCollection);
    }

    public CardCollection CreateCollectionByUsername(String username){
        AppUser requestedAppUser = userService.GetUserByName(username);
        CardCollection newCardCollection = new CardCollection(requestedAppUser);
        return collectionRepository.save(newCardCollection);
    }

    public CardCollection AddCard(CollectionCardPair collectionCardPair){
        CardCollection cardCollection = collectionCardPair.getCollection();
        PokemonCard pokemonCard = collectionCardPair.getCard();

        cardCollection.getCards().add(pokemonCard);
        return collectionRepository.save(cardCollection);
    }

    public CardCollection RemoveCard(CollectionCardPair collectionCardPair){
        CardCollection cardCollection = collectionCardPair.getCollection();
        PokemonCard pokemonCard = collectionCardPair.getCard();

        cardCollection.getCards().remove(pokemonCard);
        return collectionRepository.save(cardCollection);
    }
}
