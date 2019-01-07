[返回目录](../index.md)

# RESTful 全局异常接管

## 1.简述

在此项目中，我利用了 springboot 的特性接管了全局异常，比如说代码出错时接口会返回

```json
{
  "code": 500,
  "message": "Error",
  "data": null
}
```

其他被接管的异常可以阅读源码查看，源码内有详细的注解。（源码在 base 模块中的 handler 包中，名字为 ExceptionHandler）

## 2.自定义异常

当你需要自定义一个 RESTful 的异常时，你无需在 ExceptionHandler 中添加拦截自定义异常，你只需要新建一个异常类，并继承 ServiceException 即可

```java
public class MyException extends ServiceException {
    public MyException() {
        super("这是一个自定义的异常");
    }
}
```

对于 ServiceException 可以传入的参数建议阅读源码了解。
