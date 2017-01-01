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

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2017/1/1.
 */
public class ResExprotDBMap extends ResExprotMap {

    public ResExprotDBMap(ResultSet resultSet, String path) {
        super(resultSet, path);
    }

    public ResExprotMap CreateList() throws Exception {
        this.list = new ArrayList<>();
        HashMap<String, String> stringStringHashMap;
        if (resultSet != null) {
            stringStringHashMap = new HashMap<>();
            ResultSetMetaData res = resultSet.getMetaData();
            int index = res.getColumnCount() + 1;
            while (resultSet.next()) {
                for (int i = 0; i < index; i++) {
                    stringStringHashMap.put(res.getColumnName(i),
                            resultSet.getString(res.getColumnName(i)));
                }
                list.add(stringStringHashMap);
            }
        }
        return this;
    }

    @Override
    public void Save() throws Exception {
        CreateList().Save();
    }
}
