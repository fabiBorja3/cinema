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
import co.edu.uniajc.cinema.model.Reservation;
import co.edu.uniajc.cinema.service.ReservationService;
import co.edu.uniajc.cinema.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(value = "Reservation", tags = "Reservation")
@RestController
@RequestMapping("/api/v1")
public class ReservationController {

	@Autowired
	private ReservationService reservation_service;
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@GetMapping("/reservation")
	public List<Reservation> getAll() throws NoSuchMethodException, ResourceNotFoundException {
		try {
			return reservation_service.getAll();
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@GetMapping("/reservation/{id}")
	public ResponseEntity<Reservation> getById(@PathVariable(value = "id") Integer idReservation) throws NoSuchMethodException {
		Reservation reservation = new Reservation();
		try {
			Util util = new Util();
			boolean estado = util.isNumeric(idReservation.toString());
			if (estado == true) {
				reservation = reservation_service.getById(idReservation);
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
	@PostMapping("/reservation")
	public Reservation create(@Valid @RequestBody Reservation reservation) throws NoSuchMethodException {
		try {
			return reservation_service.create(reservation);
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@PutMapping("/reservation/{id}")
	public ResponseEntity<Reservation> edit(@PathVariable(value = "id") Integer idReservation,
			@Valid @RequestBody Reservation reservationDetalle) throws NoSuchMethodException {
		Reservation reserv = new Reservation();
		try {
			reserv = reservation_service.edit(idReservation, reservationDetalle);
			return ResponseEntity.ok(reserv);
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@DeleteMapping("/reservation/{id}")
	public Map<String, Boolean> delete(@PathVariable(value = "id") Integer idReservation)
			throws ResourceNotFoundException, NoSuchMethodException {
		try {
			reservation_service.delete(idReservation);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
}
