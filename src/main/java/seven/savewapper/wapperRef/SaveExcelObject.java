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

import org.apache.poi.ss.usermodel.Workbook;
import seven.callBack.DataFilterColumnInterface;
import seven.callBack.DataFilterInterface;
import seven.callBack.DataFilterProcessInterface;
import seven.callBack.imp.DefaultDataFilter;
import seven.callBack.imp.DefaultDataProFilter;
import seven.savewapper.SaveExcel;
import seven.util.ExcelTool;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2016/11/30.
 */
public abstract class SaveExcelObject<T> implements SaveExcel {
    protected List<T> list;
    public static final String DEFAULT_TYPE = "xlsx";
    protected String path;
    protected List<String> filterColBy_key = new ArrayList<>();
    protected DataFilterInterface filter = new DefaultDataFilter<Object>();
    protected DataFilterProcessInterface process = new DefaultDataProFilter<Object>();
    protected Comparator<? super Object> c = null;
    protected ResultSet resultSet = null;
    protected OutputStream stream = null;
    protected Boolean isResponse = false;
    protected Workbook wk = null;
    protected HashMap<String, String> convert_title = new HashMap<>();

    public SaveExcelObject(List<T> list, String path) {
        this.list = list;
        this.path = path;
    }

    public SaveExcelObject(List<T> list) {
        this.list = list;
    }

    public SaveExcelObject(ResultSet resultSet, String path) {
        this.resultSet = resultSet;
        this.path = path;
    }

    public SaveExcelObject(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public SaveExcelObject Filter(DataFilterInterface<?> filter) {
        this.filter = filter;
        return this;
    }

    protected Workbook createWK() throws Exception {
        return wk = ExcelTool.newInstance(path.equals("") ? DEFAULT_TYPE : path, true);
    }

    @Override
    public SaveExcelObject Process(DataFilterProcessInterface<?> process) {
        this.process = process;
        return this;
    }

    @Override
    public SaveExcelObject Sort(Comparator<? super Object> c) {
        this.c = c;
        return this;
    }

    @Override
    public SaveExcelObject FilterCol(DataFilterColumnInterface df) {
        for (String s : df.filter()) {
            filterColBy_key.add(s);
        }
        return this;
    }

    protected void checkData() throws Exception {
        if (this.list.isEmpty()) {
            throw new Exception("数据为空，请检查数据源");
        }
    }

    protected OutputStream createStream() throws Exception {
        if (stream == null) {
            if (path == null || path.equals(""))
                throw new Exception("请输入路径");
            return new FileOutputStream(path);
        }
        return stream;
    }

    /**
     * TempName
     */
    private String title_=null;
    protected String convertTitle(String title) throws Exception {
        title_=null;
        title_=convert_title.get(title);
        return title_==null?title:title_;
    }

    @Override
    public SaveExcel SetOutputStream(OutputStream stream) throws Exception {
        this.stream = stream;
        return this;
    }

    @Override
    public void Flush() throws Exception {
        this.Save();
    }

    @Override
    public SaveExcel SetPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public SaveExcel ConvertName(String title, String new_title) {
        convert_title.put(title, new_title);
        return this;
    }

    @Override
    public SaveExcel ConvertName(HashMap<String, String> title_mapping) {
        convert_title.putAll(title_mapping);
        return this;
    }

    @Override
    public SaveExcel ConvertName(HashMap<String, String> title_mapping, Boolean is_init) {
        if (is_init) {
            convert_title.clear();
        }
        return ConvertName(title_mapping);
    }
}
