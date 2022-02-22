package com.devsuperior.movieflix.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private AuthService authService;

	@Transactional
	public Page<ReviewDTO> pageForCurrentUser(Pageable pageable) {
		Page<Review> page = repository.findAll(pageable);
		return page.map(x -> new ReviewDTO(x));
	}

	@Transactional(readOnly = true)
	public ReviewDTO findById(Long id) {
		Optional<Review> obj = repository.findById(id);
		Review entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ReviewDTO(entity);
	}

	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {

		User user = authService.authenticated();

		try {
			Review entity = new Review();
			entity.setMovie(movieRepository.getOne(dto.getMovieId()));
			entity.setUser(user);
			entity.setText(dto.getText());
			repository.save(entity);
			return new ReviewDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("id not found " + dto.getMovieId());
		}
	}
}
