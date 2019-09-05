package com.shgbit.cloudhealth.service;

import com.shgbit.cloudhealth.dao.TakeMedicineDao;
import com.shgbit.cloudhealth.model.TakeMedicineRemind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TakeMedicineServiceIml implements TakeMedicineService {

    @Autowired
    private TakeMedicineDao takeMedicineDao;

    @Override
    public void insertTakeMedicineRemind(TakeMedicineRemind takeMedicineRemind) {
        takeMedicineDao.insertTakeMedicineRemind(takeMedicineRemind);
    }

    @Override
    public List<TakeMedicineRemind> queryTakeMedicineRemind(String loginId) {
        return takeMedicineDao.queryTakeMedicineRemind(loginId);
    }

    @Override
    public void deteleTakeMedicineRemind(TakeMedicineRemind takeMedicineRemind) {
         takeMedicineDao.deteleTakeMedicineRemind(takeMedicineRemind);
    }

    @Override
    public void updateTakeMedicineRemind(TakeMedicineRemind takeMedicineRemind) {
        takeMedicineDao.updateTakeMedicineRemind(takeMedicineRemind);
    }


}
