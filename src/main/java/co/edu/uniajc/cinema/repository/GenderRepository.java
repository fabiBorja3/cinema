package co.edu.uniajc.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniajc.cinema.model.Gender;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Integer> {

}

