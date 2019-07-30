package com.zb.dao;

import com.zb.pojo.Bill;
import javafx.beans.binding.ObjectExpression;

import java.util.List;
import java.util.Map;

public interface BillMapper {
    int add( Bill bill );
    List<Bill> getAll( Map<String, Object> map );
}
