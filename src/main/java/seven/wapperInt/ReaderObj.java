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
public interface ReaderObj<T> extends ExcelSuperInterface<ReaderObj<T>,T> {


    List<T> Create() throws Exception;

    Map<String,List<T>> CreateObjLoop() throws Exception;

}
