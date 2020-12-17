package com.lxiaocode.redis.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lixiaofeng
 * @date 2020/12/15 下午11:12
 * @blog http://www.lxiaocode.com/
 */
@Data
@TableName("stock")
public class Stock implements Serializable {
    private static final long serialVersionUID = 7925444797175927166L;

    private String productId;

    private Integer amount;
}
