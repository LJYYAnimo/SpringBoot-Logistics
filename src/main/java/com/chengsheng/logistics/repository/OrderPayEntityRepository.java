package com.chengsheng.logistics.repository;

import com.chengsheng.logistics.entity.OrderDetailEntity;
import com.chengsheng.logistics.entity.OrderPayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface OrderPayEntityRepository extends JpaRepository<OrderPayEntity,Integer> {

    @Modifying
    @Query("update OrderPayEntity p set p.remove = 1, p.updateId = ?2, p.updateTime = ?3 where p.orderId=?1 ")
    void deleteByOrderId(Integer orderId, Integer updateId, Date updateTime);


    @Query("from OrderPayEntity p where p.orderId = ?1 and p.remove = 0")
    List<OrderPayEntity> findByOrderId(Integer orderId);
}
