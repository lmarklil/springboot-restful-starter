package com.neko.seed.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neko.seed.user.data.SignInData;
import com.neko.seed.user.data.SignUpData;
import com.neko.seed.user.entity.User;
import com.neko.seed.user.view.SignInView;

public interface UserService extends IService<User> {
    SignInView signIn(SignInData data);
    void signUp(SignUpData data);
}
