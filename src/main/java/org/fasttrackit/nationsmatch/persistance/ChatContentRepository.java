package org.fasttrackit.nationsmatch.persistance;

import org.fasttrackit.nationsmatch.domain.ChatContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatContentRepository extends JpaRepository<ChatContent, Long> {
}
