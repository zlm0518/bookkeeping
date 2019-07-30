package com.zb.controller;

import com.alibaba.fastjson.JSON;
import com.zb.pojo.Bill;
import com.zb.pojo.BillType;
import com.zb.service.BillService;
import com.zb.service.BillTypeService;
import com.zb.util.RedisName;
import com.zb.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class BillController {
    @Autowired
    private BillService bs ;
    @Autowired
    private BillTypeService ts;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/ji")
    public String ji(){
        return  "add";
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/index")
    public String indexs(){
        return "index";
    }

    @ResponseBody
    @RequestMapping("/bgetAll")
    public String bgetALl( @RequestParam(required = false) Integer type_id ){
        List<Bill> billList = bs.getAll(type_id);
        if(billList.size()==0){
            return "false";
        }
        String json = JSON.toJSONString(billList);
        return json;
    }

    @ResponseBody
    @RequestMapping("/tgetAll")
    public String tgetALl(){
        List<BillType> billList = ts.getAll();
        String json = JSON.toJSONString(billList);
        return json;
    }

    @ResponseBody
    @RequestMapping("/add")
    public String add(@RequestParam String title,@RequestParam String time,@RequestParam Integer type_id,@RequestParam Integer price,@RequestParam(required = false)String explain){
        Bill bill = new Bill();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            bill.setBill_time(sdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        bill.setExplain(explain);
        bill.setPrice(price);
        bill.setTitle(title);
        bill.setType_id(type_id);
        if(bs.add(bill)){
            redisUtil.delete(RedisName.b8);
            redisUtil.delete(RedisName.B(type_id));
            return "true";
        }
        return  "false";
    }


}
