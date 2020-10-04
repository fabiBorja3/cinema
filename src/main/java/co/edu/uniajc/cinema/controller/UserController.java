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
import co.edu.uniajc.cinema.model.User;
import co.edu.uniajc.cinema.service.UserService;
import co.edu.uniajc.cinema.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(value = "User", tags = "User")
@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService UsuarioServ;
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@GetMapping("/user")
	public List<User> getAll() throws NoSuchMethodException, ResourceNotFoundException {
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
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getById(@PathVariable(value = "id") Integer idUsuario) throws NoSuchMethodException {
		User usuario = new User();
		try {
			Util util = new Util();
			boolean estado = util.isNumeric(idUsuario.toString());
			if (estado == true) {
				usuario = UsuarioServ.getById(idUsuario);
				return ResponseEntity.ok().body(usuario);
			}

		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
		return ResponseEntity.ok().body(usuario);
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@PostMapping("/user")
	public User create(@Valid @RequestBody User usuario) throws NoSuchMethodException {
		try {
			return UsuarioServ.create(usuario);
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@PutMapping("/user/{id}")
	public ResponseEntity<User> edit(@PathVariable(value = "id") Integer idUsuario,
			@Valid @RequestBody User usuarioDetalle) throws NoSuchMethodException {
		User usuario = new User();
		try {
			usuario = UsuarioServ.edit(idUsuario, usuarioDetalle);
			return ResponseEntity.ok(usuario);
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@DeleteMapping("/user/{id}")
	public Map<String, Boolean> delete(@PathVariable(value = "id") Integer idUsuario)
			throws ResourceNotFoundException, NoSuchMethodException {
		try {
			UsuarioServ.delete(idUsuario);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
}
