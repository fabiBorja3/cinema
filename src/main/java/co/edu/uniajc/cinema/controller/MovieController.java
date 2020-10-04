package co.edu.uniajc.cinema.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniajc.cinema.exception.ResourceNotFoundException;
import co.edu.uniajc.cinema.model.Movie;
import co.edu.uniajc.cinema.service.MovieService;
import co.edu.uniajc.cinema.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(value = "Movie", tags = "Movie")
@RestController
@RequestMapping("/api/v1")
public class MovieController {

	@Autowired
	private MovieService movie_service;
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@GetMapping("/movie")
	public List<Movie> getAll() throws NoSuchMethodException, ResourceNotFoundException {
		try {
			return movie_service.getAll();
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@GetMapping("/movie/{id}")
	public ResponseEntity<Movie> getById(@PathVariable(value = "id") Integer idMovie) throws NoSuchMethodException {
		Movie movie = new Movie();
		try {
			Util util = new Util();
			boolean estado = util.isNumeric(idMovie.toString());
			if (estado == true) {
				movie = movie_service.getById(idMovie);
				return ResponseEntity.ok().body(movie);
			}

		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
		return ResponseEntity.ok().body(movie);
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@PostMapping("/movie")
	public Movie create(@Valid @RequestBody Movie movie) throws NoSuchMethodException {
		try {
			return movie_service.create(movie);
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@PutMapping("/movie/{id}")
	public ResponseEntity<Movie> edit(@PathVariable(value = "id") Integer idMovie,
			@Valid @RequestBody Movie movieDetalle) throws NoSuchMethodException {
		Movie movie = new Movie();
		try {
			movie = movie_service.edit(idMovie, movieDetalle);
			return ResponseEntity.ok(movie);
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@DeleteMapping("/movie/{id}")
	public Map<String, Boolean> delete(@PathVariable(value = "id") Integer idMovie)
			throws ResourceNotFoundException, NoSuchMethodException {
		try {
			movie_service.delete(idMovie);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
}
