package com.bms.userprofile.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.userprofile.beans.CustomerCredentials;


@Repository
public interface UserIDRepository extends JpaRepository<CustomerCredentials,Long> {
	
	public long countByUsername(String userName);

	public Optional<CustomerCredentials> findByUsername(String userName);
}
