package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserRepository {

    @Inject
    EntityManager entityManager;

    public UserEntity findByTaiKhoan(String taiKhoan) {
        try{
            return entityManager.createQuery("FROM UserEntity WHERE taiKhoan = :taiKhoan", UserEntity.class)
                    .setParameter("taiKhoan", taiKhoan)
                    .getSingleResult();
        } catch (NoResultException noResultExceptione) {
            return null;
        }
    }



    @Transactional
    public void save(UserEntity user) {
        entityManager.persist(user);
    }
}
