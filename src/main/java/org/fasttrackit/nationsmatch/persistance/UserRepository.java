package org.fasttrackit.nationsmatch.persistance;

import org.fasttrackit.nationsmatch.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findByFirstNameOrLastName(String partialFirstName, String partialLastName, Pageable pageable);

    Page<User> findAllByAgeBetweenAndNationality(int minAge, int maxAge, String sameNationality, Pageable pageable);

    Page<User> findByAgeBetween(int minAge, int maxAge, Pageable pageable);

    Page<User> findByNationality(String sameNationality, Pageable pageable);

    Optional<User> findByEmail(String userEmail);
}
