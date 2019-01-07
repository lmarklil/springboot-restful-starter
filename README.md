# springboot-restful-starter

SpringBoot RESTful API 脚手架

## 简介

这是一个基于`SpringBoot 2.1.1 RELEASE`，用于搭建`RESTful API`工程的脚手架，只需`三分钟`你就可以开始编写业务代码，不再烦恼于构建项目与风格统一。

## 快速开始

1. 构建数据库
2. 运行`/src/test`下的`CodeGenerator.java`进行代码生成
3. 开始编写业务代码

## 快速入门

实践是最好的学习方式，在 2.0 版本中，我编写了一个简单的账号模块供大家参考学习。

## 建议

**如果大家有使用这个项目，建议`watch`并选择`Releases Only`以便即时的收到正式的更新**

## [文档](./docs/index.md)

文档内提供了详细的功能说明，强烈建议阅读文档后再开始编码。

## [更新日志](./docs/changelog.md)

当前版本：2.0.0 Beta

## 基本功能

- 封装了 RESTful 风格的返回结果
- 全局接管异常，并使用 RESTful 风格返回异常信息
- 简单封装了 jjwt，提供了一个开箱即用的认证模块
- @Auth 注解，用注解的方式校验用户信息
- 集成了常用的库，详见相关依赖
- 集成了 MybatisPlus

## 相关依赖

- SpringBoot
- MybatisPlus
- lombok
- jjwt
- fastjson
- druid

## 捐赠

如果你觉得这个项目很赞且对你有帮助的话，请我喝一杯咖啡呗:)
![捐赠二维码](./docs/img/donation.png)

## License

[MIT](./LICENSE)
