package com.bms.userprofile.DAO;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.userprofile.beans.CustomerDetail;

@Repository
public interface UpdateUserDetailsRepository extends JpaRepository<CustomerDetail, Long>{
	
	
	
	
}
