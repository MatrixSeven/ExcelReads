package seven.callBack;
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

import org.apache.poi.ss.usermodel.Workbook;
import seven.savewapper.cellStyle.CellStyle;

import java.util.Map;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2017/1/11.
 */
public final class CellStyleCallbackWrapper {
    CellStyleInterface cellStyle;
    String name;
    public CellStyleCallbackWrapper(String name, CellStyleInterface cellStyle) {
        this.cellStyle = cellStyle;
        this.name = name;
    }
    public void create(Workbook wk, Map<String, CellStyle> cellStyleMap) throws Exception {
        cellStyleMap.put(name, cellStyle.create(CellStyle.CreateStyle(wk.createCellStyle())));
    }

}
