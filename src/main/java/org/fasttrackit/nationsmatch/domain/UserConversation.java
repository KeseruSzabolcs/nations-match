package org.fasttrackit.nationsmatch.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity(name = "UserConversation")
@Table(name = "user_conversation")
public class UserConversation {

    @EmbeddedId
    private UserConversationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("conversationId")
    private Conversation conversation;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @Column(name = "created_on")
    private Date createdOn = new Date();

    public UserConversation() {
    }

    public UserConversation(Conversation conversation, User user) {
        this.conversation = conversation;
        this.user = user;
        this.id = new UserConversationId(conversation.getId(), user.getId());
    }

    public UserConversationId getId() {
        return id;
    }

    public void setId(UserConversationId id) {
        this.id = id;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation() {
        this.conversation = null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserConversation that = (UserConversation) o;

        return Objects.equals(conversation, that.conversation) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conversation, user);
    }
}
