package com.project.road;

import com.project.cards.CardService;

import java.util.List;

public class RoadServiceService {

    private RoadServiceService(){}

    private int roadCode;
    private int roadPeopleId;
    private int roadCost;

    private static RoadServiceService roadServiceService = new RoadServiceService();

    public static RoadServiceService getInstance(){
        return roadServiceService;
    }

    public List<RoadServiceEntity> show() throws Exception {
        List<RoadServiceEntity> services;
        try (RoadServiceRepository roadServiceRepository = new RoadServiceRepository()){
            services = roadServiceRepository.showRoadServices();
        }
        return services;
    }

    public void insert(String plateNo,int carNo,int cardNo,int password) throws Exception{
        try(RoadServiceRepository roadServiceRepository = new RoadServiceRepository()){
            roadServiceRepository.getRoadCost();
            if(CardService.getInstance().check(cardNo,password,roadCost)) {
                RoadServiceEntity roadServiceEntity = new RoadServiceEntity();
                roadServiceEntity.setRoadCode(roadCode);
                roadServiceEntity.setPeopleId(roadPeopleId);
                roadServiceEntity.setPlateNo(plateNo);
                roadServiceEntity.setCarNumber(carNo);
                roadServiceRepository.insertRoadService(roadServiceEntity);
                roadServiceRepository.commit();
            }
        }
    }

    public int getRoadCode() {
        return roadCode;
    }

    public void setRoadCode(int roadCode) {
        this.roadCode = roadCode;
    }

    public int getRoadPeopleId() {
        return roadPeopleId;
    }

    public void setRoadPeopleId(int roadPeopleId) {
        this.roadPeopleId = roadPeopleId;
    }

    public int getRoadCost() {
        return roadCost;
    }

    public void setRoadCost(int roadCost) {
        this.roadCost = roadCost;
    }
}

