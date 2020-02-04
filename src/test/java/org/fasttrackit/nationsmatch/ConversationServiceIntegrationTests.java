package org.fasttrackit.nationsmatch;

import org.fasttrackit.nationsmatch.domain.Conversation;
import org.fasttrackit.nationsmatch.exeption.ResourceNotFoundException;
import org.fasttrackit.nationsmatch.service.ConversationService;
import org.fasttrackit.nationsmatch.transfer.SaveConversationRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConversationServiceIntegrationTests {

    @Autowired
    private ConversationService conversationService;

    @Test
    public void testCreateConversation_whenValidRequest_thanConversationIsSaved() {
        createConversation();
    }

    @Test
    public void testGetConversation_whenExistingConversation_thenReturnConversation() {
        Conversation createdConversation = createConversation();
        Conversation retrievedConversation = conversationService.getConversation(createdConversation.getId());

        assertThat(retrievedConversation, notNullValue());
        assertThat(retrievedConversation.getId(), is(createdConversation.getId()));
        assertThat(retrievedConversation.getUserFirstName(), is(createdConversation.getUserFirstName()));
        assertThat(retrievedConversation.getUserLastName(), is(createdConversation.getUserLastName()));
        assertThat(retrievedConversation.getGroupName(), is(createdConversation.getGroupName()));
        assertThat(retrievedConversation.getMessageSentDate().plusDays(1), is(createdConversation.getMessageSentDate()));
        assertThat(retrievedConversation.isSeen(), is(createdConversation.isSeen()));
        assertThat(retrievedConversation.isSent(), is(createdConversation.isSent()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetConversation_whenNonExistingConversation_thenThrowResourceNotFoundException() {
        conversationService.getConversation(9999999);
    }

    @Test
    public void testUpdateConversation_whenValidRequest_thenReturnUpdatedConversation() {
        Conversation createdConversation = createConversation();
        SaveConversationRequest request = new SaveConversationRequest();
        request.setUserFirstName(createdConversation.getUserFirstName() + " updated");
        request.setUserLastName(createdConversation.getUserLastName() + " updated");
        request.setMessageSentDate(createdConversation.getMessageSentDate());
        request.setGroupName(createdConversation.getGroupName() + " Music");

        Conversation updatedConversation = conversationService.updateConversation(createdConversation.getId(), request);

        assertThat(updatedConversation, notNullValue());
        assertThat(updatedConversation.getId(), is(createdConversation.getId()));
        assertThat(updatedConversation.getUserFirstName(), is(request.getUserFirstName()));
        assertThat(updatedConversation.getUserLastName(), is(request.getUserLastName()));
        assertThat(updatedConversation.getMessageSentDate(), is(request.getMessageSentDate()));
        assertThat(updatedConversation.getGroupName(), is(request.getGroupName()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testUpdateConversation_whenInvalidRequest_thenThrowResourceNotFoundException() {
        SaveConversationRequest request = new SaveConversationRequest();
        conversationService.updateConversation(99999999, request);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteConversation_whenExistingConversation_thenConversationIsDeleted() {
        Conversation conversation = createConversation();
        conversationService.deleteConversation(conversation.getId());
        conversationService.getConversation(conversation.getId());
    }

    private Conversation createConversation() {
        SaveConversationRequest request = new SaveConversationRequest();
        request.setUserFirstName("John");
        request.setUserLastName("Bon-Jovi");
        request.setGroupName("Rock-n-roll");
        request.setMessageSentDate(LocalDate.of(2020, 1, 1));
        request.setSeen(true);
        request.setSent(true);

        Conversation createdConversation = conversationService.createConversation(request);

        assertThat(createdConversation, notNullValue());
        assertThat(createdConversation.getId(), notNullValue());
        assertThat(createdConversation.getId(), greaterThan(0L));
        assertThat(createdConversation, notNullValue());
        assertThat(createdConversation.getId(), is(createdConversation.getId()));
        assertThat(createdConversation.getUserFirstName(), is(createdConversation.getUserFirstName()));
        assertThat(createdConversation.getUserLastName(), is(createdConversation.getUserLastName()));
        assertThat(createdConversation.getGroupName(), is(createdConversation.getGroupName()));
        assertThat(createdConversation.getMessageSentDate(), is(createdConversation.getMessageSentDate()));
        assertThat(createdConversation.getActiveChats(), is(createdConversation.getActiveChats()));
        assertThat(createdConversation.isSeen(), is(createdConversation.isSeen()));
        assertThat(createdConversation.isSent(), is(createdConversation.isSent()));

        return createdConversation;
    }
}
