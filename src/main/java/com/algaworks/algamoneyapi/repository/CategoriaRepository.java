package com.algaworks.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algamoneyapi.model.Categoria;

//Jpa repository traz metodos auxiliares prontos, com implmentação atraves do spring data jpa

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
