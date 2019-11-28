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

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import seven.ExcelFactory;
import seven.anno.ExcelAnno;


import java.util.*;
import java.util.List;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2016/11/29.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class Demo {
    @Test
    public void Test_01() throws Exception {

        String filePath = System.getProperty("user.dir").concat("/测试.xlsx");

        Map<String, Map<String, String>> maps = ExcelFactory.getMaps(filePath, it -> it.vocSize(1999)
                                                                                       .title(2)
                                                                                       .content(3)
                                                                                       .isLoopSheet(true))
                                                            .Filter(it -> it.get("在线人数").equals("43"))
                                                            .CreateMapLoop();
        //
        System.out.println(maps);
        //
        String filePath2 = System.getProperty("user.dir").concat("/seven.xlsx");
        List<A> create = ExcelFactory.<A>getBeans(A.class, filePath2)
                .Filter(it->it.getB().equals("19"))
                .FilterCol(it->it.add("姓名"))
                .Create();
        System.out.println(create);
        //                             .Filter(it -> it.getA().equals(""))
        //                             .FilterCol(it -> it.add("1"))
        //                             .Sort(Comparator.comparing(A::getA))
        //                             .Create();
        //System.out.println(create);


    }

    @Test
    public void Test_02() throws Exception {
        List<A> aa = new ArrayList<>();
        aa.add(new A("小明", "15"));
        aa.add(new A("小绿", "13"));
        aa.add(new A("唐山", "18"));
        aa.add(new A("狗东", "15"));
        aa.add(new A("百毒", "12"));
        ExcelFactory.saveExcel(aa, System.getProperty("user.dir").concat("/seven.xlsx"))
                    .Filter(a -> a.getA().equals("唐山"))
                    .Process(a -> a.setA(a.getA().concat("_seven")))
                    .Sort(Comparator.comparing(A::getA))
                    .Flush();


        List<Map<String, String>> m = new ArrayList<>();
        Map<String, String> mm = new HashMap<>();
        mm.put("姓名", "w");
        mm.put("年龄", "w2");
        mm.put("性别", "w3");
        Map<String, String> mmm = new HashMap<>();
        mmm.put("姓名", "23");
        mmm.put("年龄", "w3asf2");
        mmm.put("性别", "w二3");
        m.add(mm);
        m.add(mmm);
        ExcelFactory.saveExcel(m, System.getProperty("user.dir").concat("/SaveMap_.xlsx"))
                    .SetCellStyle("A", cellStyle -> cellStyle.setFillPattern(FillPatternType.DIAMONDS)
                                                             .setAlignment(HorizontalAlignment.RIGHT))
                    .Flush();

    }

    @Test
    public void Test_03() throws Exception {
        Map<String, Map<String, String>> maps = ExcelFactory.getMaps("/home/seven/Desktop/fqlde.xlsx", it -> it.title(0)
                                                                                                               .content(1)
                                                                                                               .isLoopSheet(true))
                                                            .CreateMapLoop();
        System.out.println(maps);


    }
}

class A {
    @ExcelAnno(Value = "姓名")
    private String A;
    @ExcelAnno(Value = "年龄")
    private String B;

    public A() {
    }

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

    @Override
    public String toString() {
        return "A{" + "A='" + A + '\'' + ", B='" + B + '\'' + '}';
    }
}
