package com.project.police;

import Exceptions.UserDoesNotMatchException;
import Exceptions.UserNotFoundException;

import java.util.Objects;

public class PoliceService {

    private static PoliceService policeService = new PoliceService();

    public static PoliceService getInstance() {
        return policeService;
    }

    public boolean check(PoliceEntity policeEntity, String name, String lastName,String password) throws Exception {
        try(PoliceRepository policeRepository = new PoliceRepository()){
            policeRepository.findPolice(policeEntity);
            if(policeNull(policeEntity))
                return policeMatch(policeEntity, name, lastName, password);
            return false;
        }
    }

    static boolean policeNull(PoliceEntity policeEntity) throws UserNotFoundException {
        if(policeEntity.getPoliceFirstName() == null || policeEntity.getPoliceLastName() == null
                || policeEntity.getPolicePass() == null){
            throw new UserNotFoundException("Failed to find Police");
        }
        else {
            System.out.println("found Police");
            return true;
        }
    }

    static boolean policeMatch(PoliceEntity policeEntity,String name, String last, String password)
            throws UserDoesNotMatchException {
        if(!Objects.equals(policeEntity.getPoliceFirstName(), name)
                || !Objects.equals(policeEntity.getPoliceLastName(), last)
                || !Objects.equals(policeEntity.getPolicePass(), password)){
            throw new UserDoesNotMatchException("Name ,Last name or Password do not match");
        }
        else{
            System.out.println("matched");
            return true;
        }
    }
    
}
