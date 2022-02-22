package com.devsuperior.movieflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.services.GenreService;

@RestController
@RequestMapping(value = "/genres")
public class GenreController {
	
	@Autowired
	private GenreService service;
	
	@GetMapping
	public ResponseEntity<List<GenreDTO>> findAll(
			@RequestParam(value = "direction", defaultValue = "ASC") String direction
			) {
		List<GenreDTO> list = service.findAll(Direction.valueOf(direction));		
		return ResponseEntity.ok().body(list);
	}

}
