[返回目录](../index.md)

# 自动填充创建与更新日期

## 简述

这是一个基于[MybatisPlus 特性](https://mp.baomidou.com/guide/auto-fill-metainfo.html)的功能，只要数据库的表中定义了 create_time 和 update_time 字段，在创建和更新数据时 MybatisPlus 会自动把对应的时间戳填充上去。

## 如何使用

1. 在数据库的表中添加 create_time 和 update_time 字段
2. 在对应的 entity 中添加

```java
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Integer updateTime;
```

> 注意：为了节省数据库的存储空间，这里默认生成的时间戳是 UNIX 时间戳（精确到秒的 10 位数的时间戳），若需要精确到毫秒的时间戳，可以自行修改源码。
