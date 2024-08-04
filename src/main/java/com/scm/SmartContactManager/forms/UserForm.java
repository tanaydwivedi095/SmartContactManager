package com.scm.SmartContactManager.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message = "Username is required")
    @Size(min=3, message = "Minimum 3 characters are required")
    private String name;

    @Email(message = "Invalid Email Address")
    private String email;

    @NotBlank(message = "Phone number should not be blank")
    @Size(min=8, max=12, message = "Invalid phone number")
    private String phone;

    @NotBlank(message = "Password should not be blank")
    private String password;

    private String about;
}
