package cn.bdqn.datacockpit.utils;

import java.util.List;

public class test {
    public static void main(String[] args) {
        ImportExecl poi = new ImportExecl();
        List<List<String>> list = poi.read("C:/Users/Think/Desktop/DT45/dt45team4/ceshibiao.xlsx");
        String lists = list.toString();
        ChineseToPinYin ctp = new ChineseToPinYin();
        String pingyin = ctp.getPingYin(lists);
        System.out.println(pingyin);
        
    }
}
