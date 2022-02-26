package com.devsuperior.movieflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.services.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	private MovieService service;
	
		

	@GetMapping
	public ResponseEntity<Page<MovieDTO>> findAllGenre(
			@RequestParam(value = "genreId", defaultValue = "0") Long genreId,
			@RequestParam(value = "title", defaultValue = "") String title,
			Pageable pageable) {
		Page<MovieDTO> page = service.pageForCurrentUserGenre(genreId, title.trim(), pageable);

		return ResponseEntity.ok().body(page);
	}	

	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
		MovieDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	
//	@GetMapping(value = "/{id}/reviews")
//	public ResponseEntity<MovieDTO> findByIdReview(@PathVariable Long id) {
//		MovieDTO dto = service.findById(id);
//		return ResponseEntity.ok().body(dto);
//	}

}
