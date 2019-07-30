package com.zb.service;

import com.zb.pojo.Bill;

import java.util.List;
import java.util.Map;

public interface BillService {
    boolean add( Bill bill );
    List<Bill> getAll( Integer type_id );
}
