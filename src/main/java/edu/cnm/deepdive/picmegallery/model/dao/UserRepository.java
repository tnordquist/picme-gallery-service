package edu.cnm.deepdive.picmegallery.model.dao;

import edu.cnm.deepdive.picmegallery.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findFirstByOauthKey(String oauthkey);
}