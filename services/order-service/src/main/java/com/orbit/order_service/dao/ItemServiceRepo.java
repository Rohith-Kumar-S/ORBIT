package com.orbit.order_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orbit.order_service.model.Item;


@Repository
public interface ItemServiceRepo extends JpaRepository<Item, Integer>{

}
