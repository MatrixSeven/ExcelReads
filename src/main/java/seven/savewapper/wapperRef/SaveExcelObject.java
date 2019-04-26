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
//		    //狗神保佑 \\
//		    `-._______.-'
//		    ___`. | .'___
//		   (______|______)
//=======================================================

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import seven.callBack.*;
import seven.callBack.imp.DefaultDataFilter;
import seven.callBack.imp.DefaultDataProFilter;
import seven.savewapper.SaveExcel;
import seven.savewapper.cellStyle.CellStyle;
import seven.util.ExcelTool;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2016/11/30.
 */
public abstract class SaveExcelObject<T> implements SaveExcel<T> {
    protected List<T> list;
    public static final String DEFAULT_TYPE = "xlsx";
    protected String path;
    protected List<String> filterColByKey = new ArrayList<>();
    protected List<String> anyColByKey = new ArrayList<>();
    protected DataFilterInterface<T> filter = new DefaultDataFilter<T>();
    protected DataFilterProcessInterface<T> process = new DefaultDataProFilter<T>();
    protected Comparator<? super T> c = null;
    protected ResultSet resultSet = null;
    protected OutputStream stream = null;
    protected Workbook wk = null;
    protected HashMap<String, String> convertTitle = new HashMap<>();
    protected HashMap<String, CellStyle> cellStyle = new HashMap<>();
    private List<CellStyleCallbackWrapper> cellStyleCallbackWrappers = new ArrayList<>();


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
    public SaveExcelObject<T> Filter(DataFilterInterface<T> filter) {
        this.filter = filter;
        return this;
    }

    protected Workbook createWK() throws Exception {
        return wk != null ? wk : (wk = ExcelTool.newInstance(path.equals("") ? DEFAULT_TYPE : path, true));
    }

    @Override
    public SaveExcelObject<T> Process(DataFilterProcessInterface<T> process) {
        this.process = process;
        return this;
    }

    @Override
    public SaveExcelObject<T> Sort(Comparator<? super T> c) {
        this.c = c;
        return this;
    }

    @Override
    public SaveExcelObject<T> FilterCol(Consumer<List<String>> df) {
        df.accept(filterColByKey);
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

    protected String convertTitle(String title) throws Exception {
        String title_ = convertTitle.get(title);
        return title_ == null ? title : title_;
    }

    @Override
    public SaveExcel<T> SetOutputStream(OutputStream stream) throws Exception {
        this.stream = stream;
        return this;
    }

    @Override
    public void Flush() throws Exception {
        this.Save();
    }

    @Override
    public SaveExcel<T> SetPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public SaveExcel<T> ConvertName(String title, String newTitle) {
        convertTitle.put(title, newTitle);
        return this;
    }

    @Override
    public SaveExcel<T> ConvertName(HashMap<String, String> titleMapping) {
        convertTitle.putAll(titleMapping);
        return this;
    }

    @Override
    public SaveExcel<T> ConvertName(HashMap<String, String> titleMapping, Boolean isInit) {
        if (isInit) {
            convertTitle.clear();
        }
        return ConvertName(titleMapping);
    }

    @Override
    public SaveExcel<T> SetCellStyle(String name, CellStyleInterface styleInterface) {
        if (wk == null) {
            cellStyleCallbackWrappers.add(new CellStyleCallbackWrapper(name, styleInterface));
            return this;
        }
        cellStyle.put(name, styleInterface.create(CellStyle.CreateStyle(wk.createCellStyle())));
        return this;
    }

    protected void tryCreateCellStyle() throws Exception {
        if (wk == null) {
            throw new Exception("请输入路径并且初始化WK对象");
        }
        for (CellStyleCallbackWrapper c : cellStyleCallbackWrappers) {
            c.create(wk, cellStyle);
        }
    }

    @Override
    public SaveExcel<T> AnyCol(Consumer<List<String>> df) {
        df.accept(this.anyColByKey);
        return this;
    }


    protected void initTitle(String[] title, Row row, org.apache.poi.ss.usermodel.CellStyle defStyle) throws Exception {
        for (short i = 0; i < title.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(defStyle);
            if (cellStyle.containsKey(title[i])) {
                cell.setCellStyle(cellStyle.get(title[i]).getRealyStyle());
            }
            cell.setCellValue(convertTitle(title[i]));
        }
    }

}
