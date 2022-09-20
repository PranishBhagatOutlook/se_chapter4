package com.example.macbookstore.data;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.macbookstore.Order;

public interface OrderRepository
        extends CrudRepository<Order, String> {

}