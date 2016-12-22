package com.marcus.chiu.springmvc.c_service.service;

import com.marcus.chiu.springmvc.e_entity.entity.Phone;

import java.util.List;

/**
 * Created by marcus.chiu on 10/26/16.
 */
public interface PhoneService {
    Phone findById(int id);

    void savePost(Phone phone);

    void updatePhone(Phone phone);

    void deletePhoneById(int id);

    List<Phone> findAllPhones();
}
