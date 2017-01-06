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

import java.io.FileOutputStream;
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

    @Override
    public void Save() throws Exception {
        Workbook wk = ExcelTool.newInstance(path, true);
        OutputStream out = new FileOutputStream(path);
        Class<?> clazz = list.get(0).getClass();
        Field[] fields = ExcelTool.GetFilesDeep(clazz);
        String[] title = new String[fields.length];
        short[] align = new short[fields.length];
        ExcelAnno ea = null;
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            //过滤列
            if (filterColBy_key.contains(fields[i].getName())) {
                continue;
            }
            ea = fields[i].getAnnotation(ExcelAnno.class);
            if (ea != null&&!ea.Value().equals("Null")) {
                title[i] = ea.Value();
                align[i] = ea.Align();
                continue;
            }
            title[i] = fields[i].getName();
            align[i] = 0x2;
        }

        if (c != null) {
            list.sort(c);
        }
        Sheet sheet = wk.createSheet("sheet1");
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个样式
        CellStyle style = wk.createCellStyle();
        // 设置这些样式
//        style.setFillPattern(FillPatternType.ALT_BARS);
//        style.setBorderBottom(BorderStyle.THIN);
//        style.setBorderLeft(BorderStyle.THIN);
//        style.setBorderRight(BorderStyle.THIN);
//        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        Row row = sheet.createRow(0);
        for (short i = 0; i < title.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(title[i]);
        }
        int index = 0;
        Object object=null;
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
                cell.setCellValue(object==null?"":object.toString());
            }
        }

        try {
            wk.write(out);
        } finally {
            wk.close();
            out.close();
        }
    }
}
