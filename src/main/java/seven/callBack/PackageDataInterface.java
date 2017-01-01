package seven.callBack;
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
//		    //π∑…Ò±£””\\
//		    `-._______.-'
//		    ___`. | .'___
//		   (______|______)
//=======================================================

import java.sql.ResultSet;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2017/1/1.
 */
public interface PackageDataInterface<T> {
    T PackageDataProcess(ResultSet res);
}
