package seven.handler.implInput;

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

import seven.handler.InPutHandler;

/**
 * @author Seven
 * FileName: LongHandler.java
 * Created by Seven on 2019/12/2
 **/
public class LongHandler implements InPutHandler<Long> {
    @Override
    public Long handlerConvert(String source) {
        return Long.parseLong(source);
    }
}
