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
import seven.wapperInt.wapperRef.sysWppers.ResWrapperMap;

import java.util.HashMap;
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
        getBeans(System.getProperty("user.dir").concat("\\测试.xls"),
                new ResWrapperMap() {
                    @Override
                    protected void LoadConfig(Config config) {
                        config.setContent_row_start(3);
                        config.setTitle_row(2);
                    }
                }).
                Process((HashMap<String, String> o) -> System.out.println(o + "\n")
                ).FilterCol(() -> new String[]{}
        ).Filter((HashMap<String, String> o) -> o.get("创建人") != null && o.get("创建人").length() > 5).Create();

        System.out.println(
        getBeans(System.getProperty("user.dir").concat("\\测试.xls"),
                new ResWrapperMap() {
                    @Override
                    protected void LoadConfig(Config config) {
                        config.setContent_row_start(3);
                        config.setTitle_row(2);
                    }
                }).
                Process((HashMap<String, String> o) ->{}
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
                Process((HashMap<String, String> o) -> {}
                ).FilterCol(() -> new String[]{}
        ).Filter((HashMap<String, String> o) -> o.get("创建人") != null && o.get("创建人").length() > 5).Create();


        System.out.println( ExcelFactory.getBeans(System.getProperty("user.dir").concat("\\测试.xlsx"),
                new ResWrapperMap() {
                    @Override
                    protected void LoadConfig(Config config) {
                        config.setContent_row_start(3);
                        config.setTitle_row(2);
                    }
                }).
                Process((HashMap<String, String> o) -> {}
                ).FilterCol(() -> new String[]{}
        ).Filter((HashMap<String, String> o) -> o.get("创建人") != null &&
                o.get("创建人").length() > 4).<Map>CreateMap("创建人"));


    }


}
