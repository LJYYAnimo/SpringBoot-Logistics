package com.chengsheng.logistics.repository;

import com.chengsheng.logistics.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailEntityRepository extends JpaRepository<OrderDetailEntity,Integer> {

    @Query("from OrderDetailEntity d where d.orderId = ?1 and d.remove = 0")
    List<OrderDetailEntity> findByOrderId(Integer orderId);

}
