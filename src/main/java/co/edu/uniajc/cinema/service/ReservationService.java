package co.edu.uniajc.cinema.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniajc.cinema.model.Reservation;
import co.edu.uniajc.cinema.repository.ReservationRepository;
import co.edu.uniajc.cinema.exception.ResourceNotFoundException;

@Service
@Transactional
public class ReservationService {

	@Autowired
	private ReservationRepository reservation_Repository;

	/**
	 * getAll() Se lista el contenido de la tabla Reservation
	 * 
	 * @return
	 */
	public List<Reservation> getAll() {
		List<Reservation> listPerson = new ArrayList<Reservation>();
		try {
			listPerson = reservation_Repository.findAll();
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return listPerson;
	}

	/**
	 * getById Se obtiene por medio de Identificador de tabla Reservation
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Reservation getById(Integer idReservation) {
		Reservation reservation = new Reservation();
		try {
			if (idReservation > 0) {
				reservation = reservation_Repository.findById(idReservation).orElseThrow(
						() -> new ResourceNotFoundException("Reservation no encontrado por Id :: " + idReservation));
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return reservation;
	}

	/**
	 * create Se crea objeto en tabla Reservation
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Reservation create(Reservation reservation) {
		Reservation userReservation = new Reservation();
		try {
			if (reservation != null) {
				userReservation = reservation_Repository.save(reservation);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return userReservation;
	}

	/**
	 * edit Se edita objeto en tabla Reservation
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Reservation edit(Integer idReservation, Reservation reservationDetalle) {
		Reservation reservation = new Reservation();
		try {
			if (idReservation > 0) {
				Reservation getReservation = getById(idReservation);
				if (getReservation != null) {
					getReservation.setDate(reservationDetalle.getDate());
					getReservation.setTheater(reservationDetalle.getTheater());
					getReservation.setPerson(reservationDetalle.getPerson());
					reservation = reservation_Repository.save(getReservation);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return reservation;
	}

	/**
	 * delete() Se elimina objeto en tabla Reservation
	 * 
	 * @param id
	 * @return
	 */
	public void delete(Integer id) {
		try {
			if (id > 0) {
				Reservation reservation = getById(id);
				if (reservation != null) {
					reservation_Repository.delete(reservation);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}
