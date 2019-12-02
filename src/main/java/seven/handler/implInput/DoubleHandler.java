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
 * FileName: DoubleHandler.java
 * Created by Seven on 2019/12/2
 **/
public class DoubleHandler implements InPutHandler<Double> {
    @Override
    public Double handlerConvert(String source) {
        return Double.parseDouble(source);
    }
}
