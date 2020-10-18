package co.edu.uniajc.cinema.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author norberto.caro
 *
 */
@Entity
@Table(name = "reservation")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "date", nullable = false)
    private Date date;
	
	@OneToOne(targetEntity = Theater.class,cascade= CascadeType.ALL)
	@JoinColumn(name="id_theater", referencedColumnName = "id")
	private Theater Theater;
	
	@OneToOne(targetEntity = Person.class,cascade= CascadeType.ALL)
	@JoinColumn(name="id_person", referencedColumnName = "id")
	private Person person;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Theater getTheater() {
		return Theater;
	}

	public void setTheater(Theater theater) {
		Theater = theater;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
