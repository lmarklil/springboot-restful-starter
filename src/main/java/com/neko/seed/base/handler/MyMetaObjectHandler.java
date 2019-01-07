package com.neko.seed.base.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * MybatisPlus自动写入参数配置
 *
 * @author Li Jinhui
 * @update 2018/12/28 10:46
 * @since 2018/12/6 10:00
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 获取UNIX时间戳（精确到秒的10位字符的时间戳）
     */
    private int getCurrentUnixTimestamp() {
        double timestamp = System.currentTimeMillis() / 1000;
        return (int) timestamp;
    }

    /**
     * 自动插入创建时间到create_time字段
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 默认插入的时间戳对应数据库的int类型
        // 若要使用精确到秒级的时间戳请将getCurrentUnixTimestamp()改成System.currentTimeMillis()并在数据库中设置long类型的create_time字段
        this.setFieldValByName("createTime", getCurrentUnixTimestamp(), metaObject);
    }

    /**
     * 自动插入更新时间到update_time字段
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 默认插入的时间戳对应数据库的int类型
        // 若要使用精确到秒级的时间戳请将getCurrentUnixTimestamp()改成System.currentTimeMillis()并在数据库中设置long类型的create_time字段
        this.setFieldValByName("updateTime", getCurrentUnixTimestamp(), metaObject);
    }

}