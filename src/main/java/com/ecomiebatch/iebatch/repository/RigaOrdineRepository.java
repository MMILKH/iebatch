package com.ecomiebatch.iebatch.repository;


import com.ecomiebatch.iebatch.entity.RigaOrdine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RigaOrdineRepository extends JpaRepository <RigaOrdine,Integer>{

    List<RigaOrdine> findByOrdineId(int ordineId);
}
