package cn.sunnypuma.buildingtax.application.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseUserInfo<M extends BaseUserInfo<M>> extends Model<M> implements IBean {

	public M setUserId(java.lang.String userId) {
		set("user_id", userId);
		return (M)this;
	}
	
	public java.lang.String getUserId() {
		return getStr("user_id");
	}

	public M setUserName(java.lang.String userName) {
		set("user_name", userName);
		return (M)this;
	}
	
	public java.lang.String getUserName() {
		return getStr("user_name");
	}

	public M setHeslo(java.lang.String heslo) {
		set("heslo", heslo);
		return (M)this;
	}
	
	public java.lang.String getHeslo() {
		return getStr("heslo");
	}

	public M setIdentifyid(java.lang.String identifyid) {
		set("identifyid", identifyid);
		return (M)this;
	}
	
	public java.lang.String getIdentifyid() {
		return getStr("identifyid");
	}

	public M setBirthday(java.util.Date birthday) {
		set("birthday", birthday);
		return (M)this;
	}
	
	public java.util.Date getBirthday() {
		return get("birthday");
	}

	public M setAuthorized(java.lang.String authorized) {
		set("authorized", authorized);
		return (M)this;
	}
	
	public java.lang.String getAuthorized() {
		return getStr("authorized");
	}

	public M setEmail(java.lang.String email) {
		set("email", email);
		return (M)this;
	}
	
	public java.lang.String getEmail() {
		return getStr("email");
	}

	public M setTelephone(java.lang.String telephone) {
		set("telephone", telephone);
		return (M)this;
	}
	
	public java.lang.String getTelephone() {
		return getStr("telephone");
	}

	public M setEduBackground(java.lang.String eduBackground) {
		set("edu_background", eduBackground);
		return (M)this;
	}
	
	public java.lang.String getEduBackground() {
		return getStr("edu_background");
	}

	public M setHiredate(java.util.Date hiredate) {
		set("hiredate", hiredate);
		return (M)this;
	}
	
	public java.util.Date getHiredate() {
		return get("hiredate");
	}

	public M setPosition(java.lang.String position) {
		set("position", position);
		return (M)this;
	}
	
	public java.lang.String getPosition() {
		return getStr("position");
	}

	public M setHobby(java.lang.String hobby) {
		set("hobby", hobby);
		return (M)this;
	}
	
	public java.lang.String getHobby() {
		return getStr("hobby");
	}

	public M setUserSrc(java.lang.String userSrc) {
		set("user_src", userSrc);
		return (M)this;
	}
	
	public java.lang.String getUserSrc() {
		return getStr("user_src");
	}

	public M setUserType(java.lang.String userType) {
		set("user_type", userType);
		return (M)this;
	}
	
	public java.lang.String getUserType() {
		return getStr("user_type");
	}

	public M setUserAvatar(java.lang.String userAvatar) {
		set("user_avatar", userAvatar);
		return (M)this;
	}
	
	public java.lang.String getUserAvatar() {
		return getStr("user_avatar");
	}

	public M setDetail(java.lang.String detail) {
		set("detail", detail);
		return (M)this;
	}
	
	public java.lang.String getDetail() {
		return getStr("detail");
	}

	public M setExtendField(java.lang.String extendField) {
		set("extend_field", extendField);
		return (M)this;
	}
	
	public java.lang.String getExtendField() {
		return getStr("extend_field");
	}

	public M setAddress(java.lang.String address) {
		set("address", address);
		return (M)this;
	}
	
	public java.lang.String getAddress() {
		return getStr("address");
	}

	public M setSessionTime(java.lang.Long sessionTime) {
		set("session_time", sessionTime);
		return (M)this;
	}
	
	public java.lang.Long getSessionTime() {
		return getLong("session_time");
	}

	public M setIsOrgPwd(java.lang.Long isOrgPwd) {
		set("is_org_pwd", isOrgPwd);
		return (M)this;
	}
	
	public java.lang.Long getIsOrgPwd() {
		return getLong("is_org_pwd");
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

	public M setSalt(java.lang.String salt) {
		set("salt", salt);
		return (M)this;
	}
	
	public java.lang.String getSalt() {
		return getStr("salt");
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

	public M setSort(java.lang.Integer sort) {
		set("sort", sort);
		return (M)this;
	}
	
	public java.lang.Integer getSort() {
		return getInt("sort");
	}

	public M setStatus(java.lang.Long status) {
		set("status", status);
		return (M)this;
	}
	
	public java.lang.Long getStatus() {
		return getLong("status");
	}

	public M setComment(java.lang.String comment) {
		set("comment", comment);
		return (M)this;
	}
	
	public java.lang.String getComment() {
		return getStr("comment");
	}

	public M setEditFlag(java.lang.Long editFlag) {
		set("edit_flag", editFlag);
		return (M)this;
	}
	
	public java.lang.Long getEditFlag() {
		return getLong("edit_flag");
	}

	public M setDeleteFlag(java.lang.Long deleteFlag) {
		set("delete_flag", deleteFlag);
		return (M)this;
	}
	
	public java.lang.Long getDeleteFlag() {
		return getLong("delete_flag");
	}

	public M setLockFlag(java.lang.Long lockFlag) {
		set("lock_flag", lockFlag);
		return (M)this;
	}
	
	public java.lang.Long getLockFlag() {
		return getLong("lock_flag");
	}

}
