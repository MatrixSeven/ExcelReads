package seven.util;

import java.util.HashMap;
import java.util.regex.Pattern;

//=======================================================
//		          .----.
//		       _.'__    `.
//		   .--(^)(^^)---/#\
//		 .' @          /###\
//		 :         ,   #####
//		  `-..__.-' _.-\###/
//		        `;_:    `"'
//		      .'"""""`.
//		     /,  ya ,\\
//		    //狗神保佑  \\
//		    `-._______.-'
//		    ___`. | .'___
//		   (______|______)
//=======================================================
/**
 * @author Seven<p>
 * @date   2016年6月4日-下午4:07:25
 */
public class RegHelper {

	public static final String _REG_INT = "^\\d+$";
	public static final String _REG_STRING = "Null";
	public static final String _REG_FLOAT = "\\d+\\.\\d+";
	public static final String _REG_DATE = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
	public static final String _REG_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	public static final String _REG_PHONE = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
	private static HashMap<String, Pattern> Require_Cache;

	static {
		Require_Cache = new HashMap<String, Pattern>();
		Require_Cache.put("_REG_INT", Pattern.compile(_REG_INT));
		Require_Cache.put("_REG_STRING", Pattern.compile(_REG_STRING));
		Require_Cache.put("_REG_FLOAT", Pattern.compile(_REG_FLOAT));
		Require_Cache.put("_REG_DATE", Pattern.compile(_REG_DATE));
		Require_Cache.put("_REG_EMAIL", Pattern.compile(_REG_EMAIL));
		Require_Cache.put("_REG_PHONE", Pattern.compile(_REG_PHONE));
	}

	public static boolean require(String require, String value) {
		if (Require_Cache.containsKey(require))
			return Require_Cache.get(require).matcher(value).matches();
		Require_Cache.put(require, Pattern.compile(require));
		return Require_Cache.get(require).matcher(value).matches();
	}

}