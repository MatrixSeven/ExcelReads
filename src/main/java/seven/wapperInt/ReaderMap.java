package seven.wapperInt;

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

import seven.ExcelSuperInterface;
import seven.config.Config;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Seven
 * FileName: WrapperObj.java
 * Created by Seven on 2019/11/28
 **/
public interface ReaderMap extends ExcelSuperInterface<ReaderMap,Map> {


    List<Map<String, String>> CreateMap() throws Exception;

    Map<String, Map<String, String>> CreateMapLoop() throws Exception;
}
