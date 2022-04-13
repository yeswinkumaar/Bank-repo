package com.bms.userprofile.DAO;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.userprofile.beans.CustomerAddress;
import com.bms.userprofile.beans.CustomerDetail;

@Repository
public interface UpdateUserAddressRepository extends JpaRepository<CustomerAddress,Long> {
	
  
	public Optional<CustomerAddress> findByCustomerDetailAndIsActiveContaining(CustomerDetail customerId,String IsActive);
	
}
