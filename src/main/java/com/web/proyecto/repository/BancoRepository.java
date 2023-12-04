package com.web.proyecto.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.web.proyecto.entity.Banco;
public interface BancoRepository extends JpaRepository<Banco, Integer>  {

}
