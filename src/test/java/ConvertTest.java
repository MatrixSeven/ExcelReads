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
 * FileName: ConverTest.java
 * Created by Seven on 2019/12/2
 **/
public class ConvertTest implements ConvertInterface<String> {
    @Override
    public String convert(Object o) {
        return o.toString()+o.toString();
    }
}
