package test;

import common.util.WriteToTxtUtil;

import java.util.*;

/**
 * @author : Created by zhq
 * @since : 2017/6/30
 */
public class test {


    public static void main(String[] args) throws  Exception {

   /*     String a = "20170617";
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("=="+format1.format(a));*/

        /*Date date = new SimpleDateFormat("yyyyMMdd").parse("20170617");
        String now = new SimpleDateFormat("yyyy-MM-dd").format(date);
        System.out.println(now);*/

   /*     userVo userVo  =new userVo();
        userVo userVo1  =new userVo();
        userVo.setUid(1);
        userVo1.setUid(2);
        userVo.setUname("zhuhongqiang");
        userVo1.setUname("zhuhongqiang1");
        long mob = 18600405807L;
        userVo.setMobile(mob);
        userVo1.setMobile(13456117L);
        List list = new ArrayList();
        list.add(userVo);
        list.add(userVo1);
        try {
        }catch (Exception e){
            e.printStackTrace();
        }
*/


        /*BeanUtils.copyProperties(userVo,userPo);

        userPo.setMobile(String.valueOf(userVo.getMobile()));
        System.out.println(userPo.toString());
        System.out.println(userPo.getMobile());

        int orderstatus =1;
        String nowname = OrderSatus.getDescByCode(orderstatus).getDesc();
        System.out.println(nowname);*/

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
