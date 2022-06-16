package com.project.cards;

public class CardEntity {

    private int cardNo;
    private int cardBalance;
    private int cardPass;
    private int peopleLId;
    private String cardReal;

    public int getCardNo() {
        return cardNo;
    }

    public CardEntity setCardNo(int cardNo) {
        this.cardNo = cardNo;
        return this;
    }

    public int getCardBalance() {
        return cardBalance;
    }

    public CardEntity setCardBalance(int cardBalance) {
        this.cardBalance = cardBalance;
        return this;
    }

    public int getCardPass() {
        return cardPass;
    }

    public CardEntity setCardPass(int cardPass) {
        this.cardPass = cardPass;
        return this;
    }

    public int getPeopleLId() {
        return peopleLId;
    }

    public CardEntity setPeopleLId(int peopleLId) {
        this.peopleLId = peopleLId;
        return this;
    }

    public String getCardReal() {
        return cardReal;
    }

    public CardEntity setCardReal(String cardReal) {
        this.cardReal = cardReal;
        return this;
    }
}
