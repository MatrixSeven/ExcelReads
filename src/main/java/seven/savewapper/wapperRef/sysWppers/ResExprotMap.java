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
import seven.savewapper.wapperRef.SaveExcelObject;
import seven.util.ExcelTool;

import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.*;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2016/11/30.
 */
public class ResExprotMap extends SaveExcelObject<Map> {
    public ResExprotMap(List<Map> list, String path) {
        super(list, path);
    }
    public ResExprotMap(ResultSet resultSet, String path) {
        super(resultSet, path);
    }
    public ResExprotMap(ResultSet resultSet) {
        super(resultSet);
    }



    @Override
    @Deprecated
    public void Save() throws Exception {
        OutputStream out = createStream();
        createWK();
        checkData();
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
        Map m = list.get(0);
        Set<String> set_title = m.keySet();
        Iterator<String> it = set_title.iterator();
        String t;
        String[] title;
        List<String> l;
        l = new ArrayList();
        while (it.hasNext()) {
            t = it.next();
            if (!filterColBy_key.contains(t)) {
                l.add(t);
            }
        }
        title = l.toArray(new String[l.size()]);

        Row row = sheet.createRow(0);
        for (short i = 0; i < title.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(title[i]);
        }
        int index = 0;
        for (Map<String, String> o : list) {
            if (!filter.filter(o).booleanValue()) {
                continue;
            }
            row = sheet.createRow(++index);
            process.process(o);//加工每一行
            for (int i = 0; i < title.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(style);
                cell.setCellValue(o.get(title[i]));
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
