package co.edu.uniajc.cinema.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniajc.cinema.model.User;
import co.edu.uniajc.cinema.repository.UserRepository;
import co.edu.uniajc.cinema.exception.ResourceNotFoundException;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository usuario_Repository;

	/**
	 * getAll() Se lista el contenido de la tabla usuario
	 * 
	 * @return
	 */
	public List<User> getAll() {
		List<User> listaUsuario = new ArrayList<User>();
		try {
			listaUsuario = usuario_Repository.findAll();
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return listaUsuario;
	}

	/**
	 * getById Se obtiene por medio de Identificador de tabla usuario
	 * 
	 * @param idUsuario
	 * @return
	 */
	public User getById(Integer idUsuario) {
		User usuario = new User();
		try {
			if (idUsuario > 0) {
				usuario = usuario_Repository.findById(idUsuario).orElseThrow(
						() -> new ResourceNotFoundException("Usuario no encontrado por Id :: " + idUsuario));
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return usuario;
	}

	/**
	 * create Se crea objeto en tabla usuario
	 * 
	 * @param idUsuario
	 * @return
	 */
	public User create(User usuario) {
		User user = new User();
		try {
			if (user != null) {
				user = usuario_Repository.save(usuario);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return user;
	}

	/**
	 * edit Se edita objeto en tabla usuario
	 * 
	 * @param idUsuario
	 * @return
	 */
	public User edit(Integer idUsuario, User usuarioDetalle) {
		User user = new User();
		try {
			if (idUsuario > 0) {
				User usuario = getById(idUsuario);
				if (usuario != null) {
					usuario.setNickname(usuarioDetalle.getNickname());
					usuario.setPassword(usuarioDetalle.getPassword());
					user = usuario_Repository.save(usuario);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return user;
	}

	/**
	 * delete() Se elimina objeto en tabla usuario
	 * 
	 * @param idUsuario
	 * @return
	 */
	public void delete(Integer idUsuario) {
		try {
			if (idUsuario > 0) {
				User usuario = getById(idUsuario);
				if (usuario != null) {
					usuario_Repository.delete(usuario);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}
