package com.example.React_back.Repository;

import com.example.React_back.Models.Conges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CongesRepository extends JpaRepository<Conges, Integer> {
}
