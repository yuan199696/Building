package cn.sunnypuma.buildingtax.application.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseEnterprisePosition<M extends BaseEnterprisePosition<M>> extends Model<M> implements IBean {

	public M setEnterpriseId(java.lang.String enterpriseId) {
		set("enterprise_id", enterpriseId);
		return (M)this;
	}
	
	public java.lang.String getEnterpriseId() {
		return getStr("enterprise_id");
	}

	public M setPositionId(java.lang.String positionId) {
		set("position_id", positionId);
		return (M)this;
	}
	
	public java.lang.String getPositionId() {
		return getStr("position_id");
	}

	public M setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
		return (M)this;
	}
	
	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public M setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
		return (M)this;
	}
	
	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

	public M setStatus(java.lang.Long status) {
		set("status", status);
		return (M)this;
	}
	
	public java.lang.Long getStatus() {
		return getLong("status");
	}

	public M setSort(java.lang.Integer sort) {
		set("sort", sort);
		return (M)this;
	}
	
	public java.lang.Integer getSort() {
		return getInt("sort");
	}

	public M setComment(java.lang.String comment) {
		set("comment", comment);
		return (M)this;
	}
	
	public java.lang.String getComment() {
		return getStr("comment");
	}

	public M setCreateUser(java.lang.String createUser) {
		set("create_user", createUser);
		return (M)this;
	}
	
	public java.lang.String getCreateUser() {
		return getStr("create_user");
	}

	public M setUpdateUser(java.lang.String updateUser) {
		set("update_user", updateUser);
		return (M)this;
	}
	
	public java.lang.String getUpdateUser() {
		return getStr("update_user");
	}

}
