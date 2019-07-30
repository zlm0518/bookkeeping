package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.dao.BillTypeMapper;
import com.zb.pojo.Bill;
import com.zb.pojo.BillType;
import com.zb.service.BillTypeService;
import com.zb.util.RedisName;
import com.zb.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("billTypeService")
public class BillTypeServiceImpl implements BillTypeService {

    @Autowired
    private BillTypeMapper bm;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<BillType> getAll () {
        List<BillType> list = null;
        String key = RedisName.t1;
        if(redisUtil.exists(key)){
            System.out.println("查询t缓存");
            String json = redisUtil.get(key).toString();
            list = JSON.parseArray(json, BillType.class);

        }else{
            System.out.println("查询t数据库");
            list = bm.getAll();
            String json = JSON.toJSONString(list);
            redisUtil.set(key,json,60*10);
        }
        return list;
    }
}
