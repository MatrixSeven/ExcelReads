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

import java.util.List;
import java.util.Map;

/**
 * @author Seven
 * FileName: WrapperObj.java
 * Created by Seven on 2019/11/28
 **/
public interface ReaderMap extends ExcelSuperInterface<ReaderMap,Map> {


    List<Map<String, String>> CreateMap() throws Exception;

    Map<String, List<Map<String, String>>> CreateMapLoop() throws Exception;
}
