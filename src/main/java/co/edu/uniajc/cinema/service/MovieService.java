package co.edu.uniajc.cinema.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniajc.cinema.model.Movie;
import co.edu.uniajc.cinema.repository.MovieRepository;
import co.edu.uniajc.cinema.exception.ResourceNotFoundException;

@Service
@Transactional
public class MovieService {

	@Autowired
	private MovieRepository gender_Repository;

	/**
	 * getAll() Se lista el contenido de la tabla pelicula
	 * 
	 * @return
	 */
	public List<Movie> getAll() {
		List<Movie> listaUsuario = new ArrayList<Movie>();
		try {
			listaUsuario = gender_Repository.findAll();
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return listaUsuario;
	}

	/**
	 * getById Se obtiene por medio de Identificador de tabla pelicula
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Movie getById(Integer idMovie) {
		Movie movie = new Movie();
		try {
			if (idMovie > 0) {
				movie = gender_Repository.findById(idMovie).orElseThrow(
						() -> new ResourceNotFoundException("Pelicula no encontrado por Id :: " + idMovie));
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return movie;
	}

	/**
	 * create Se crea objeto en tabla pelicula
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Movie create(Movie movie) {
		Movie userMovie = new Movie();
		try {
			if (movie != null) {
				userMovie = gender_Repository.save(movie);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return userMovie;
	}

	/**
	 * edit Se edita objeto en tabla pelicula
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Movie edit(Integer idMovie, Movie movieDetalle) {
		Movie movie = new Movie();
		try {
			if (idMovie > 0) {
				Movie getMovie = getById(idMovie);
				if (getMovie != null) {
					getMovie.setTitle(movieDetalle.getTitle());
					getMovie.setClassification(movieDetalle.getClassification());
					getMovie.setTrailer(movieDetalle.getTrailer());
					getMovie.setWebsite(movieDetalle.getWebsite());
					getMovie.setReleaseDate(movieDetalle.getReleaseDate());
					getMovie.setDirector(movieDetalle.getDirector());
					getMovie.setOriginalLanguage(movieDetalle.getOriginalLanguage());
					getMovie.setSypnosys(movieDetalle.getSypnosys());
					getMovie.setGender(movieDetalle.getGender());
					movie = gender_Repository.save(getMovie);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return movie;
	}

	/**
	 * delete() Se elimina objeto en tabla pelicula
	 * 
	 * @param idUsuario
	 * @return
	 */
	public void delete(Integer idMovie) {
		try {
			if (idMovie > 0) {
				Movie movie = getById(idMovie);
				if (movie != null) {
					gender_Repository.delete(movie);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}
