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

import seven.callBack.DataFilterColumnInterface;
import seven.callBack.DataFilterInterface;
import seven.callBack.DataFilterProcessInterface;
import seven.callBack.imp.DefaultDataFilter;
import seven.callBack.imp.DefaultDataProFilter;
import seven.config.Config;
import seven.wapperInt.Wrapper;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author Seven<p>
 * @date   2016年4月12日-下午4:07:57
 */
@SuppressWarnings("all")
public abstract class WrapperObj<T> extends Wrapper<T> {
	protected DataFilterInterface filter=new DefaultDataFilter();
	protected DataFilterProcessInterface process=new DefaultDataProFilter();
	protected List<String> filterColByKey =new ArrayList<>();
	protected List<String> filterColByValue =new ArrayList<>();
	protected Comparator<? super Object> c=null;
	protected String fs;
	protected static final  boolean isMap=false;

	protected abstract <T> T RefResWrapper(String fs, boolean isMap, String key) throws Exception;


	public WrapperObj(Consumer<Config> consumer) {
		super(consumer);
	}


	protected boolean isNull(Map<String, String> map) {
		boolean isN = true;
		for (Map.Entry<String, String> s : map.entrySet()) {
			isN = isN && s.getValue().trim().equals("");
		}
		return isN;
	}


	@Override
	public Wrapper<T> FilterCol(Consumer<List<String>> consumer) {
		consumer.accept(filterColByKey);
		return this;
	}

	public Wrapper init(String filePath){
		this.fs=filePath;
		return this;
	}

	public Wrapper<T> Sort(Comparator c) {
		this.c = c;return this;
	}
	public List<T> Create() throws Exception{
		return RefResWrapper(fs,isMap,null);
	}

	@Override
	public  List<Map<String,String>>  CreateMap()throws Exception {
		return RefResWrapper(fs,isMap,null);
	}

	@Override
	public Map<String, Map<String, String>> CreateMap(String key) throws Exception {
		return RefResWrapper(fs,!isMap,key);
	}

	@Override
	public Wrapper<T> Filter(DataFilterInterface<? extends T> filter) {
		this.filter = filter;return this;
	}

	@Override
	public Wrapper<T> Process(DataFilterProcessInterface<? extends T> process) {
		this.process = process;return this;
	}
}
