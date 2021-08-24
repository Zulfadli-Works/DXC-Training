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

import com.dxc.spring.SpringDemo.model.Vendor;
import com.dxc.spring.SpringDemo.serviceRepo.VendorRepos;

@RestController
@RequestMapping("/ven")	//this is for the url in postman, to access vendor controller
public class VendorController 
{
	@Autowired
	public VendorRepos ven;
	
	@PostMapping("/insert")
	public String insert(@RequestBody Vendor v)
	{
		ven.save(v);
		return "Hello data is saved";
	}
	
	@GetMapping("/fetch")
	public List<Vendor> findAllVendors()
	{
		return (List<Vendor>) ven.findAll();
	}
	
	@GetMapping("/find/{id}")
	public List<Vendor> findAllId(@PathVariable int id)
	{
		return (List<Vendor>) ven.findAllById(Collections.singleton(id));
		
	}
	
	@DeleteMapping("/delete/{id}")
	  public List<Vendor> deleteVendor(@PathVariable int id){
		ven.deleteById(id);
	     return (List<Vendor>) ven.findAll();
	}
}
