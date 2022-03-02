package com.te.carinfoapp.dao;


import org.springframework.data.repository.CrudRepository;

import com.te.carinfoapp.dto.AdminDetails;

public interface AdminDao extends CrudRepository<AdminDetails, Integer> {
	
	AdminDetails findByUsername(String name);
	AdminDetails findById(int id);	

}
