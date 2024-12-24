package com.backend.models.dto.request.auth;

import com.google.gson.annotations.Expose;

/**
 * UserSignUpRequest is the data class, serves as the medium object for input in User's sign up request operation.
 */
public class UserSignUpRequest {

    /**
     * Represents user's firstName.
     * Requirements for this field are:
     * - Must not be null
     * - Must not be empty
     * - Must not be blank
     * - Must contain only Latin characters
     * - Optionally contains whitespaces with restrictions:
     *  - Cannot be started with white space
     *  - Cannot be ended with white space
     *  - Cannot be used multiple times between the words
     */
    @Expose
    private String firstName;

    /**
     * Represents user's lastName.
     * Requirements for this field are:
     * - Must not be null
     * - Must not be empty
     * - Must not be blank
     * - Must contain only Latin characters
     * - Optionally contains whitespaces and dashes with restrictions:
     *  - Cannot be started with white space or dash
     *  - Cannot be ended with white space or dash
     *  - Cannot be used multiple times between the words
     */
    @Expose
    private String lastName;

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
     * Getter for firstName field
     *
     * @return {@code String} accessed firstName field
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for firstName field
     *
     * @param firstName {@code String} set firstName field
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for lastName field
     *
     * @return {@code String} accessed lastName field
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for lastName field
     *
     * @param lastName {@code String} set lastName field
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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