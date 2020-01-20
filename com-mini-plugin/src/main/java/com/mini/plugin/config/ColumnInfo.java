package com.mini.plugin.config;

import com.google.gson.annotations.Expose;
import com.intellij.database.model.DasColumn;

import java.io.Serializable;
import java.util.EventListener;

public final class ColumnInfo implements Serializable, EventListener {
	private String columnName, fieldName, comment, dbType, fieldType;
	private boolean auto, id, ref, createAt, updateAt, del, lock;
	@Expose(serialize = false, deserialize = false)
	private DasColumn column;
	private int delValue;
	private boolean ext;
	
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	
	public void setCreateAt(boolean createAt) {
		this.createAt = createAt;
	}
	
	public void setUpdateAt(boolean updateAt) {
		this.updateAt = updateAt;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public void setColumn(DasColumn column) {
		this.column = column;
	}
	
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	
	public void setDelValue(int delValue) {
		this.delValue = delValue;
	}
	
	public void setAuto(boolean auto) {
		this.auto = auto;
	}
	
	public void setLock(boolean lock) {
		this.lock = lock;
	}
	
	public void setRef(boolean ref) {
		this.ref = ref;
	}
	
	public void setDel(boolean del) {
		this.del = del;
	}
	
	public void setExt(boolean ext) {
		this.ext = ext;
	}
	
	public void setId(boolean id) {
		this.id = id;
	}
	
	public String getColumnName() {
		return columnName;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public String getFieldType() {
		return fieldType;
	}
	
	public String getComment() {
		return comment;
	}
	
	public String getDbType() {
		return dbType;
	}
	
	public boolean isCreateAt() {
		return createAt;
	}
	
	public boolean isUpdateAt() {
		return updateAt;
	}
	
	public DasColumn getColumn() {
		return column;
	}
	
	public int getDelValue() {
		return delValue;
	}
	
	public boolean isAuto() {
		return auto;
	}
	
	public boolean isLock() {
		return lock;
	}
	
	public boolean isRef() {
		return ref;
	}
	
	public boolean isDel() {
		return del;
	}
	
	public boolean isExt() {
		return ext;
	}
	
	public boolean isId() {
		return id;
	}
}
