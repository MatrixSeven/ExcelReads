package seven.handler.implOutput;

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

import org.apache.poi.xwpf.usermodel.BreakType;
import seven.handler.OutPutHandler;

/**
 * @author Seven
 * FileName: BaseOutPutHandler.java
 * Created by Seven on 2019/12/2
 **/
public class BaseOutPutHandler implements OutPutHandler<Object, Object> {
    @Override
    public Object handlerConvert(Object source) {
        return source;
    }
}
