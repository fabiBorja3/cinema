package co.edu.uniajc.cinema.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniajc.cinema.model.City;
import co.edu.uniajc.cinema.repository.CityRepository;
import co.edu.uniajc.cinema.exception.ResourceNotFoundException;

@Service
@Transactional
public class CityService {

	@Autowired
	private CityRepository city_Repository;

	/**
	 * getAll() Se lista el contenido de la tabla City
	 * 
	 * @return
	 */
	public List<City> getAll() {
		List<City> listaCity = new ArrayList<City>();
		try {
			listaCity = city_Repository.findAll();
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return listaCity;
	}

	/**
	 * getById Se obtiene por medio de Identificador de tabla City
	 * 
	 * @param idUsuario
	 * @return
	 */
	public City getById(Integer idCity) {
		City movie = new City();
		try {
			if (idCity > 0) {
				movie = city_Repository.findById(idCity).orElseThrow(
						() -> new ResourceNotFoundException("City no encontrado por Id :: " + idCity));
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return movie;
	}

	/**
	 * create Se crea objeto en tabla City
	 * 
	 * @param idUsuario
	 * @return
	 */
	public City create(City city) {
		City userCity = new City();
		try {
			if (city != null) {
				userCity = city_Repository.save(city);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return userCity;
	}

	/**
	 * edit Se edita objeto en tabla City
	 * 
	 * @param idUsuario
	 * @return
	 */
	public City edit(Integer idCity, City cityDetalle) {
		City city = new City();
		try {
			if (idCity > 0) {
				City getCity = getById(idCity);
				if (getCity != null) {
					getCity.setName(cityDetalle.getName());
					city = city_Repository.save(getCity);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return city;
	}

	/**
	 * delete() Se elimina objeto en tabla City
	 * 
	 * @param idUsuario
	 * @return
	 */
	public void delete(Integer id) {
		try {
			if (id > 0) {
				City movie = getById(id);
				if (movie != null) {
					city_Repository.delete(movie);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}
