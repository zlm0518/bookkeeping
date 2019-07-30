package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.dao.BillMapper;
import com.zb.pojo.Bill;
import com.zb.service.BillService;
import com.zb.util.RedisName;
import com.zb.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("billService")
public class BillServiceImpl implements BillService {

    @Autowired
    private BillMapper bm;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean add ( Bill bill ) {
        return bm.add(bill)==1;
    }

    @Override
    public List<Bill> getAll (Integer type_id) {
        String key = null;
        List<Bill> billList = null;
        Map<String,Object> map = new HashMap<>();
        System.out.println("type_id:"+type_id);
        if(type_id==null){
            key = RedisName.b8;
        }else{
            key = RedisName.B(type_id);
            map.put("type_id",type_id);
        }

        if(redisUtil.exists(key)){
            System.out.println("查询缓存");
            String json = redisUtil.get(key).toString();
            billList = JSON.parseArray(json,Bill.class);
        }else{
            System.out.println("查询数据库");
            billList = bm.getAll(map);
           // System.out.println(billList.size());
            String json = JSON.toJSONString(billList);
            redisUtil.set(key,json,60*10);
        }
        return billList;
    }
}
