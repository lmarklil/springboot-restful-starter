[返回目录](../index.md)

# RESTful Result 生成

## 1.在 Controller 中返回正常结果

```java
@GetMapping("/hello")
public Result hello() {
    return new Result().success("Hello,world!");
}
```

结果示例

```json
{
  "code": 200,
  "message": "Success",
  "data": "Hello,world!"
}
```

`success`方法的参数可以为空，也可以是任何你想返回的数据类型。

## 2.生成失败的结果

Result 实体内部有 fail 方法，可以快速封装错误的结果，但是在实际运用中，我建议抛出一个 ServiceException，可以传入的参数为(data,message,code)，异常通常在业务层抛出。

```java
@Override
public boolean isLargeNumber(int number) {
    if(number>1000) {
        return true;
    } else if(number>=0&&number<=1000){
        return false;
    } else {
        throw new ServiceException(10400,"数字不能为负数");
    }
}
```

如果传入的数字为复数，则返回的结果为

```json
{
  "code": 400,
  "message": "数字不能为负数",
  "data": 10400
}
```

返回错误结果时，data 可以是错误代码，也可以是你想返回的数据。

## 3.更多用法

为了适应各种结果组合，Result 实体类和 ServiceException 里有多种重载方法，建议阅读源码了解详情。
