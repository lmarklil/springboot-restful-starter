package com.neko.seed.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neko.seed.auth.enums.TokenSubject;
import com.neko.seed.auth.service.TokenService;
import com.neko.seed.base.exception.ServiceException;
import com.neko.seed.user.data.SignInData;
import com.neko.seed.user.data.SignUpData;
import com.neko.seed.user.entity.User;
import com.neko.seed.user.exception.PasswordErrorException;
import com.neko.seed.user.exception.UserNotFoundException;
import com.neko.seed.user.mapper.UserMapper;
import com.neko.seed.user.service.UserService;
import com.neko.seed.user.view.SignInView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenService tokenService;

    @Override
    public SignInView signIn(SignInData data) {
        // 找到对应name的用户
        User user = userMapper.selectOne(new QueryWrapper<User>().select("id,name,password").eq("name", data.getName()));
        // 判断用户是否存在
        if (user != null) {
            // 校验密码
            if(data.getPassword().equals(user.getPassword())) {
                // 校验通过，登陆成功，返回Token
                SignInView signInView = new SignInView();
                // 生成AccessToken
                signInView.setAccessToken(tokenService.generate(TokenSubject.ACCESS,user.getId()));
                // 生成RrefreshToken，有效期为24小时
                signInView.setRefreshToken(tokenService.generate(TokenSubject.REFRESH,user.getId(),24));
                return signInView;
            } else {
                // 自定义异常示范
                throw new PasswordErrorException();
            }

        } else {
            // 抛出用户不存在的服务异常
            throw new UserNotFoundException();
        }
    }

    @Override
    public void signUp(SignUpData data) {
        // 创建User对象
        User user = new User();
        // 将data的数据复制到user
        BeanUtils.copyProperties(data,user);
        try {
            // 尝试创建用户
            // 建议使用索引约束来判断用户名是否存在，用户存在时会抛出异常，可以自行捕获数据库的异常，并返回用户已存在的错误提示
            userMapper.insert(user);
        } catch (Exception e) {
            // 抛出服务异常
            throw new ServiceException("用户创建失败");
        }
    }
}
