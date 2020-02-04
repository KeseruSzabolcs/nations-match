package org.fasttrackit.nationsmatch.persistance;

import org.fasttrackit.nationsmatch.domain.UserConversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserConversationRepository extends JpaRepository<UserConversation, Long> {
}
