package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.UserDao;
import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserBusinessService {


    @Autowired
    private UserDao userDao;


    public boolean checkUserName(String userName)throws SignUpRestrictedException{
            if(userDao.checkUserName(userName)!=null){
                throw new SignUpRestrictedException("SGR-001","Try any other Username, this Username has already been taken");
            }
            return true;
    }
    public boolean checkEmail(String email)throws SignUpRestrictedException{
        if(userDao.checkEmail(email)!=null)
        {
            throw new SignUpRestrictedException("SGR-002","This user has already been registered, try with any other emailId");
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity signup(UserEntity userEntity)
    {
        return userDao.createUser(userEntity);
    }
}

