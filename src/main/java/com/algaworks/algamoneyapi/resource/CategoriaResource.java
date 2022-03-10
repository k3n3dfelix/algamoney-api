package com.algaworks.algamoneyapi.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.algamoneyapi.model.Categoria;
import com.algaworks.algamoneyapi.repository.CategoriaRepository;



//Controlador
//Recursos da categoria, vai expor tudo que esteja relacionado a categoria

@RestController 														//Já converte para Json
@RequestMapping("/categorias")		
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository categoriaRepository; 					//Injetando o repositório
	
	@GetMapping
	public List<Categoria> listar(){
		return categoriaRepository.findAll();
	}
	
	@PostMapping
	//@ResponseStatus(HttpStatus.CREATED)									//Retorna o status 201 Created se ok
	public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		
		//Retornando a Url na Hrader do recurso
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(categoriaSalva.getCodigo()).toUri();  //Builder do spring, pegando a partir da Uri da requisição atual
		response.setHeader("Location",uri.toASCIIString());				//Setando o header location
		
		return ResponseEntity.created(uri).body(categoriaSalva);
	}
	
	 @GetMapping("/{codigo}")
	public Categoria buscarPeloCodigo(@PathVariable Long codigo) {
		
		return categoriaRepository.findById(codigo).orElse(null);
		
	} 
	/* Outra opção buscarPeloCodigo
	@GetMapping("/{codigo}")
	public Optional<Categoria> getCategoria(@RequestBody @PathVariable Long codigo){
		return categoriaRepository.findById(codigo);
	} */
}
