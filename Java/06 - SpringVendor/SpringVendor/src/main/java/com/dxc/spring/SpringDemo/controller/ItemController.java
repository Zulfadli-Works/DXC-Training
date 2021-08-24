package com.dxc.spring.SpringDemo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.spring.SpringDemo.model.Item;
import com.dxc.spring.SpringDemo.serviceRepo.ItemRepos;

@RestController
@RequestMapping("/itm") //this is for the url in postman, to access item controller	//http://localhost:8089/itm
public class ItemController 
{
	@Autowired
	public ItemRepos itm;
	
	//http://localhost:8089/itm/insert
	@PostMapping("/insert")
	public String insert(@RequestBody Item i)
	{
		itm.save(i);
		return "Hello data is saved";
	}
	
	//http://localhost:8089/itm/fetch
	@GetMapping("/fetch")
	public List<Item> findAllVendors()
	{
		return (List<Item>) itm.findAll();
	}
	
	//http://localhost:8089/itm/find
	@GetMapping("/find/{id}")
	public List<Item> findAllId(@PathVariable int id)
	{
		return (List<Item>) itm.findAllById(Collections.singleton(id));
		
	}
	
	//http://localhost:8089/itm/delete
	@DeleteMapping("/delete/{id}")
	  public List<Item> deleteVendor(@PathVariable int id){
		itm.deleteById(id);
	     return (List<Item>) itm.findAll();
	}
}
