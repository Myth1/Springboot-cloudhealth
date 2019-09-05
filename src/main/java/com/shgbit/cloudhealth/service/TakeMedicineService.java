package com.shgbit.cloudhealth.service;

import com.shgbit.cloudhealth.model.TakeMedicineRemind;

import java.util.List;

public interface TakeMedicineService {
    void insertTakeMedicineRemind(TakeMedicineRemind takeMedicineRemind);

    List<TakeMedicineRemind> queryTakeMedicineRemind(String loginId);

    void deteleTakeMedicineRemind(TakeMedicineRemind takeMedicineRemind);

    void updateTakeMedicineRemind(TakeMedicineRemind takeMedicineRemind);
}
