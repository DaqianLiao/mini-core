/**   
 * Created the sn.mini.web.editor.basics.ShortEditor.java
 * @created 2017年10月28日 下午8:21:11 
 * @version 1.0.0 
 */
package sn.mini.web.editor.basics;

import sn.mini.util.lang.TypeUtil;
import sn.mini.web.editor.IEditor;

/**   
 * sn.mini.web.editor.basics.ShortEditor.java 
 * @author XChao  
 */
public class ShortEditor implements IEditor	 {

	@Override
	public Object parse(String text) {
		return TypeUtil.castToShort(text);
	}

}
