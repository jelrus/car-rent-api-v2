package com.backend.service.impl;

import com.backend.dao.UserDao;
import com.backend.exception.CarNotFoundException;
import com.backend.exception.UserNotFoundException;
import com.backend.models.table.User;
import com.backend.service.UserService;
import com.backend.utils.services.LoggerService;

/**
 * Service implementation for managing user-related operations.
 * This includes retrieving user details and checking user existence.
 */
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Retrieves a User by their ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The User associated with the provided ID.
     * @throws UserNotFoundException if no user is found with the specified ID.
     */
    @Override
    public User findByUserId(String userId) {
        LoggerService.info("[UserService | findByUserId] Finding user by id {}", userId);

        // retrieve the user by ID from the database
        User response = userDao.findByUserId(userId);

        // check if the retrieved user is null and handle accordingly
        if (response == null) {
            LoggerService.warn("[UserService | findByUserId] User was not found in the table");
            throw new UserNotFoundException("User not found");
        }
        return response;
    }

    /**
     * Checks if a user exists in the database by their ID.
     *
     * @param userId The ID of the user to check.
     * @throws UserNotFoundException if the user with the specified ID does not exist.
     */
    @Override
    public void existsByUserId(String userId) {
        if (!userDao.existsByUserId(userId)) {
            LoggerService.error("[UserService | Exists By userId] User is not found in the table");
            throw new CarNotFoundException("User not found");
        }
        LoggerService.info("[UserService | Exists By userId] User exists in table");
    }
}
