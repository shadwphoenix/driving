package com.project.cards;


import Exceptions.BalanceLowException;
import Exceptions.UserDoesNotMatchException;
import Exceptions.UserNotFoundException;
import com.project.people.PeopleEntity;

import java.sql.SQLException;

public class CardService {

    private CardService(){}

    public static CardService cardService = new CardService();

    public static CardService getInstance(){
        return cardService;
    }


    public boolean check(int cardNo,int password,int amount) throws Exception {
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardNo(cardNo);
        try (CardRepository cardRepository = new CardRepository()){
            cardRepository.getInfoCard(cardEntity);
            if(cardNull(cardEntity))
                if (passwordMatch(cardEntity,password))
                    if (balanceCheck(cardEntity,amount))
                        return true;
        }
        return false;
    }

    public int balance(int cardNo) throws Exception {
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardNo(cardNo);
        try (CardRepository cardRepository = new CardRepository()){
            cardRepository.getInfoCard(cardEntity);
            return cardEntity.getCardBalance();
        }
    }

    public void update(int cardNo,int balance) throws Exception {
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardNo(cardNo).setCardBalance(balance);
        try (CardRepository cardRepository = new CardRepository()){
            cardRepository.updateCardInfo(cardEntity);
            cardRepository.commit();
        }
    }

    public boolean cardNull(CardEntity cardEntity) throws UserNotFoundException{
        if(cardEntity.getCardReal() == null){
            throw new UserNotFoundException("Card not valid");
        }
        else {
            return true;
        }
    }

    public boolean passwordMatch(CardEntity cardEntity, int password) throws UserDoesNotMatchException{
        if (cardEntity.getCardPass() != password){
            throw new UserDoesNotMatchException("Password is incorrect");
        }
        else{
            return true;
        }
    }

    public boolean balanceCheck(CardEntity cardEntity,int amount) throws BalanceLowException {
        if(cardEntity.getCardBalance() <= amount){
            throw new BalanceLowException("Balance too low");
        }
        else {
            return true;
        }
    }

}
