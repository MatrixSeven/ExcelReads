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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import seven.ExcelFactory;
import seven.savewapper.anno.ExcelAnno;
import seven.wapperInt.wapperRef.sysWppers.ResWrapperMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static seven.ExcelFactory.getBeans;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2016/11/29.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class Demo {
    @Test
    public void Test_01() throws Exception {


        ExcelFactory.getBeans(System.getProperty("user.dir").concat("\\测试.xls"),
                new ResWrapperMap() {
                    @Override
                    protected void LoadConfig(Config config) {
                        config.setContent_row_start(3);
                        config.setTitle_row(2);
                    }
                }). //这里能够处理每一行数据
                Process((HashMap<String, String> o) -> System.out.println(o + "\n")
                //这里能够处理时候过滤某一列
        ).FilterCol(() -> new String[]{}
                //这里能根据某一行的某一列的内容来取舍这行数据
        ).Filter((HashMap<String, String> o) -> o.get("创建人") != null && o.get("创建人").length() > 5
                //排序
        ).Sort((o1, o2) -> o1.hashCode() > o2.hashCode() ? 1 : hashCode() == o2.hashCode() ? 0 : -1).Create();

        System.out.println(
                getBeans(System.getProperty("user.dir").concat("\\测试.xls"),
                        new ResWrapperMap() {
                            @Override
                            protected void LoadConfig(Config config) {
                                config.setContent_row_start(3);
                                config.setTitle_row(2);
                            }
                        }).
                        Process((HashMap<String, String> o) -> {
                                }
                        ).FilterCol(() -> new String[]{}
                ).Filter((HashMap<String, String> o) -> o.get("创建人") != null && o.get("创建人").length() > 5).<Map>CreateMap("创建人"));
        System.err.println("=======================================================================");
        getBeans(System.getProperty("user.dir").concat("\\测试.xlsx"),
                new ResWrapperMap() {
                    @Override
                    protected void LoadConfig(Config config) {
                        config.setContent_row_start(3);
                        config.setTitle_row(2);
                    }
                }).
                Process((HashMap<String, String> o) -> {
                        }
                ).FilterCol(() -> new String[]{}
        ).Filter((HashMap<String, String> o) -> o.get("创建人") != null && o.get("创建人").length() > 5).Create();


        System.out.println(ExcelFactory.getBeans(System.getProperty("user.dir").concat("\\测试.xlsx"),
                new ResWrapperMap() {
                    @Override
                    protected void LoadConfig(Config config) {
                        config.setContent_row_start(3);
                        config.setTitle_row(2);
                    }
                }).
                Process((HashMap<String, String> o) -> {
                        }
                ).FilterCol(() -> new String[]{}
        ).Filter((HashMap<String, String> o) -> o.get("创建人") != null &&
                o.get("创建人").length() > 4).<Map>CreateMap("创建人"));


    }


    @Test
    public void Test_02() throws Exception {
        List<A> aa = new ArrayList<>();
        aa.add(new A("小明", "15"));
        aa.add(new A("小绿", "13"));
        aa.add(new A("唐山", "18"));
        aa.add(new A("狗东", "15"));
        aa.add(new A("百毒", "12"));
        ExcelFactory.saveExcel(aa,System.getProperty("user.dir").concat("\\seven.xlsx"))
                .Filter((A a) ->a.getA().length()==2 )
                .Process((A a)->a.setA(a.getA().concat("_seven")))
                .Save();


        List<Map> m = new ArrayList<>();
        Map mm = new HashMap();
        mm.put("A", "w");
        mm.put("A1", "w2");
        mm.put("A2", "w3");
        Map mmm = new HashMap();
        mmm.put("A", "23");
        mmm.put("A1", "w3asf2");
        mmm.put("A2", "w二3");
        m.add(mm);
        m.add(mmm);
        ExcelFactory.saveExcel(m, System.getProperty("user.dir").concat("\\SaveMap.xlsx")
        ).Save();

    }
}

class A {
    @ExcelAnno(value ="姓名")
    String A;
    @ExcelAnno(value = "年龄")
    String B;

    public A(String a, String b) {
        A = a;
        B = b;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }
}
