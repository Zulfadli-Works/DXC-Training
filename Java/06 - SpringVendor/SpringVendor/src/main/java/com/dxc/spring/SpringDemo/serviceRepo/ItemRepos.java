package com.dxc.spring.SpringDemo.serviceRepo;

import org.springframework.data.repository.CrudRepository;

import com.dxc.spring.SpringDemo.model.Item;

public interface ItemRepos extends CrudRepository<Item, Integer>
{

}
