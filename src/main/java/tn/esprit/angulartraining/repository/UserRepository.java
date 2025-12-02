package tn.esprit.angulartraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.angulartraining.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
