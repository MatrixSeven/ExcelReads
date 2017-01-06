package seven.util;
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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2016/11/30.
 */
public class ExcelTool {
    private ExcelTool() {
    }

    ;

    public static final Workbook newInstance(String type, boolean isSave) throws Exception {
        File f = new File(type);
        if (isSave) {
            if (type.equals("xls")) {
                return new HSSFWorkbook();
            }
            return new XSSFWorkbook();
        }
        if (!f.isFile()) {
            throw new Exception("请填写正确路径");
        }
        type = type.substring(type.lastIndexOf(".") + 1);
        if (type.equals("xls")) {
            return new HSSFWorkbook(new POIFSFileSystem(f));
        }
        return new XSSFWorkbook(f);
    }

    public static final Field[] GetFilesDeep(Class<?> t) {
        if (!t.getSuperclass().equals(Object.class)) {
            Field fieldSu[] = GetFilesDeep(t.getSuperclass());
            Field fieldSe[] = t.getDeclaredFields();
            Field[] field = new Field[fieldSe.length + fieldSu.length];
            System.arraycopy(fieldSe, 0, field, 0, fieldSe.length);
            System.arraycopy(fieldSu, 0, field, fieldSe.length, fieldSu.length);
            return field;
        }
        return t.getDeclaredFields();
    }

    public static final Method[] GetMethodDeep(Class<?> t) {
        if (!t.getSuperclass().equals(Object.class)) {
            Method MethodSu[] = GetMethodDeep(t.getSuperclass());
            Method MethodSe[] = t.getDeclaredMethods();
            Method[] Method = new Method[MethodSe.length + MethodSu.length];
            System.arraycopy(MethodSe, 0, Method, 0, MethodSe.length);
            System.arraycopy(MethodSu, 0, Method, MethodSe.length, MethodSu.length);
            return Method;
        }
        return t.getDeclaredMethods();
    }
}
