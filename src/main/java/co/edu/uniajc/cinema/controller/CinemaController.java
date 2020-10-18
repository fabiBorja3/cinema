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
import co.edu.uniajc.cinema.model.Cinema;
import co.edu.uniajc.cinema.service.CinemaService;
import co.edu.uniajc.cinema.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(value = "Cinema", tags = "Cinema")
@RestController
@RequestMapping("/api/v1")
public class CinemaController {

	@Autowired
	private CinemaService cinema_service;
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@GetMapping("/cinema")
	public List<Cinema> getAll() throws NoSuchMethodException, ResourceNotFoundException {
		try {
			return cinema_service.getAll();
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@GetMapping("/cinema/{id}")
	public ResponseEntity<Cinema> getById(@PathVariable(value = "id") Integer idCinema) throws NoSuchMethodException {
		Cinema cinema = new Cinema();
		try {
			Util util = new Util();
			boolean estado = util.isNumeric(idCinema.toString());
			if (estado == true) {
				cinema = cinema_service.getById(idCinema);
				return ResponseEntity.ok().body(cinema);
			}

		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
		return ResponseEntity.ok().body(cinema);
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@PostMapping("/cinema")
	public Cinema create(@Valid @RequestBody Cinema cinema) throws NoSuchMethodException {
		try {
			return cinema_service.create(cinema);
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@PutMapping("/cinema/{id}")
	public ResponseEntity<Cinema> edit(@PathVariable(value = "id") Integer idCinema,
			@Valid @RequestBody Cinema cinemaDetalle) throws NoSuchMethodException {
		Cinema cinema = new Cinema();
		try {
			cinema = cinema_service.edit(idCinema, cinemaDetalle);
			return ResponseEntity.ok(cinema);
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@DeleteMapping("/cinema/{id}")
	public Map<String, Boolean> delete(@PathVariable(value = "id") Integer idCinema)
			throws ResourceNotFoundException, NoSuchMethodException {
		try {
			cinema_service.delete(idCinema);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
}
