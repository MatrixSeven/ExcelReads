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
 * FileName: IntagerHandler.java
 * Created by Seven on 2019/12/2
 **/
public class IntegerHandler implements InPutHandler<Integer> {
    @Override
    public Integer handlerConvert(String source) {
        return Integer.parseInt(source);
    }
}
