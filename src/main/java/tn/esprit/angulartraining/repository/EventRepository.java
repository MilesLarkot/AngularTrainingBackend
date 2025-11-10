package tn.esprit.angulartraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.angulartraining.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
