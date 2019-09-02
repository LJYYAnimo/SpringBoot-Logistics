package com.chengsheng.logistics.repository;

import com.chengsheng.logistics.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity,Integer> {


    @Query(value = "select COUNT(1) from chengsheng_order where GET_GOODS_DATE =:getGoodsDate", nativeQuery = true)
    Integer findNoByGetDate(@Param("getGoodsDate")String getGoodsDate);

}
