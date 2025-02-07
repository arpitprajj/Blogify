package com.learn.springboot.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class userDto {


    private int id;
    @NotEmpty
    @Size(min=4,message="Username must be min 4 chars")
    private String name;

    @Email
    private String email;

    @NotEmpty
    @Size(min=3,max=10,message = "Passwaord must be between 3 -10 char")
    private String password;

    @NotEmpty
    private String about;
}
