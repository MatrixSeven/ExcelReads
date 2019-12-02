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

import seven.handler.InPutHandler;
import seven.handler.OutPutHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Seven
 * FileName: LocalDateTimeHandler.java
 * Created by Seven on 2019/12/2
 **/
public class LocalDateTimeHandler implements OutPutHandler<LocalDateTime, String> {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public String handlerConvert(LocalDateTime source) {
        return dateTimeFormatter.format(source);

    }
}
