package com.chengsheng.logistics.repository;

import com.chengsheng.logistics.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface OrderDetailEntityRepository extends JpaRepository<OrderDetailEntity,Integer> {

    @Query("from OrderDetailEntity d where d.orderId = ?1 and d.remove = 0")
    List<OrderDetailEntity> findByOrderId(Integer orderId);

    @Modifying
    @Query("update OrderDetailEntity d set d.remove = 1, d.updateId = ?2, d.updateTime = ?3 where d.orderId=?1 ")
    void deleteByOrderId(Integer orderId, Integer updateId, Date updateTime);

}
