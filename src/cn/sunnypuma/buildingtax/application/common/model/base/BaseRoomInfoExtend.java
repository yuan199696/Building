package cn.sunnypuma.buildingtax.application.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseRoomInfoExtend<M extends BaseRoomInfoExtend<M>> extends Model<M> implements IBean {

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.String getId() {
		return getStr("id");
	}

	public M setFieldName(java.lang.String fieldName) {
		set("field_name", fieldName);
		return (M)this;
	}
	
	public java.lang.String getFieldName() {
		return getStr("field_name");
	}

	public M setStatus(java.lang.Long status) {
		set("status", status);
		return (M)this;
	}
	
	public java.lang.Long getStatus() {
		return getLong("status");
	}

	public M setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
		return (M)this;
	}
	
	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public M setCreateUser(java.lang.String createUser) {
		set("create_user", createUser);
		return (M)this;
	}
	
	public java.lang.String getCreateUser() {
		return getStr("create_user");
	}

	public M setComment(java.lang.String comment) {
		set("comment", comment);
		return (M)this;
	}
	
	public java.lang.String getComment() {
		return getStr("comment");
	}

	public M setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
		return (M)this;
	}
	
	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

	public M setUpdateUser(java.lang.String updateUser) {
		set("update_user", updateUser);
		return (M)this;
	}
	
	public java.lang.String getUpdateUser() {
		return getStr("update_user");
	}

}
