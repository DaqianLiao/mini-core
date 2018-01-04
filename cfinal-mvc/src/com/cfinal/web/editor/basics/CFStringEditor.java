/**
 * Created the com.cfinal.web.editor.CFStringEditor.java
 * @created 2017年7月25日 下午3:34:40
 * @version 1.0.0
 */
package com.cfinal.web.editor.basics;

import com.cfinal.util.cast.CFCastUtil;
import com.cfinal.web.editor.CFAbstractEditor;

/**
 * com.cfinal.web.editor.CFStringEditor.java
 * @author XChao
 */
public class CFStringEditor extends CFAbstractEditor {

	public Object parse(String text) {
		return CFCastUtil.castString(text);
	}
}
