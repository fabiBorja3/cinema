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
import co.edu.uniajc.cinema.model.Gender;
import co.edu.uniajc.cinema.service.GenderService;
import co.edu.uniajc.cinema.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(value = "Gender", tags = "Gender")
@RestController
@RequestMapping("/api/v1")
public class GenderController {

	@Autowired
	private GenderService UsuarioServ;
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@GetMapping("/gender")
	public List<Gender> getAll() throws NoSuchMethodException, ResourceNotFoundException {
		try {
			return UsuarioServ.getAll();
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@GetMapping("/gender/{id}")
	public ResponseEntity<Gender> getById(@PathVariable(value = "id") Integer idGender) throws NoSuchMethodException {
		Gender gender = new Gender();
		try {
			Util util = new Util();
			boolean estado = util.isNumeric(idGender.toString());
			if (estado == true) {
				gender = UsuarioServ.getById(idGender);
				return ResponseEntity.ok().body(gender);
			}

		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
		return ResponseEntity.ok().body(gender);
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@PostMapping("/gender")
	public Gender create(@Valid @RequestBody Gender gender) throws NoSuchMethodException {
		try {
			return UsuarioServ.create(gender);
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@PutMapping("/gender/{id}")
	public ResponseEntity<Gender> edit(@PathVariable(value = "id") Integer idGender,
			@Valid @RequestBody Gender genderDetalle) throws NoSuchMethodException {
		Gender gender = new Gender();
		try {
			gender = UsuarioServ.edit(idGender, genderDetalle);
			return ResponseEntity.ok(gender);
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@DeleteMapping("/gender/{id}")
	public Map<String, Boolean> delete(@PathVariable(value = "id") Integer idGender)
			throws ResourceNotFoundException, NoSuchMethodException {
		try {
			UsuarioServ.delete(idGender);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
}
