package com.smartjob.cl.bci.repository;

import com.smartjob.cl.bci.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhoneRepository extends JpaRepository<PhoneEntity, Integer> {
}
