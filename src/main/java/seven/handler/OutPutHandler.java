package seven.handler;

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

import javax.swing.plaf.IconUIResource;

/**
 * @author Seven
 * FileName: OutPutHandler.java
 * Created by Seven on 2019/12/2
 **/
public interface OutPutHandler<T,R> {

    default R Handler(T source) {
        if(source!=null){
            return handlerConvert(source);
        }
        return null;

    }

    R handlerConvert(T source);

}
