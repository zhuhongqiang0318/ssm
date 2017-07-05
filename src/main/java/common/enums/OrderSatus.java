package common.enums;

/**
 * @author : Created by zhq
 * @since : 2017/6/30
 */
public enum OrderSatus {

    Pay_OFF(1,"未支付"),
    Pay_ON(2,"支付中"),
    Pay_YES(3,"已支付"),
    ;

    private int code;
    private String desc;
    OrderSatus(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据key 获取对应的值
     * @param code
     * @return
     */
    public static OrderSatus getDescByCode(int code){
        OrderSatus [] Ordersatus = OrderSatus.values();
        for (int i=0;i<Ordersatus.length;i++){
            if (Ordersatus[i].getCode()==code){
                return Ordersatus[i];
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 根据状态值获取状态
     * @param args
     */
    public static void main(String[] args) {
        int status =2;
        String nowname = OrderSatus.getDescByCode(status).getDesc();
        System.out.println(nowname);
    }
}
