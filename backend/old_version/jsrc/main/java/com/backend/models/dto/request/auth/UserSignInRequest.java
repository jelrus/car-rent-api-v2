package com.backend.models.dto.request.auth;

import com.google.gson.annotations.Expose;

/**
 * UserSignInRequest is the data class, serves as the medium object for input in User's sign in request operation.
 */
public class UserSignInRequest {

    /**
     * Represents user's account email.
     * Requirements for this field are:
     * - Must not be null
     * - Must not be empty
     * - Must not be blank
     * - Must comprise with email regex pattern
     */
    @Expose
    private String email;

    /**
     * Represents user's account password.
     * Requirements for this field are:
     * - Must not be null
     * - Must not be empty
     * - Must not be blank
     * - Must contain at least 1 uppercase character
     * - Must contain at least 1 lowercase character
     * - Must contain at least 1 number character
     * - Must be at least 8 characters long
     */
    @Expose
    private String password;

    /**
     * Getter for email field
     *
     * @return {@code String} accessed email field
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email field
     *
     * @param email {@code String} set email field
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for password field
     *
     * @return {@code String} accessed password field
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password field
     *
     * @param password {@code String} set password field
     */
    public void setPassword(String password) {
        this.password = password;
    }
}