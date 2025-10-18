package com.orbit.cart_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orbit.cart_service.model.Item;


@Repository
public interface ItemServiceRepo extends JpaRepository<Item, Integer>{

}
