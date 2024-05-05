package com.gfg.jdblblr2librarymanagement.repository;

import com.gfg.jdblblr2librarymanagement.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {
}
