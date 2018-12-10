# springboot-restful-starter
SpringBoot RESTful API 脚手架

## 简介
这是一个基于SpringBoot 2.1.1 RELEASE，用于搭建RESTful API工程的脚手架，只需三分钟你就可以开始编写业务代码，不再烦恼于构建项目与风格统一。

## 快速开始
1. 构建数据库
2. 运行`/src/test`下的`CodeGenerator.java`进行代码生成
3. 开始编写业务代码

## 内置功能与使用方法
### RESTful风格Result生成器
1.成功且不带数据的结果
```java
// 不带数据的成功结果
return new Result().success();
```
返回结果示例：
```json
{
    "code": 200,
    "message": "Success",
    "data": null
}
```
2.成功且带返回数据的结果
```java
return new Result().success("Hello,world");

// 当然你也可以返回对象或其他类型的数据
User user = new User();
return new Result().success(user);
```
返回结果示例：
```json
{
    "code": 200,
    "message": "Success",
    "data": "Hello,world"
}
```
或者是：
```json
{
    "code": 200,
    "message": "Success",
    "data": {
        "name": "jack",
        "age": 20
    }
}
```
3.错误结果：
```java
// fail方法的参数(错误代码,错误信息)
return new Result().fail(10400, "登陆失败，密码错误");

// 你还可以自定义错误结果的code
return new Result().fail(null, "未登录", 401);
```
返回结果示例：
```json
{
    "code": 400,
    "message": "登陆失败，密码错误",
    "data": 10400
}
```
或者：
```json
{
    "code": 401,
    "message": "未登录",
    "data": null
}
```
### RESTful风格的异常接管
```java
// 参数说明(错误信息, 错误Code)
throw new ServiceException("未登录", 401);

// 你也可以返回错误代码
throw new ServiceException(10404, "服务器维护中", 404);
```
返回结果示例：
```json
{
    "code": 401,
    "message": "未登录",
    "data": null
}
```
或者：
```json
{
    "code": 404,
    "message": "服务器维护中",
    "data": 10404
}
```
### 基于JWT的认证机制
```java
@Autowired
private TokenService tokenService;

// 生成Payload
Map<String,Object> payload = new HashMap<String,Object>();
payload.put("id",1);
// 生成Token
tokenService.generate(TokenType.ACCESS, payload, 1);


// 格式化Token
String token = getYourToken();
tokenService.parse(token); // 返回的结果是一个Jwt对象，详见JJWT文档
```
### Auth注解（相关说明请见下面的「内置认证机制说明」）
```java
// 在controller中使用
@PostMapping("/user/1/edit")
public Result edit(@Auth int userId, @ResponseBody sthPosted) {
    // 根据ID判断权限
}
```

## 相关依赖
* SpringBoot
* Mybatis Plus
* lombok
* jjwt
* fastjson
* druid

## 相关文档
* [MybatisPlus](https://mp.baomidou.com/)

## License
[MIT](./LICENSE)