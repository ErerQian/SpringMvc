package com.gaussic.controller;

import com.gaussic.model.UserEntity;
import com.gaussic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by HP-HQ on 2017/2/10.
 */
public class Mian {
    @Autowired
    static UserRepository userRepository;
    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.add();
        UserEntity s = new UserEntity();
        s.setLastname("5454564");
        userRepository.saveAndFlush(s);
    }


}
