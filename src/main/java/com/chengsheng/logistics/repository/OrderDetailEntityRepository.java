package com.chengsheng.logistics.repository;

import com.chengsheng.logistics.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailEntityRepository extends JpaRepository<OrderDetailEntity,Integer> {



}
