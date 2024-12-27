package com.example.React_back.Repository;

import com.example.React_back.Models.Conges;
import com.example.React_back.Models.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CongesRepository extends JpaRepository<Conges, Integer> {
    List<Conges> findByEmployeeId(Long employeeId);


    List<Conges> findByValidatedFalse();

    void deleteByEmployeeId(Long id);
}
