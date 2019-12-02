package seven.callBack.imp;

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

import seven.callBack.ConvertInterface;

/**
 * @author Seven
 * FileName: DefaultConvert.java
 * Created by Seven on 2019/12/2
 **/
public class DefaultConvert implements ConvertInterface<Object> {
    @Override
    public Object convert(Object o) {
        return o;
    }
}
