package org.fasttrackit.nationsmatch.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.nationsmatch.domain.User;
import org.fasttrackit.nationsmatch.exeption.ResourceNotFoundException;
import org.fasttrackit.nationsmatch.persistance.UserRepository;
import org.fasttrackit.nationsmatch.transfer.CreateUserRequest;
import org.fasttrackit.nationsmatch.transfer.GetUsersRequest;
import org.fasttrackit.nationsmatch.transfer.UpdateUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserService(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    public User createUser(CreateUserRequest request) {
        LOGGER.info("Creating user: {}", request);
        User user = objectMapper.convertValue(request, User.class);
        return userRepository.save(user);
    }

    public User getUser(long id) {
        LOGGER.info("Retrieving user {}", id);
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException
                ("User " + id + " does not exist."));
    }

    public Page<User> getUsers(GetUsersRequest request, Pageable pageable) {
        LOGGER.info("Retrieving Users {}", request);
        User user = new User();
        if (request != null && request.getPartialFirstName() != null || request.getPartialLastName() != null) {
            return userRepository.findByFirstNameOrLastName(request.getPartialFirstName(),
                    request.getPartialLastName(), pageable);
        } else if (request.getMinAge() != null && request.getMaxAge() != null && request.getSameNationality() != null) {
            return userRepository.findAllByAgeBetweenAndNationality(request.getMinAge(),
                    request.getMaxAge(), request.getSameNationality(), pageable);
        } else if (request.getMinAge() != null && request.getMaxAge() != null) {
            return userRepository.findByAgeBetween(request.getMinAge(), request.getMaxAge(), pageable);
        } else if (request.getSameNationality() != null) {
            return userRepository.findByNationality(request.getSameNationality(), pageable);
        } else {
            return userRepository.findAll(pageable);
        }
    }

    public User updateUser(long id, UpdateUserRequest request) {
        LOGGER.info("Updating user {}: {}", id, request);
        User user = getUser(id);
        BeanUtils.copyProperties(request, user);
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        LOGGER.info("Deleting user {}", id);
        userRepository.deleteById(id);
        LOGGER.info("Deleted user {}", id);
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws ResourceNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        optionalUser.orElseThrow(() -> new ResourceNotFoundException("User with name "
                + userEmail + " does not exist."));
        return optionalUser.map(org.fasttrackit.nationsmatch.domain.UserDetails::new).get();
    }
}
