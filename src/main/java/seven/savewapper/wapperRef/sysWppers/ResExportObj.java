package seven.savewapper.wapperRef.sysWppers;
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

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import seven.anno.ExcelAnno;
import seven.savewapper.wapperRef.SaveExcelObject;
import seven.util.ExcelTool;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.List;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2016/11/30.
 */
public class ResExportObj<T> extends SaveExcelObject<T> {

    public ResExportObj(List<T> list, String path) {
        super(list, path);
    }
    public ResExportObj(ResultSet resultSet, String path) {
        super(resultSet, path);
    }
    public ResExportObj(List<T> list){
        super(list);
    };
    public ResExportObj(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    @Deprecated
    public void Save() throws Exception {
        OutputStream out =createStream();
        createWK();
        tryCreateCellStyle();
        checkData();
        Class<?> clazz = list.get(0).getClass();
        Field[] fields = ExcelTool.GetFilesDeep(clazz);
        String[] title = new String[fields.length];
        short[] align = new short[fields.length];
        ExcelAnno ea = null;
        if(anyColByKey.isEmpty()) {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                //过滤列
                if (filterColByKey.contains(fields[i].getName())) {
                    continue;
                }

                ea = fields[i].getAnnotation(ExcelAnno.class);
                if (ea != null) {
                    if(ea.Pass()) {
                        continue;
                    }}
                title[i] = fields[i].getName();
                align[i] = 0x2;
                if (ea != null) {
                    if(ea.Align()!=0x2){
                        align[i] = ea.Align();
                    }
                    if (!ea.Value().equals("Null")) {
                        title[i] = ea.Value();
                    }
                }
            }
        }else {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                if(!anyColByKey.contains(fields[i].getName())){
                    throw new Exception("字段名无效");
                }
            }
            title = anyColByKey.toArray(new String[anyColByKey.size()]);
        }


        if (c != null) {
            list.sort(c);
        }
        Sheet sheet = wk.createSheet("sheet1");
        sheet.setDefaultColumnWidth((short) 15);
        CellStyle style = wk.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        Row row = sheet.createRow(0);
        initTitle(title,row,style);
        int index = 0;
        Object object=null;
        String fn;
        for (T o : list) {
            //过滤行
            if (!filter.test(o)) {
                continue;
            }
            process.accept(o);//加工每一行
            row = sheet.createRow(++index);
            for (int i = 0; i < title.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(style);
                object=fields[i].get(o);
                if(cellStyle.containsKey(title[i])){
                    cell.setCellStyle(cellStyle.get(title[i]).getRealyStyle());
                }
                cell.setCellValue(object==null?"":object.toString());

            }
        }

        try {
            wk.write(out);
            out.flush();
        } finally {
            ExcelTool.Close(wk,out);
        }
    }
}
