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
import co.edu.uniajc.cinema.model.City;
import co.edu.uniajc.cinema.service.CityService;
import co.edu.uniajc.cinema.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(value = "City", tags = "City")
@RestController
@RequestMapping("/api/v1")
public class CityController {

	@Autowired
	private CityService city_service;
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@GetMapping("/city")
	public List<City> getAll() throws NoSuchMethodException, ResourceNotFoundException {
		try {
			return city_service.getAll();
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@GetMapping("/city/{id}")
	public ResponseEntity<City> getById(@PathVariable(value = "id") Integer idCity) throws NoSuchMethodException {
		City city = new City();
		try {
			Util util = new Util();
			boolean estado = util.isNumeric(idCity.toString());
			if (estado == true) {
				city = city_service.getById(idCity);
				return ResponseEntity.ok().body(city);
			}

		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
		return ResponseEntity.ok().body(city);
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@PostMapping("/city")
	public City create(@Valid @RequestBody City city) throws NoSuchMethodException {
		try {
			return city_service.create(city);
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@PutMapping("/city/{id}")
	public ResponseEntity<City> edit(@PathVariable(value = "id") Integer idCity,
			@Valid @RequestBody City cityDetalle) throws NoSuchMethodException {
		City city = new City();
		try {
			city = city_service.edit(idCity, cityDetalle);
			return ResponseEntity.ok(city);
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@DeleteMapping("/city/{id}")
	public Map<String, Boolean> delete(@PathVariable(value = "id") Integer idCity)
			throws ResourceNotFoundException, NoSuchMethodException {
		try {
			city_service.delete(idCity);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
}
