package com.neko.seed.user.data;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class SignInData {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
}
