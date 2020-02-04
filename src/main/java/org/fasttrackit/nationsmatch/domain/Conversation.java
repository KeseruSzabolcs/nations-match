package org.fasttrackit.nationsmatch.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity(name = "Conversation")
@Table(name = "conversation")
public class Conversation {

    @Id
    @GeneratedValue
    private Long id;
    private String groupName;
    @NotBlank
    private String userFirstName;
    @NotBlank
    private String userLastName;
    @NotNull
    private LocalDate messageSentDate;
    private boolean sent;
    private boolean seen;
    private int activeChats;
    private String content;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<UserConversation> users = new ArrayList<>();

    public Conversation() {
    }

    public void addUserToConversation(User user) {
        UserConversation userConversation = new UserConversation(this, user);
        users.add(userConversation);
        user.getConversations().add(userConversation);
    }

    public void removeUserFromConversation(User user) {
        for (Iterator<UserConversation> iterator = users.iterator();
             iterator.hasNext(); ) {
            UserConversation userConversation = iterator.next();
            if (userConversation.getConversation().equals(this) && userConversation.getUser().equals(user)) {
                iterator.remove();
                userConversation.getUser().getConversations().remove(userConversation);
                userConversation.setConversation();
                userConversation.setUser(null);
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public LocalDate getMessageSentDate() {
        return messageSentDate;
    }

    public void setMessageSentDate(LocalDate messageSentDate) {
        this.messageSentDate = messageSentDate;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public int getActiveChats() {
        return activeChats;
    }

    public void setActiveChats(int activeChats) {
        this.activeChats = activeChats;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<UserConversation> getUsers() {
        return users;
    }

    public void setUsers(List<UserConversation> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", messageSentDate=" + messageSentDate +
                ", sent=" + sent +
                ", seen=" + seen +
                ", activeChats=" + activeChats +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return false;
        if (o == null || getClass() != o.getClass()) return false;

        Conversation conversation = (Conversation) o;
        return Objects.equals(groupName, conversation.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName);
    }
}
