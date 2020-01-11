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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Seven
 * FileName: LocalDateTimeHandler.java
 * Created by Seven on 2019/12/2
 **/
public class LocalDateTimeHandler implements InPutHandler<LocalDateTime> {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public LocalDateTime handlerConvert(String source) {
        return LocalDateTime.parse(source, dateTimeFormatter);
    }
}
