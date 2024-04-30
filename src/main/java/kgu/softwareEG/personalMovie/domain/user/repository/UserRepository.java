package kgu.softwareEG.personalMovie.domain.user.repository;

import kgu.softwareEG.personalMovie.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findBySocialId(String email);
}
