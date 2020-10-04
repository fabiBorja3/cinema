package co.edu.uniajc.cinema.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniajc.cinema.model.Gender;
import co.edu.uniajc.cinema.repository.GenderRepository;
import co.edu.uniajc.cinema.exception.ResourceNotFoundException;

@Service
@Transactional
public class GenderService {

	@Autowired
	private GenderRepository gender_Repository;

	/**
	 * getAll() Se lista el contenido de la tabla genero
	 * 
	 * @return
	 */
	public List<Gender> getAll() {
		List<Gender> listaUsuario = new ArrayList<Gender>();
		try {
			listaUsuario = gender_Repository.findAll();
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return listaUsuario;
	}

	/**
	 * getById Se obtiene por medio de Identificador de tabla genero
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Gender getById(Integer idGender) {
		Gender genero = new Gender();
		try {
			if (idGender > 0) {
				genero = gender_Repository.findById(idGender).orElseThrow(
						() -> new ResourceNotFoundException("Genero no encontrado por Id :: " + idGender));
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return genero;
	}

	/**
	 * create Se crea objeto en tabla usuario
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Gender create(Gender gender) {
		Gender usergender = new Gender();
		try {
			if (gender != null) {
				usergender = gender_Repository.save(gender);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return usergender;
	}

	/**
	 * edit Se edita objeto en tabla genero
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Gender edit(Integer idGender, Gender genderDetalle) {
		Gender user = new Gender();
		try {
			if (idGender > 0) {
				Gender gender = getById(idGender);
				if (gender != null) {
					gender.setName(genderDetalle.getName());
					user = gender_Repository.save(gender);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return user;
	}

	/**
	 * delete() Se elimina objeto en tabla genero
	 * 
	 * @param idUsuario
	 * @return
	 */
	public void delete(Integer idGender) {
		try {
			if (idGender > 0) {
				Gender usuario = getById(idGender);
				if (usuario != null) {
					gender_Repository.delete(usuario);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}
