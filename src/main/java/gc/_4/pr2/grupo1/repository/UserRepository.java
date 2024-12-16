package gc._4.pr2.grupo1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gc._4.pr2.grupo1.entity.Admin;

public interface UserRepository extends JpaRepository<Admin, Integer>{
    
	Optional<Admin> findByUser(String user);

}
