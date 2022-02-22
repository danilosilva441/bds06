package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.devsuperior.movieflix.entities.Review;

public class ReviewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message = "Campo requerido")
	private String text;
	
	private UserDTO userId;
	
	@NotNull(message = "Campo requerido")
	private Long movieId;
	
	public ReviewDTO() {
	}

	public ReviewDTO(Long id, @NotNull(message = "Campo requerido") String text, UserDTO userId,
			@NotNull(message = "Campo requerido") Long movieId) {
		super();
		this.id = id;
		this.text = text;
		this.userId = userId;
		this.movieId = movieId;
	}

	public ReviewDTO(Review entity) {
		id = entity.getId();
		text = entity.getText();
		userId = new UserDTO(entity.getUser());
		movieId = entity.getMovie().getId();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public UserDTO getUserId() {
		return userId;
	}

	public void setUserId(UserDTO userId) {
		this.userId = userId;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
}
