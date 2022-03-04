package app.repositories;

import app.entities.BuddyInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuddyRepository extends CrudRepository<BuddyInfo, Long> {}