package com.lab6.crud_lab6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab6.crud_lab6.model.Empleado;
 

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Long>{

}
