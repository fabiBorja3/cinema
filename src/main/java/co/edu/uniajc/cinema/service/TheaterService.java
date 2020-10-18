package co.edu.uniajc.cinema.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniajc.cinema.model.Theater;
import co.edu.uniajc.cinema.repository.TheaterRepository;
import co.edu.uniajc.cinema.exception.ResourceNotFoundException;

@Service
@Transactional
public class TheaterService {

	@Autowired
	private TheaterRepository reservation_Repository;

	/**
	 * getAll() Se lista el contenido de la tabla Theater
	 * 
	 * @return
	 */
	public List<Theater> getAll() {
		List<Theater> listTheater = new ArrayList<Theater>();
		try {
			listTheater = reservation_Repository.findAll();
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return listTheater;
	}

	/**
	 * getById Se obtiene por medio de Identificador de tabla Theater
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Theater getById(Integer idTheater) {
		Theater theater = new Theater();
		try {
			if (idTheater > 0) {
				theater = reservation_Repository.findById(idTheater).orElseThrow(
						() -> new ResourceNotFoundException("Theater no encontrado por Id :: " + idTheater));
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return theater;
	}

	/**
	 * create Se crea objeto en tabla Theater
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Theater create(Theater theater) {
		Theater userTheater = new Theater();
		try {
			if (theater != null) {
				userTheater = reservation_Repository.save(theater);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return userTheater;
	}

	/**
	 * edit Se edita objeto en tabla Theater
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Theater edit(Integer idTheater, Theater theaterDetalle) {
		Theater reservation = new Theater();
		try {
			if (idTheater > 0) {
				Theater getTheater = getById(idTheater);
				if (getTheater != null) {
					getTheater.setTotalCapacity(theaterDetalle.getTotalCapacity());
					getTheater.setMovie(theaterDetalle.getMovie());
					reservation = reservation_Repository.save(getTheater);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return reservation;
	}

	/**
	 * delete() Se elimina objeto en tabla Theater
	 * 
	 * @param id
	 * @return
	 */
	public void delete(Integer id) {
		try {
			if (id > 0) {
				Theater reservation = getById(id);
				if (reservation != null) {
					reservation_Repository.delete(reservation);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}
