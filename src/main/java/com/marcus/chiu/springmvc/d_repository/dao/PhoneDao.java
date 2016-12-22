package com.marcus.chiu.springmvc.d_repository.dao;

import com.marcus.chiu.springmvc.e_entity.entity.Phone;

import java.util.List;

/**
 * Created by marcus.chiu on 10/26/16.
 */
public interface PhoneDao {

    Phone findById(int id);

    void savePhone(Phone phone);

    void deletePhoneById(int id);

    List<Phone> findAllPhones();

    Phone findPhoneByNumber(String number);
}
