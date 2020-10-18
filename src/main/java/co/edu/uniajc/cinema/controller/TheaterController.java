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
import co.edu.uniajc.cinema.model.Theater;
import co.edu.uniajc.cinema.service.TheaterService;
import co.edu.uniajc.cinema.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(value = "Theater", tags = "Theater")
@RestController
@RequestMapping("/api/v1")
public class TheaterController {

	@Autowired
	private TheaterService theater_service;
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@GetMapping("/theater")
	public List<Theater> getAll() throws NoSuchMethodException, ResourceNotFoundException {
		try {
			return theater_service.getAll();
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@GetMapping("/theater/{id}")
	public ResponseEntity<Theater> getById(@PathVariable(value = "id") Integer idTheater) throws NoSuchMethodException {
		Theater reservation = new Theater();
		try {
			Util util = new Util();
			boolean estado = util.isNumeric(idTheater.toString());
			if (estado == true) {
				reservation = theater_service.getById(idTheater);
				return ResponseEntity.ok().body(reservation);
			}

		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
		return ResponseEntity.ok().body(reservation);
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@PostMapping("/theater")
	public Theater create(@Valid @RequestBody Theater theater) throws NoSuchMethodException {
		try {
			return theater_service.create(theater);
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@PutMapping("/theater/{id}")
	public ResponseEntity<Theater> edit(@PathVariable(value = "id") Integer idTheater,
			@Valid @RequestBody Theater theaterDetalle) throws NoSuchMethodException {
		Theater theater = new Theater();
		try {
			theater = theater_service.edit(idTheater, theaterDetalle);
			return ResponseEntity.ok(theater);
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@DeleteMapping("/theater/{id}")
	public Map<String, Boolean> delete(@PathVariable(value = "id") Integer idTheater)
			throws ResourceNotFoundException, NoSuchMethodException {
		try {
			theater_service.delete(idTheater);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
}
