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

import java.time.LocalDate;

/**
 * @author Seven
 * FileName: DateHandler.java
 * Created by Seven on 2019/12/2
 **/
public class LocalDateHandler implements InPutHandler<LocalDate> {
    @Override
    public LocalDate handlerConvert(String source) {
        return LocalDate.parse(source);
    }
}
