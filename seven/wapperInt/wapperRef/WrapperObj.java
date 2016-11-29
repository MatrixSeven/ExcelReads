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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import seven.wapperInt.Wrapper;
import seven.wapperInt.callBack.DataFilterInterface;
import seven.wapperInt.callBack.DataFilterColumnInterface;
import seven.wapperInt.callBack.DataProcessInterface;
import seven.wapperInt.callBack.imp.DefaultDataFiter;
import seven.wapperInt.callBack.imp.DefaultProcess;

import java.io.File;
import java.util.*;

/**
 * @author Seven<p>
 * @date   2016年4月12日-下午4:07:57
 */
public abstract class WrapperObj<T> extends Wrapper {
	protected DataFilterInterface filter=new DefaultDataFiter<Object>();
	protected DataProcessInterface process=new DefaultProcess<Object>();
	protected List<String> filterColBy_key=new ArrayList<>();
	protected List<String> filterColBy_value=new ArrayList<>();
	protected Comparator<? super Object> c;
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
		for (String s:df.Filter() ) {
			filterColBy_key.add(s);
		}
		return this;
	}

    protected Workbook newInstance (String type) throws Exception{
        File f=new File(type);
        if(!f.isFile()){
            throw new Exception("请填写正确路径");
        }
        type=type.substring(type.lastIndexOf(".")+1);
        if(type.equals("xls")){
            return new HSSFWorkbook(new POIFSFileSystem(f));
        }
        return new XSSFWorkbook(f);
    }

	public Wrapper init(String FilePath){
		this.c=new Comparator<Object>() {
			@Override
			public int compare(Object o1,Object o2) {
				return 0;
			}
		};

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
	public Wrapper Process(DataProcessInterface<?> process) {
		this.process = process;return this;
	}
}
