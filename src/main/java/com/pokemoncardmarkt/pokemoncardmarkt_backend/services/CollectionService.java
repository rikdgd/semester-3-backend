package com.pokemoncardmarkt.pokemoncardmarkt_backend.services;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.CardCollection;
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
        return collectionRepository.findCollectionByUser(foundAppUser);
    }

    public CardCollection CreateCollection(long userId){
        AppUser requestedAppUser = userService.GetUserById(userId);
        CardCollection newCardCollection = new CardCollection(requestedAppUser);
        return collectionRepository.save(newCardCollection);
    }

    public CardCollection AddCardById(long collectionId, long cardId){
        PokemonCard foundCard = cardService.GetCardById(cardId);
        CardCollection targetCardCollection = collectionRepository.findById(collectionId).orElseThrow();

        List<PokemonCard> collectionCards = targetCardCollection.getCards();
        collectionCards.add(foundCard);
        targetCardCollection.setCards(collectionCards);

        return collectionRepository.save(targetCardCollection);
    }

    public CardCollection RemoveCardById(long collectionId, long cardId){
        PokemonCard foundCard = cardService.GetCardById(cardId);
        CardCollection targetCardCollection = collectionRepository.findById(collectionId).orElseThrow();

        List<PokemonCard> collectionCards = targetCardCollection.getCards();
        collectionCards.remove(foundCard);
        targetCardCollection.setCards(collectionCards);

        return collectionRepository.save(targetCardCollection);
    }
}
