package com.project.violation;

import com.project.cards.CardService;

import java.sql.SQLException;
import java.util.List;

public class ViolationService {

    private ViolationService(){}

    private static int violationID;

    private static ViolationService violationService = new ViolationService();

    public static ViolationService getInstance(){
        return violationService;
    }


    public void setViolationID(int violationID) {
        ViolationService.violationID = violationID;
    }

    public int getViolationID() {
        return violationID;
    }

    //sums up points on the license
    public int points(ViolationEntity violationEntity) throws Exception {
        try (ViolationRepository violationRepository = new ViolationRepository()){
            violationRepository.showLicensePoints(violationEntity);
            return violationEntity.getViolationPoint();
        }
    }

    //lists violations
    public List<ViolationEntity> show(ViolationEntity violationEntity) throws Exception {
        List<ViolationEntity> violationEntities;
        try(ViolationRepository violationRepository = new ViolationRepository()) {
            violationEntities = violationRepository.showViolations(violationEntity);
        }
        return violationEntities;
    }

    public boolean check(ViolationEntity violationEntity) throws SQLException {
        try (ViolationRepository violationRepository = new ViolationRepository()){
            return violationRepository.checkPlate(violationEntity);
        }
    }

    public void add(ViolationEntity violationEntity) throws SQLException {
        try (ViolationRepository violationRepository = new ViolationRepository()){
            violationRepository.insertViolation(violationEntity);
            violationRepository.commit();
        }
    }

    public void clear(String cardTxt,String passwordTxt) throws Exception{
        ViolationEntity violationEntity = new ViolationEntity();
        violationEntity.setViolationId(violationID);
        try (ViolationRepository violationRepository = new ViolationRepository()){
            violationRepository.getViolationCharge(violationEntity);
            System.out.println(violationEntity.getViolationId());
            int card = Integer.parseInt(cardTxt);
            int password = Integer.parseInt(passwordTxt);
            if (CardService.getInstance().check(card,password,violationEntity.getViolationCharge())){
                violationRepository.clearViolation(violationEntity);
                int balance = CardService.getInstance().balance(card);
                balance = balance - violationEntity.getViolationCharge();
                CardService.getInstance().update(card,balance);
                violationRepository.commit();
            }
        }
    }


}
