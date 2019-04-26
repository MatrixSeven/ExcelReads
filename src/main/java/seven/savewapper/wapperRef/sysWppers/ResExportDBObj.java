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

import seven.anno.ExcelAnno;
import seven.callBack.PackageDataInterface;
import seven.util.ExcelTool;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2017/1/1.
 */
public class ResExportDBObj extends ResExportObj {
    protected PackageDataInterface dataInterface;
    protected Class clazz = null;

    public ResExportDBObj(ResultSet resultSet, String path, PackageDataInterface dataInterface) {
        super(resultSet, path);
        this.dataInterface = dataInterface;
    }

    public ResExportDBObj(ResultSet resultSet, String path, Class type) {
        super(resultSet, path);
        this.clazz = type;
    }

    public ResExportDBObj(ResultSet resultSet, Class type) {
        super(resultSet);
        this.clazz = type;
    }

    public ResExportDBObj CreateList() throws Exception {
        this.list = new ArrayList<>();
        if (clazz != null) {
            Field[] fields = ExcelTool.GetFilesDeep(clazz);
            int len = fields.length;
            String[] name = new String[len];
            String[] f_name = new String[len];
            ExcelAnno anno = null;
            for (int i = 0; i < len; i++) {
                fields[i].setAccessible(true);
                if ((anno = fields[i].getAnnotation(ExcelAnno.class)) != null && !anno.Value().equals("Null")) {
                    name[i] = anno.Value();
                    f_name[i] = fields[i].getName();
                    continue;
                }
                f_name[i] = name[i] = fields[i].getName();
            }
            Object o = null;
            while (resultSet.next()) {
                o = clazz.newInstance();
                for (int i = 0; i < len; i++) {
                    fields[i].set(o, resultSet.getString(name[i]));
                }
                list.add(o);
            }
            return this;
        }
        while (resultSet.next()) {
            list.add(dataInterface.packageDataProcess(resultSet));
        }
        return this;
    }

    @Override
    public void Save() throws Exception {
        this.CreateList();
        super.Save();
    }
}
