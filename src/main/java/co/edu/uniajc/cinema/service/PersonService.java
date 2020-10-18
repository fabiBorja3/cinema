package co.edu.uniajc.cinema.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniajc.cinema.model.Person;
import co.edu.uniajc.cinema.repository.PersonRepository;
import co.edu.uniajc.cinema.exception.ResourceNotFoundException;

@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonRepository person_Repository;

	/**
	 * getAll() Se lista el contenido de la tabla Person
	 * 
	 * @return
	 */
	public List<Person> getAll() {
		List<Person> listPerson = new ArrayList<Person>();
		try {
			listPerson = person_Repository.findAll();
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return listPerson;
	}

	/**
	 * getById Se obtiene por medio de Identificador de tabla Person
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Person getById(Integer idPerson) {
		Person person = new Person();
		try {
			if (idPerson > 0) {
				person = person_Repository.findById(idPerson).orElseThrow(
						() -> new ResourceNotFoundException("Person no encontrado por Id :: " + idPerson));
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return person;
	}

	/**
	 * create Se crea objeto en tabla Person
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Person create(Person person) {
		Person userPerson = new Person();
		try {
			if (person != null) {
				userPerson = person_Repository.save(person);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return userPerson;
	}

	/**
	 * edit Se edita objeto en tabla Person
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Person edit(Integer idPerson, Person personDetalle) {
		Person person = new Person();
		try {
			if (idPerson > 0) {
				Person getPerson = getById(idPerson);
				if (getPerson != null) {
					getPerson.setName(personDetalle.getName());
					getPerson.setLastname(personDetalle.getLastname());
					getPerson.setAddress(personDetalle.getAddress());
					getPerson.setPhone(personDetalle.getPhone());
					getPerson.setMail(personDetalle.getMail());
					person = person_Repository.save(getPerson);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return person;
	}

	/**
	 * delete() Se elimina objeto en tabla Person
	 * 
	 * @param idUsuario
	 * @return
	 */
	public void delete(Integer id) {
		try {
			if (id > 0) {
				Person movie = getById(id);
				if (movie != null) {
					person_Repository.delete(movie);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}
