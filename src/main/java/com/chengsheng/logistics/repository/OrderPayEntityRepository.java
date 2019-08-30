package com.chengsheng.logistics.repository;

import com.chengsheng.logistics.entity.OrderPayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPayEntityRepository extends JpaRepository<OrderPayEntity,Integer> {
}
