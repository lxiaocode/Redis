package com.lxiaocode.redis.controller;

import com.lxiaocode.redis.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lixiaofeng
 * @date 2020/12/16 上午12:49
 * @blog http://www.lxiaocode.com/
 */
@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private IStockService stockService;

    @GetMapping("/buy")
    public void buy() {
        System.out.println(stockService.updateByProductId(Thread.currentThread().getName(), "dbc4ca6d6a6a641b4243300903f8af97", 1));
    }
}
