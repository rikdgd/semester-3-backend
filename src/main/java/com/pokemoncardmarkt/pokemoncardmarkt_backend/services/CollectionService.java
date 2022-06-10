package com.pokemoncardmarkt.pokemoncardmarkt_backend.services;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.Collection;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.PokemonCard;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.User;
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

    public Collection GetById(long id){
        return collectionRepository.findById(id).orElseThrow();
    }

    public Collection GetByUserId(long userId){
        User foundUser = userService.GetUserById(userId);
        return collectionRepository.findCollectionByUser(foundUser);
    }

    public Collection CreateCollection(long userId){
        User requestedUser = userService.GetUserById(userId);
        Collection newCollection = new Collection(requestedUser);
        return collectionRepository.save(newCollection);
    }

    public Collection AddCardById(long collectionId, long cardId){
        PokemonCard foundCard = cardService.GetCardById(cardId);
        Collection targetCollection = collectionRepository.findById(collectionId).orElseThrow();

        List<PokemonCard> collectionCards = targetCollection.getCards();
        collectionCards.add(foundCard);
        targetCollection.setCards(collectionCards);

        return collectionRepository.save(targetCollection);
    }

    public Collection RemoveCardById(long collectionId, long cardId){
        PokemonCard foundCard = cardService.GetCardById(cardId);
        Collection targetCollection = collectionRepository.findById(collectionId).orElseThrow();

        List<PokemonCard> collectionCards = targetCollection.getCards();
        collectionCards.remove(foundCard);
        targetCollection.setCards(collectionCards);

        return collectionRepository.save(targetCollection);
    }
}
