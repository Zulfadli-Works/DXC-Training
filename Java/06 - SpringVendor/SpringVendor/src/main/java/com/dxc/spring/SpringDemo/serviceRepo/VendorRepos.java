package com.dxc.spring.SpringDemo.serviceRepo;

import org.springframework.data.repository.CrudRepository;

import com.dxc.spring.SpringDemo.model.Vendor;

public interface VendorRepos extends CrudRepository<Vendor, Integer>
{

}
