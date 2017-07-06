package test;

import common.util.WriteToTxtUtil;

import java.util.*;

/**
 * @author : Created by zhq
 * @since : 2017/6/30
 */
public class test {


    public static void main(String[] args) throws  Exception {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(11);
        list.add(12);
        list.add(13);
        Integer integer = new Integer(1);
        WriteToTxtUtil.writeListToTxt(list,"D:\\opt\\test.txt");
        WriteToTxtUtil.writeTxtToList("D:\\opt\\test.txt",integer);
    }
}
