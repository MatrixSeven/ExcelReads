package seven.wapperInt.wapperRef;

import java.util.List;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import seven.wapperInt.Wapper;

//=======================================================
//		          .----.
//		       _.'__    `.
//		   .--(^)(^^)---/#\
//		 .' @          /###\
//		 :         ,   #####
//		  `-..__.-' _.-\###/
//		        `;_:    `"'
//		      .'"""""`.
//		     /,  ya ,\\
//		    //狗神保佑  \\
//		    `-._______.-'
//		    ___`. | .'___
//		   (______|______)
//=======================================================
/**
 * @author Seven<p>
 * @date   2016年4月12日-下午4:07:57
 */
public abstract class WapperObj<T> extends Wapper{
	public abstract List<T> RefResWapper(POIFSFileSystem fs) throws Exception;
}
