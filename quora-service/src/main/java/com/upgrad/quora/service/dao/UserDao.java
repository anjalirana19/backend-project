package com.upgrad.quora.service.dao;

//import com.upgrad.quora.service.entity.UserAuthTokenEntity;

import com.upgrad.quora.service.entity.UserAuthTokenEntity;
import com.upgrad.quora.service.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class UserDao {



    @PersistenceContext
    private EntityManager entityManager;

    public UserEntity createUser(UserEntity userEntity){
        entityManager.persist(userEntity);
        return userEntity;
    }
    public UserEntity checkUserName(String userName){
        try {
            return entityManager.createNamedQuery("userByName", UserEntity.class).setParameter("username", userName).getSingleResult();
        }
        catch (Exception e){
            return  null;
        }

    }
    public UserEntity checkEmail(String email){
        try {
            return entityManager.createNamedQuery("userByEmail", UserEntity.class).setParameter("useremail", email).getSingleResult();
        }
        catch (Exception e){
            return  null;
        }
    }

    public UserEntity getUser(final String userUuid){
        try{
            return entityManager.createNamedQuery("userByUuid",UserEntity.class).setParameter("uuid",userUuid).getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }

    public UserEntity getUserByEmail(final String email) {
        try {
            return entityManager.createNamedQuery("userByEmail", UserEntity.class).setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public UserAuthTokenEntity createAuthToken(final UserAuthTokenEntity userAuthTokenEntity){
        entityManager.persist(userAuthTokenEntity);
        return userAuthTokenEntity;
    }
    public UserAuthTokenEntity checkLogin(final String accessToken){
        try{
            return entityManager.createNamedQuery("userByAccessToken",UserAuthTokenEntity.class).setParameter("accessToken",accessToken).getSingleResult();
        }
        catch (Exception e){
            return null;
        }
    }
    public UserEntity getUserByUuid(String uuid){
        try {
            return entityManager.createNamedQuery("userByUuid", UserEntity.class).setParameter("uuid", uuid).getSingleResult();
        }
        catch (Exception e){
            return  null;
        }
    }

    public void updateUser(final UserEntity updatedUserEntity){
        entityManager.merge(updatedUserEntity);
    }
}
