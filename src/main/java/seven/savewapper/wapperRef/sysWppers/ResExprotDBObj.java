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
//		    //π∑…Ò±£””\\
//		    `-._______.-'
//		    ___`. | .'___
//		   (______|______)
//=======================================================

import seven.callBack.PackageDataInterface;
import seven.savewapper.anno.ExcelAnno;
import seven.util.ExcelTool;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2017/1/1.
 */
public class ResExprotDBObj extends ResExprotObj {
    protected PackageDataInterface dataInterface;
    protected Class clazz = null;

    //    protected  T type;
    public ResExprotDBObj(ResultSet resultSet, String path, PackageDataInterface dataInterface) {
        super(resultSet, path);
        this.dataInterface = dataInterface;
//        Type sType = getClass().getGenericSuperclass();
//        Type[] generics = ((ParameterizedType) sType).getActualTypeArguments();
//        Class<T> mTClass = (Class<T>) (generics[0]);
//        try {
//            type = mTClass.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    public ResExprotDBObj(ResultSet resultSet, String path, Class type) {
        super(resultSet, path);
        this.clazz = type;
    }

    public ResExprotDBObj CreateList() throws Exception {
        this.list = new ArrayList<>();
//        Object o;
//        Method[] methods= dataInterface.getClass().getDeclaredMethods();
//        Class<?>[] type= methods[0].getParameterTypes();
//        Class<?> clazz= type[type.length-1].getClass();
        if (clazz != null) {
            Field[] fields= ExcelTool.GetFilesDeep(clazz);
            int len =fields.length;
            String name[]=new String[len];
            ExcelAnno anno=null;
            for (int i = 0; i < len; i++) {
                fields[i].setAccessible(true);
                if((anno=fields[i].getAnnotation(ExcelAnno.class))!=null){
                    name[i]=anno.value();
                    continue;
                }
                name[i]=fields[i].getName();
            }
            Object o=null;
            while (resultSet.next()) {
                o=clazz.newInstance();
                for (int i = 0; i < len; i++) {
                    fields[i].set(o, resultSet.getString(name[i]));
                }
                list.add(o);
            }
            return this;
        }
        while (resultSet.next()) {
            list.add(dataInterface.PackageDataProcess(resultSet));
        }
        return this;
    }

    @Override
    public void Save() throws Exception {
        this.CreateList();
        super.Save();
    }
}
