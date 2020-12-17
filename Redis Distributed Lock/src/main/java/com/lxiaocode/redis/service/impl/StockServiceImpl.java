package com.lxiaocode.redis.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxiaocode.redis.component.RedisLock;
import com.lxiaocode.redis.mapper.StockMapper;
import com.lxiaocode.redis.model.Stock;
import com.lxiaocode.redis.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lixiaofeng
 * @date 2020/12/15 下午11:20
 * @blog http://www.lxiaocode.com/
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {

    @Autowired
    private RedisLock redisLock;

    @Override
    public String updateByProductId(String userId, String productId, int count) {
        if (!redisLock.lock(productId, userId)) {
            return "太多人抢购了，请稍后再试！";
        }
        System.out.println("获取锁");

        try {
            Stock stock = getByProductId(productId);

            if (stock != null) {
                if (stock.getAmount() <= 0) {
                    return "商品出售完了，productId=" + productId;
                }else if (stock.getAmount() < count) {
                    return "商品库存不足，productId=" + productId;
                }
                // 更新库存
                stock.setAmount(stock.getAmount() - count);
                super.lambdaUpdate()
                        .eq(Stock::getProductId, productId).update(stock);
                // 模拟延时
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                return "购买成功，库存还有" + stock.getAmount();

            }else {
                return "获取库存失败!";
            }

        }finally {
            redisLock.release(productId, userId);
            System.out.println("释放锁");
        }
    }

    @Override
    public Stock getByProductId(String productId) {
        return super.lambdaQuery()
                .eq(Stock::getProductId, productId).one();
    }
}
