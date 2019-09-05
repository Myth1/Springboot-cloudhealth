package com.shgbit.cloudhealth.test;

import com.shgbit.cloudhealth.dao.UserRepositoryImpl;
import com.shgbit.cloudhealth.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserRepositoryImpl userDao;

    @Test
    public void testSaveUser() {
        User user=new User();
        user.setUserName("小213asdfasfd明12w23222");
        user.setPassWord("1212343");
        userDao.saveUser(user);
    }

    @Test
    public void findUserByUserName(){
        User user= userDao.findUserByUserName("小明");
       System.out.println("user is "+user);
    }

    @Test
    public void updateUser(){
        User user=new User();
        user.setUserName("天空");
        user.setPassWord("fffxxxx");
        userDao.updateUser(user);
    }

    @Test
    public void deleteUserById(){
//        userDao.deleteUserById(1l);
    }

}