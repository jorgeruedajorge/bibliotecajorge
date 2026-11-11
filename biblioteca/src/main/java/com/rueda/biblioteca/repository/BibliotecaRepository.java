package com.rueda.biblioteca.repository;

import com.rueda.biblioteca.model.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliotecaRepository extends JpaRepository <Biblioteca, Integer>{
}
