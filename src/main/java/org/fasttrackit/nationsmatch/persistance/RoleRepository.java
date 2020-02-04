package org.fasttrackit.nationsmatch.persistance;

import org.fasttrackit.nationsmatch.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
