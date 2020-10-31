package com.algaworks.algamoney.api.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.algamoney.api.model.Category;
import com.algaworks.algamoney.api.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryResources {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping
	public List<Category> list() {
		return categoryRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
		category = categoryRepository.save(category);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
										.buildAndExpand(category.getId()).toUri();
		
		return ResponseEntity.created(uri).body(category);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Category category = categoryRepository.findById(id).orElse(null);
		if(category == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(category);
	}
}
