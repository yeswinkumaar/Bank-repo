package com.bms.userprofile.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.userprofile.beans.PasswordAudit;

@Repository
public interface PasswordAuidRepository extends JpaRepository<PasswordAudit, Long>{

}
