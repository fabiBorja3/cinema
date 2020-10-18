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
import co.edu.uniajc.cinema.model.Person;
import co.edu.uniajc.cinema.service.PersonService;
import co.edu.uniajc.cinema.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(value = "Person", tags = "Person")
@RestController
@RequestMapping("/api/v1")
public class PersonController {

	@Autowired
	private PersonService person_service;
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@GetMapping("/person")
	public List<Person> getAll() throws NoSuchMethodException, ResourceNotFoundException {
		try {
			return person_service.getAll();
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@GetMapping("/person/{id}")
	public ResponseEntity<Person> getById(@PathVariable(value = "id") Integer idPerson) throws NoSuchMethodException {
		Person city = new Person();
		try {
			Util util = new Util();
			boolean estado = util.isNumeric(idPerson.toString());
			if (estado == true) {
				city = person_service.getById(idPerson);
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
	@PostMapping("/person")
	public Person create(@Valid @RequestBody Person person) throws NoSuchMethodException {
		try {
			return person_service.create(person);
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@PutMapping("/person/{id}")
	public ResponseEntity<Person> edit(@PathVariable(value = "id") Integer idPerson,
			@Valid @RequestBody Person personDetalle) throws NoSuchMethodException {
		Person person = new Person();
		try {
			person = person_service.edit(idPerson, personDetalle);
			return ResponseEntity.ok(person);
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@DeleteMapping("/person/{id}")
	public Map<String, Boolean> delete(@PathVariable(value = "id") Integer idPerson)
			throws ResourceNotFoundException, NoSuchMethodException {
		try {
			person_service.delete(idPerson);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
}
