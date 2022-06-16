package com.project.accident;

import java.sql.SQLException;
import java.util.List;

public class AccidentService {

    private AccidentService(){

    }

    public static AccidentService accidentService = new AccidentService();

    public static AccidentService getInstance(){
        return accidentService;
    }

    public boolean check(AccidentEntity accidentEntity) throws SQLException {
        AccidentRepository accidentRepository = new AccidentRepository();
        return accidentRepository.checkCarNo(accidentEntity);
    }

    public List<AccidentEntity> show(AccidentEntity accidentEntity) throws SQLException {
        AccidentRepository accidentRepository = new AccidentRepository();
        return accidentRepository.showAccidents(accidentEntity);
    }

    public void add(AccidentEntity accidentEntity) throws Exception {
        try(AccidentRepository accidentRepository = new AccidentRepository()){
            accidentRepository.insertAccidents(accidentEntity);
            accidentRepository.commit();
        }

    }

}
