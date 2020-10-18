package co.edu.uniajc.cinema.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniajc.cinema.model.Cinema;
import co.edu.uniajc.cinema.repository.CinemaRepository;
import co.edu.uniajc.cinema.exception.ResourceNotFoundException;

@Service
@Transactional
public class CinemaService {

	@Autowired
	private CinemaRepository cinema_Repository;

	/**
	 * getAll() Se lista el contenido de la tabla Cinema
	 * 
	 * @return
	 */
	public List<Cinema> getAll() {
		List<Cinema> listaCinema = new ArrayList<Cinema>();
		try {
			listaCinema = cinema_Repository.findAll();
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return listaCinema;
	}

	/**
	 * getById Se obtiene por medio de Identificador de tabla Cinema
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Cinema getById(Integer idMovie) {
		Cinema movie = new Cinema();
		try {
			if (idMovie > 0) {
				movie = cinema_Repository.findById(idMovie).orElseThrow(
						() -> new ResourceNotFoundException("Cinema no encontrado por Id :: " + idMovie));
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return movie;
	}

	/**
	 * create Se crea objeto en tabla Cinema
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Cinema create(Cinema cinema) {
		Cinema userMovie = new Cinema();
		try {
			if (cinema != null) {
				userMovie = cinema_Repository.save(cinema);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return userMovie;
	}

	/**
	 * edit Se edita objeto en tabla Cinema
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Cinema edit(Integer idCinema, Cinema cinemaDetalle) {
		Cinema cinema = new Cinema();
		try {
			if (idCinema > 0) {
				Cinema getCinema = getById(idCinema);
				if (getCinema != null) {
					getCinema.setName(cinemaDetalle.getName());
					getCinema.setAddress(cinemaDetalle.getAddress());
					getCinema.setPhone(cinemaDetalle.getPhone());
					getCinema.setCity(cinemaDetalle.getCity());
					cinema = cinema_Repository.save(getCinema);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return cinema;
	}

	/**
	 * delete() Se elimina objeto en tabla Cinema
	 * 
	 * @param idUsuario
	 * @return
	 */
	public void delete(Integer id) {
		try {
			if (id > 0) {
				Cinema movie = getById(id);
				if (movie != null) {
					cinema_Repository.delete(movie);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}
