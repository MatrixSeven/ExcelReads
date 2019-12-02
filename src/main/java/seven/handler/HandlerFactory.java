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

import seven.handler.implInput.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Seven
 * FileName: HandlerFactory.java
 * Created by Seven on 2019/12/2
 **/
@SuppressWarnings("all")
public class HandlerFactory {
    private static final Map<Class, InPutHandler> handler = new HashMap<>();
    static {
        reg();
    }

    private static void reg() {
        handler.put(Integer.class, new IntegerHandler());
        handler.put(Double.class, new DoubleHandler());
        handler.put(Long.class, new LongHandler());
        handler.put(LocalDateTime.class, new LocalDateTimeHandler());
        handler.put(LocalDate.class, new LocalDateHandler());
    }

    public static InPutHandler getInPutHandler(Class clazz) {
        return handler.getOrDefault(clazz,it->it);
    }
}
