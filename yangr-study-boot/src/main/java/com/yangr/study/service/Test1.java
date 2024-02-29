package com.yangr.study.service;


import java.util.List;

public class Test1 {
    public String judge(List<String> list) {
        return list.get(0);
    }
    // 增加上述代码健壮性
    public String judge2(List<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }











}
