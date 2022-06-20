package com.chrisbees.management.repositories;

import com.chrisbees.management.model.Drivers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends CrudRepository<Drivers, Long> {
    public Drivers findDriversByBusName(String busName);
}
