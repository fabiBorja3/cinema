package co.edu.uniajc.cinema.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.uniajc.cinema.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "SELECT * FROM user WHERE nickname = ?1 and password = ?2 ", nativeQuery = true)
	Optional<User> login(String username,String password);
	Optional<User> findByToken(String token);
}

