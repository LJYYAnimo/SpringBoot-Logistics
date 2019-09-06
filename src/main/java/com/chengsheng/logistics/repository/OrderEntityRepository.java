package com.chengsheng.logistics.repository;

import com.chengsheng.logistics.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Repository
@Transactional
public interface OrderEntityRepository extends JpaRepository<OrderEntity,Integer> {

    @Query(value = "select COUNT(1) from chengsheng_order where GET_GOODS_DATE =:getGoodsDate", nativeQuery = true)
    Integer findNoByGetDate(@Param("getGoodsDate")String getGoodsDate);

    @Modifying
    @Query("update OrderEntity o set o.remove = 1, o.updateId = ?2, o.updateTime = ?3 where o.id=?1 ")
    void deleteById(Integer id, Integer updateId, Date updateTime);

}
