package com.project.people;

import Exceptions.UserDoesNotMatchException;
import Exceptions.UserNotFoundException;

import java.util.Objects;

public class PeopleService {

    private PeopleService(){

    }

    private static  PeopleService peopleService = new PeopleService();

    public static PeopleService getInstance(){
        return peopleService;
    }

    public boolean check(PeopleEntity peopleEntity,String name, String lastName) throws Exception {
        try(PeopleRepository peopleRepository = new PeopleRepository()){
            peopleRepository.findUser(peopleEntity);
            if(userNull(peopleEntity))
                return userMatch(peopleEntity, name, lastName);
            return false;
        }
    }

    static boolean userNull(PeopleEntity peopleEntity) throws UserNotFoundException {
        if(peopleEntity.getLastName() == null || peopleEntity.getFirstName() == null){
            throw new UserNotFoundException("Failed to find user");
        }
        else {
            return true;
        }
    }

    static boolean userMatch(PeopleEntity peopleEntity,String name, String last) throws UserDoesNotMatchException{
        if(!Objects.equals(peopleEntity.getFirstName(), name) || !Objects.equals(peopleEntity.getLastName(), last)){
            throw new UserDoesNotMatchException("Name or Last name do not match");
        }
        else{
            return true;
        }
    }


}
