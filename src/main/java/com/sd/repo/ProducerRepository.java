package com.sd.repo;


import com.sd.entity.Producer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Producer entity.
 */

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {

}
