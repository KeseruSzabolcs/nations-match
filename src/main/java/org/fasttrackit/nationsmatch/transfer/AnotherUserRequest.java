package org.fasttrackit.nationsmatch.transfer;

import javax.validation.constraints.NotBlank;

public class AnotherUserRequest {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
