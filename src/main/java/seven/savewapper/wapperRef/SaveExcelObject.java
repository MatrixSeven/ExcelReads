package seven.savewapper.wapperRef;
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

import seven.callBack.DataFilterColumnInterface;
import seven.callBack.DataFilterInterface;
import seven.callBack.DataFilterProcessInterface;
import seven.callBack.imp.DefaultDataFilter;
import seven.callBack.imp.DefaultDataProFilter;
import seven.savewapper.SaveExcel;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2016/11/30.
 */
public abstract class SaveExcelObject<T> implements SaveExcel{
    protected List<T> list;
    protected String path;
    protected List<String> filterColBy_key=new ArrayList<>();
    protected DataFilterInterface filter=new DefaultDataFilter<Object>();
    protected DataFilterProcessInterface process=new DefaultDataProFilter<Object>();
    protected Comparator<? super Object> c=null;
    protected ResultSet resultSet=null;

    public SaveExcelObject(List<T> list, String path) {
        this.list = list;
        this.path = path;
    }
    public SaveExcelObject(ResultSet resultSet, String path) {
        this.resultSet=resultSet;
        this.path = path;
    }

    @Override
    public SaveExcelObject Filter(DataFilterInterface<?> filter) {
        this.filter=filter;
        return this;
    }

    @Override
    public SaveExcelObject Process(DataFilterProcessInterface<?> process) {
        this.process=process;
        return this;
    }

    @Override
    public SaveExcelObject Sort(Comparator<? super Object> c) {
        this.c=c;
        return this;
    }
    @Override
    public SaveExcelObject FilterCol(DataFilterColumnInterface df) {
        for (String s:df.filter() ) {
            filterColBy_key.add(s);
        }
        return this;
    }
    protected void checkData() throws Exception {
        if (this.list.isEmpty()) {
            throw new Exception("数据为空，请检查数据源");
        }
    }
}
