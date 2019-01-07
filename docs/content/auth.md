[返回目录](../index.md)

# @Auth 注解

## 1.简述

Auth 注解是一个提供用户身份校验功能的注解类，它的工作机制是在访问 Controller 时通过获取并解析请求头上的`Access-Token`来获取当前登陆用户的 Id，@Auth 注解允许传入一个 required 参数来限制身份验证的必须性。

## 2.如何使用？

### 在必须验证身份的接口中

```java
// 查询账户余额
@GetMapping("/money")
public Result getMoney(@Auth Long userId) {
    // 调用业务
}
```

如果访问该接口时请求头中的`Access-Token`不存在或者为空时，会抛出未登录的异常。

> 注意：@Auth 注解默认传出的参数为`Long`类型，如需更改类型，请在`注解参数解析器`源码中修改。

### 在非必须验证身份的接口中

```java
// 获取当前用户的Id，如果用户未登录则结果为null
@GetMapping("/my/id")
public Result getMyId(@Auth(required=false) Long id) {
    return new Result().success(id);
}
```
