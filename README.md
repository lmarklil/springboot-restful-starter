# springboot-restful-starter
Spring Boot RESTful 手脚架

## 简介
此项目是一个基于 Springboot 2.1.1 RELEASE 的手脚架项目，用于RESTful风格接口的快速开发，只需三分钟你就可以开始编写业务代码，不再烦恼于构建项目与风格统一。

## 快速开始
1. 创建数据库
2. 运行test目录下的CodeGenerator进行代码生成
3. 开始编写业务代码

## 内置功能
* RESTful风格Result生成器
* RESTful风格的异常接管
* 基于JWT的认证机制
* Auth注解（相关说明请见下面的「内置认证机制说明」）

## 内置认证机制如何使用？
1. 登陆成功后发放AccessToken
··· java
    @GetMapping("/test")
    public Result test() {
    // Do something about sign in
    Map<String,Object> payload = new HashMap<String,Object>();
        payload.put("accountId", 1);
    //generate方法参数解释 1.Token类型 2.Token payload 3.Token有效期
    return new Result().success(tokenService.generate(TokenType.ACCESS,payload,1))
    }
···
2. 在需要认证的接口参数上使用@Auth注解，返回的参数是当前登陆的用户ID，在无Token时，系统会抛出未登录的异常。
···java
    @GetMapping("/me")
    public Result me(@Auth int accountId) {

    }
···

## 相关依赖
* SpringBoot
* Mybatis Plus
* lombok
* jjwt
* fastjson
* druid

## 相关文档
* [MybatisPlus](https://mp.baomidou.com/)