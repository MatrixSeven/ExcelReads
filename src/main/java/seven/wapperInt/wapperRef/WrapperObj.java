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

import seven.wapperInt.Wrapper;
import seven.callBack.DataFilterColumnInterface;
import seven.callBack.DataFilterInterface;
import seven.callBack.DataFilterProcessInterface;
import seven.callBack.imp.DefaultDataFilter;
import seven.callBack.imp.DefaultDataProFilter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author Seven<p>
 * @date   2016年4月12日-下午4:07:57
 */
public abstract class WrapperObj<T> extends Wrapper {
	protected DataFilterInterface filter=new DefaultDataFilter<Object>();
	protected DataFilterProcessInterface process=new DefaultDataProFilter<Object>();
	protected List<String> filterColBy_key=new ArrayList<>();
	protected List<String> filterColBy_value=new ArrayList<>();
	protected Comparator<? super Object> c=null;
    protected String fs;
	protected static final  boolean isMap=false;

	protected abstract <T> T RefResWapper(String fs,boolean isMap,String key) throws Exception;


	protected boolean isNull(Map<String, String> map) {
		boolean isN = true;
		for (Map.Entry<String, String> s : map.entrySet()) {
			isN = isN && s.getValue().trim().equals("");
		}
		return isN;
	}


	public Wrapper FilterCol(DataFilterColumnInterface df) {
		for (String s:df.filter() ) {
			filterColBy_key.add(s);
		}
		return this;
	}

	public Wrapper init(String FilePath){
		this.fs=FilePath;
		return this;
	}

	public Wrapper Sort(Comparator c) {
		this.c = c;return this;
	}
	public List<T> Create() throws Exception{
		return RefResWapper(fs,isMap,null);
	}

	@Override
	public  <T> T  CreateMap(String key)throws Exception {
		return RefResWapper(fs,!isMap,key);
	}

	public Wrapper Filter(DataFilterInterface<?> filter) {
		this.filter = filter;return this;
	}
	public Wrapper Process(DataFilterProcessInterface<?> process) {
		this.process = process;return this;
	}
}
