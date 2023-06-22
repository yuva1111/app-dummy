package com.appmetadata.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.appmetadata.Register;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<Register,Long> {

}
