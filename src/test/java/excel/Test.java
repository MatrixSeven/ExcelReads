package excel;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import seven.ExcelFactory;
import seven.wapperInt.Wapper.Config;
import seven.wapperInt.anno.ExcelAnno;
import seven.wapperInt.anno.RegHelper;
import seven.wapperInt.wapperRef.sysWppers.ResWapperMap;
import seven.wapperInt.wapperRef.sysWppers.ResWapperObj;

@SuppressWarnings("unused")
@RunWith(BlockJUnit4ClassRunner.class)
public class Test {

	@org.junit.Test
	public void Test1() throws Exception {
		String filePath = "C:\\Users\\seven\\Desktop\\insidebudget.xls";
		String filePath2 = "C:\\Users\\seven\\Desktop\\extbudget.xls";

		List<Map<String, String>> extbudgets = ExcelFactory.getBeans(filePath, new ResWapperMap() {
			@Override
			protected Config LoadConfig() {
				Config config = new Config();
				config.setContent_row_start(2);
				config.setTitle_row(1);
				return config;
			}
		});
		for (Map<String, String> e : extbudgets) {
			System.err.println(e);
		}
	}

	
	@org.junit.Test
	public void Test2() throws Exception {

		String filePath = "C:\\Users\\seven\\Desktop\\测试.xls";
		long s=System.currentTimeMillis();
		List<TestBean> extbudgets2 = ExcelFactory.getBeans(filePath, new ResWapperObj<TestBean>() {
			@Override
			protected Config LoadConfig() {
				Config config = new Config();
				config.setTitle_row(2);
				config.setContent_row_start(3);
				config.setIs_loop_sheet(true);
				config.setVoc_size(50000);
				return config;
			}
		});
		System.out.println("Object:"+((System.currentTimeMillis()-s))+"ms  共"+extbudgets2.size());
		s=System.currentTimeMillis();
		List<Map<String, String>> list2 = ExcelFactory.getBeans(filePath, new ResWapperMap() {
			@Override
			protected Config LoadConfig() {
				Config config = new Config();
				config.setTitle_row(2);
				config.setVoc_size(50000);
				config.setRequire(new String[]{
						"Null","Null","Null","Null","Null","Null","Null","Null"//RegHelper._REG_DATE
				});
				config.setContent_row_start(3);
				return config;
			}
		});
		System.out.println("HashMap:"+((System.currentTimeMillis()-s))+"ms  共"+list2.size());
		System.out.println("测试完成");
	}

}
