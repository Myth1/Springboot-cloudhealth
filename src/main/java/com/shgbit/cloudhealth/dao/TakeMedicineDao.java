package com.shgbit.cloudhealth.dao;

import com.shgbit.cloudhealth.model.TakeMedicineRemind;

import java.util.List;

public interface TakeMedicineDao {
    void insertTakeMedicineRemind(TakeMedicineRemind takeMedicineRemind);

    List<TakeMedicineRemind> queryTakeMedicineRemind(String loginId);

    void updateTakeMedicineRemind(TakeMedicineRemind takeMedicineRemind);

    void deteleTakeMedicineRemind(TakeMedicineRemind takeMedicineRemind);
}
