package com.neko.seed.user.view;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SignInView {
    private String accessToken;
    private String refreshToken;
}