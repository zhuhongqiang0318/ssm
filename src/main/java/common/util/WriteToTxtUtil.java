package common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Created by zhq
 * @since : 2017/7/4
 */
public class WriteToTxtUtil {
    protected static HttpServletRequest request;
    protected static HttpServletResponse response;
    protected static HttpSession session;

    /**
     * list转json存到 txt文档中
     * @param list
     * @param path_filename
     * @throws Exception
     *   String s = request.getSession().getServletContext().getRealPath("/");//获取路径
     */
    public static void writeListToTxt(List list,String path_filename)throws Exception{
        File file = new File(path_filename);
        file.createNewFile();
        //BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        BufferedWriter bf = new BufferedWriter(new FileWriter(file));
        bf.write(JSON.toJSONString(list));
        bf.flush();
        bf.close();
    }

    /**
     * 读取txt转为list
     * @param path_filename
     * @param data
     */
    public static List writeTxtToList(String path_filename,Object data){
        List list = new ArrayList();
        BufferedReader br = null;//构造一个BufferedReader类来读取文件
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(path_filename));
            String str = null;
            while((str = br.readLine())!=null){//使用readLine方法，一次读一行
                sb.append(str);//System.lineSeparator()换行加空格
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        list = JSONArray.parseArray(sb.toString(),Object.class);
        System.out.println(list);
        return list;
    }
}
