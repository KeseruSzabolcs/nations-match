package org.fasttrackit.nationsmatch.persistance;

import org.fasttrackit.nationsmatch.domain.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}
