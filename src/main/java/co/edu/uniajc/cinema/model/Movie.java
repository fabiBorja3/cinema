package co.edu.uniajc.cinema.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author norberto.caro
 *
 */
@Entity
@Table(name = "movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
 
	@Column(name = "title", nullable = false)
    private String title;
	
	@Column(name ="classification", nullable = false)
	private String classification;
	
	@Column(name = "trailer", nullable = false)
    private String trailer;
	
	@Column(name = "website", nullable = false)
    private String website;
	
	@Column(name = "release_Date", nullable = false)
    private Date releaseDate;
	
	@Column(name = "director", nullable = false)
    private String director;
	
	@Column(name = "original_language", nullable = false)
    private String originalLanguage;
	
	@Column(name = "sypnosys", nullable = false)
    private String sypnosys;
	
	@OneToOne(targetEntity = Gender.class,cascade= CascadeType.ALL)
	@JoinColumn(name="id_gender", referencedColumnName = "id")
	private Gender gender;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public String getSypnosys() {
		return sypnosys;
	}

	public void setSypnosys(String sypnosys) {
		this.sypnosys = sypnosys;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
