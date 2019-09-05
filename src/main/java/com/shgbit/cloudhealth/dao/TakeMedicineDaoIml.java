package com.shgbit.cloudhealth.dao;

import com.shgbit.cloudhealth.model.TakeMedicineRemind;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TakeMedicineDaoIml implements TakeMedicineDao{

    @Override
    public void insertTakeMedicineRemind(TakeMedicineRemind takeMedicineRemind) {
        
    }

    @Override
    public List<TakeMedicineRemind> queryTakeMedicineRemind(String loginId) {
        return null;
    }

    @Override
    public void deteleTakeMedicineRemind(TakeMedicineRemind takeMedicineRemind) {
    }

    @Override
    public void updateTakeMedicineRemind(TakeMedicineRemind takeMedicineRemind) {

    }
}
