package org.fasttrackit.nationsmatch;

import org.fasttrackit.nationsmatch.domain.User;
import org.fasttrackit.nationsmatch.exeption.ResourceNotFoundException;
import org.fasttrackit.nationsmatch.service.UserService;
import org.fasttrackit.nationsmatch.transfer.CreateUserRequest;
import org.fasttrackit.nationsmatch.transfer.UpdateUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTests {

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser_whenValidRequest_thenUserIsSaved() {
        createUser();
    }

    @Test(expected = TransactionSystemException.class)
    public void testCreateUser_whenInvalidRequest_throwTransactionSystemException() {
        CreateUserRequest request = new CreateUserRequest();
        userService.createUser(request);
    }

    @Test
    public void testGetUser_whenExistingUser_thenReturnUser() {
        User createdUser = createUser();
        User retrievedUser = userService.getUser(createdUser.getId());

        assertThat(retrievedUser, notNullValue());
        assertThat(retrievedUser.getId(), is(createdUser.getId()));
        assertThat(retrievedUser.getFirstName(), is(createdUser.getFirstName()));
        assertThat(retrievedUser.getLastName(), is(createdUser.getLastName()));
        assertThat(retrievedUser.getNationality(), is(createdUser.getNationality()));
        assertThat(retrievedUser.getAge(), is(createdUser.getAge()));
        assertThat(retrievedUser.getDescription(), is(createdUser.getDescription()));
        assertThat(retrievedUser.getEmail(), is(createdUser.getEmail()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetUser_whenNonExistingUser_thenThrowResourceNotFoundException() {
        userService.getUser(9999999);
    }

    @Test
    public void testUpdateUser_whenValidRequest_thenReturnUpdatedUser() {
        User createdUser = createUser();
        UpdateUserRequest request = new UpdateUserRequest();
        request.setFirstName(createdUser.getFirstName() + " next");
        request.setLastName(createdUser.getLastName() + " next");
        request.setNationality(createdUser.getNationality() + " Irish");
        request.setDescription(createdUser.getDescription() + " cold");
        request.setAge(createdUser.getAge() + 2);
        request.setEmail(createdUser.getEmail() + " .org");

        User updatedUser = userService.updateUser(createdUser.getId(), request);

        assertThat(updatedUser, notNullValue());
        assertThat(updatedUser.getId(), is(createdUser.getId()));
        assertThat(updatedUser.getFirstName(), is(request.getFirstName()));
        assertThat(updatedUser.getLastName(), is(request.getLastName()));
        assertThat(updatedUser.getDescription(), is(request.getDescription()));
        assertThat(updatedUser.getNationality(), is(request.getNationality()));
        assertThat(updatedUser.getAge(), is(request.getAge()));
        assertThat(updatedUser.getEmail(), is(request.getEmail()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testUpdateUser_whenInvalidRequest_thenThrowResourceNotFoundException() {
        UpdateUserRequest request = new UpdateUserRequest();
        userService.updateUser(99999999, request);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteUser_whenExistingUser_thenUserIsDeleted() {
        User user = createUser();
        userService.deleteUser(user.getId());
        userService.getUser(user.getId());
    }

    private User createUser() {
        CreateUserRequest request = new CreateUserRequest();
        request.setFirstName("Java");
        request.setLastName("Spring Boot");
        request.setAge(20);
        request.setDescription("Problem generator");
        request.setNationality("Any");
        request.setPassword("oop");
        request.setEmail("oop@yahoo.com");
        request.setImageUrl("this is me");

        User createdUser = userService.createUser(request);

        assertThat(createdUser, notNullValue());
        assertThat(createdUser.getId(), notNullValue());
        assertThat(createdUser.getId(), greaterThan(0L));
        assertThat(createdUser.getFirstName(), is(request.getFirstName()));
        assertThat(createdUser.getLastName(), is(request.getLastName()));
        assertThat(createdUser.getAge(), is(request.getAge()));
        assertThat(createdUser.getDescription(), is(request.getDescription()));
        assertThat(createdUser.getNationality(), is(request.getNationality()));
        assertThat(createdUser.getImageUrl(), is(request.getImageUrl()));
        assertThat(createdUser.getPassword(), is(request.getPassword()));
        return createdUser;
    }
}
