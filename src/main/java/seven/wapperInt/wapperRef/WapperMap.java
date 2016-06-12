package seven.wapperInt.wapperRef;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import seven.wapperInt.Wapper;

public abstract class WapperMap extends Wapper {

	public abstract List<Map<String, String>> RefResWapper(POIFSFileSystem fs) throws Exception;

	protected boolean isNull(Map<String, String> map) {
		boolean isN = true;
		for (Entry<String, String> s : map.entrySet()) {
			isN = isN && s.getValue().trim().equals("");
		}
		return isN;
	}
}
