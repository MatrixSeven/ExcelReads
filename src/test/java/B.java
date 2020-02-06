//=======================================================
//		          .----.
//		       _.'__    `.
//		   .--(^)(^^)---/!\
//		 .' @          /!!!\
//		 :         ,    !!!!
//		  `-..__.-' _.-\!!!/
//		        `;_:    `"'
//		      .'"""""`.
//		     /,  ya ,\\
//		    //狗神保佑\\
//		    `-._______.-'
//		    ___`. | .'___
//		   (______|______)
//=======================================================

import seven.anno.ExcelAnno;

/**
 * @author Seven
 * FileName: B.java
 * Created by Seven on 2019/12/2
 **/
public class B {

    @ExcelAnno(Value = "时间")
    private Integer time;
    @ExcelAnno(Value = "金额")
    private Long money;
    @ExcelAnno(Value = "在线人数")
    private Integer oneLine;
    @ExcelAnno(Value = "服务IP")
    private String ip;
    @ExcelAnno(Value = "地区")
    private String are;
    @ExcelAnno(Value = "创建人")
    private String user;
    @ExcelAnno(Value = "连接类型")
    private String type;
    @ExcelAnno(Value = "岁数")
    private Integer age;


    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Integer getOneLine() {
        return oneLine;
    }

    public void setOneLine(Integer oneLine) {
        this.oneLine = oneLine;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAre() {
        return are;
    }

    public void setAre(String are) {
        this.are = are;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "B{" +
                "time=" + time +
                ", money=" + money +
                ", oneLine=" + oneLine +
                ", ip='" + ip + '\'' +
                ", are='" + are + '\'' +
                ", user='" + user + '\'' +
                ", type='" + type + '\'' +
                ", age=" + age +
                '}';
    }
}
