package com.chengsheng.logistics.repository;

import com.chengsheng.logistics.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserEntityRepository extends JpaRepository<UserEntity,Integer> {

    @Query("from UserEntity u where u.userName = ?1 and u.remove = 0")
    UserEntity findByUserName(String userName);
}
