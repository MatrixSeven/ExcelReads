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
public class ResExprotObj extends SaveExcelObject<Object> {

    public ResExprotObj(List<Object> list, String path) {
        super(list, path);
    }
    public ResExprotObj(ResultSet resultSet, String path) {
        super(resultSet, path);
    }
    public ResExprotObj(List<Object> list){
        super(list);
    };
    public ResExprotObj(ResultSet resultSet) {
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
        if(anyColBy_key.isEmpty()) {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                //过滤列
                if (filterColBy_key.contains(fields[i].getName())) {
                    continue;
                }
                ea = fields[i].getAnnotation(ExcelAnno.class);
                title[i] = fields[i].getName();
                if (ea != null && !ea.Value().equals("Null")) {
                    align[i] = ea.Align();
                    continue;
                }
                align[i] = 0x2;
            }
        }else {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                if(!anyColBy_key.contains( fields[i].getName())){
                    throw new Exception("字段名无效");
                }
            }
            title = anyColBy_key.toArray(new String[anyColBy_key.size()]);
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
        for (Object o : list) {
            //过滤行
            if (!filter.filter(o)) {
                continue;
            }
            process.process(o);//加工每一行
            row = sheet.createRow(++index);
            for (int i = 0; i < title.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(style);
                object=fields[i].get(o);
                if(cell_style.containsKey(title[i])){
                    cell.setCellStyle(cell_style.get(title[i]).getRealyStyle());
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
