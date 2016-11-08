package seven.wapperInt.wapperRef;



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

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import seven.wapperInt.Wrapper;
import seven.wapperInt.callBack.DataFilterInterface;
import seven.wapperInt.callBack.DataFiterColumnInterface;
import seven.wapperInt.callBack.DataProcessInterface;
import seven.wapperInt.callBack.imp.DefaultDataFiter;
import seven.wapperInt.callBack.imp.DefaultProcess;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author Seven<p>
 * @date   2016年4月12日-下午4:07:57
 */
public abstract class WrapperObj<T> extends Wrapper<T> {
	protected DataFilterInterface filter=new DefaultDataFiter<Object>();
	protected DataProcessInterface process=new DefaultProcess<Object>();
	protected List<String> filterColBy_key=new ArrayList<>();
	protected List<String> filterColBy_value=new ArrayList<>();
	protected Comparator<? super T> c;
	protected POIFSFileSystem fs;
	protected abstract List<T> RefResWapper(POIFSFileSystem fs) throws Exception;
	protected boolean isNull(Map<String, String> map) {
		boolean isN = true;
		for (Map.Entry<String, String> s : map.entrySet()) {
			isN = isN && s.getValue().trim().equals("");
		}
		return isN;
	}

	public Wrapper FiterCol(DataFiterColumnInterface df) {
		for (String s:df.Filter() ) {
			filterColBy_key.add(s);
		}
		return this;
	}


	public Wrapper init(POIFSFileSystem fs){
		this.c=new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return 0;
			}
		};
		this.fs=fs;
		return this;
	}

	public Wrapper Sort(Comparator c) {
		this.c = c;return this;
	}
	public List<T> Creart() throws Exception{
		return RefResWapper(fs);
	}
	public Wrapper Filter(DataFilterInterface<T> filter) {
		this.filter = filter;return this;
	}
	public Wrapper Process(DataProcessInterface<T> process) {
		this.process = process;return this;
	}
}
