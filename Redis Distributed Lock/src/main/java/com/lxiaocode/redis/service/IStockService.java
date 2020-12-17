package com.lxiaocode.redis.service;

import com.lxiaocode.redis.model.Stock;

/**
 * @author lixiaofeng
 * @date 2020/12/15 下午11:15
 * @blog http://www.lxiaocode.com/
 */
public interface IStockService {

    String updateByProductId(String userId, String productId, int count);

    Stock getByProductId(String productId);
}
