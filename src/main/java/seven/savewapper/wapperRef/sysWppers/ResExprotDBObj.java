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

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2017/1/1.
 */
public class ResExprotDBObj extends ResExprotObj{
    protected PackageDataInterface dataInterface;
//    protected  T type;
    public ResExprotDBObj(ResultSet resultSet, String path, PackageDataInterface dataInterface) {
        super(resultSet, path);
        this.dataInterface=dataInterface;
//        Type sType = getClass().getGenericSuperclass();
//        Type[] generics = ((ParameterizedType) sType).getActualTypeArguments();
//        Class<T> mTClass = (Class<T>) (generics[0]);
//        try {
//            type = mTClass.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    public ResExprotDBObj CreateList() throws Exception {
        this.list=new ArrayList<>();
//        T o= (T) type.getClass().newInstance();
        while (resultSet.next()){
            list.add(dataInterface.PackageDataProcess(resultSet));
        }
        return this;
    }

    @Override
    public void Save() throws Exception {
        CreateList().Save();
    }
}
