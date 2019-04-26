package seven.wapperInt;

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

import seven.config.Config;
import seven.wapperInt.wapperRef.WrapperObj;
import seven.wapperInt.wapperRef.sysWppers.ResWrapperMap;
import seven.wapperInt.wapperRef.sysWppers.ResWrapperObj;

import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Seven
 * FileName: WrapperFactory.java
 * Created by Seven on 19-4-26
 **/
@SuppressWarnings("all")
public class WrapperFactory {

    private WrapperFactory(){}

    public static WrapperObj<Map<String,String>> MakeMap(Consumer<Config> consumer){
        return new ResWrapperMap(consumer);
    }

    public static <T> WrapperObj<T> MakeObj(Consumer<Config> consumer,Class<?> clazz){
        return new ResWrapperObj<T>(consumer,clazz);
    }
}
