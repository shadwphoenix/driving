package com.project.services;

import com.project.cards.CardService;

import java.sql.SQLException;
import java.util.List;

public class CityServicesServ {

    private int cityCode;
    private int cityPeopleId;
    private int cityCost;

    private CityServicesServ(){}

    private static CityServicesServ cityServicesServ = new CityServicesServ();

    public static CityServicesServ getInstance() {
        return cityServicesServ;
    }

    public List<CityServiceEntity> show() throws Exception {
        List<CityServiceEntity> cityServiceEntities;
        try (CityServiceRepository cityServiceRepository = new CityServiceRepository()) {
            cityServiceEntities = cityServiceRepository.showCity();
        }
        return cityServiceEntities;
    }

    public void insert(String plateNo,int carNo,int cardNo, int password) throws Exception {
        try(CityServiceRepository cityServiceRepository = new CityServiceRepository()){
            cityServiceRepository.getCityCost();
            if(CardService.cardService.check(cardNo,password,cityCost)) {
                CityServiceEntity cityServiceEntity = new CityServiceEntity();
                cityServiceEntity.setPlateNo(plateNo);
                cityServiceEntity.setCarNo(carNo);
                cityServiceEntity.setServiceCode(cityCode);
                cityServiceEntity.setPeopleId(cityPeopleId);
                cityServiceRepository.insertCityService(cityServiceEntity);
            }
        }
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getCityPeopleId() {
        return cityPeopleId;
    }

    public void setCityPeopleId(int cityPeopleId) {
        this.cityPeopleId = cityPeopleId;
    }

    public int getCityCost() {
        return cityCost;
    }

    public void setCityCost(int cityCost) {
        this.cityCost = cityCost;
    }
}
