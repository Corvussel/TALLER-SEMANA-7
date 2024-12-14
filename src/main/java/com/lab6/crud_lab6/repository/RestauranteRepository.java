package com.lab6.crud_lab6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab6.crud_lab6.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante,Long>{

}
