package com.dbumama.market.model.base;

import com.dbumama.market.model.gen.WxmModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Wxmall, do not modify this file.
 * http://www.dbumama.com
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseMenuNotifyRcd<M extends BaseMenuNotifyRcd<M>> extends WxmModel<M> implements IBean {

	public M setId(java.lang.Long id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}

	public M setNotifyerId(java.lang.Long notifyerId) {
		set("notifyer_id", notifyerId);
		return (M)this;
	}
	
	public java.lang.Long getNotifyerId() {
		return getLong("notifyer_id");
	}

	public M setOpenId(java.lang.String openId) {
		set("open_id", openId);
		return (M)this;
	}
	
	public java.lang.String getOpenId() {
		return getStr("open_id");
	}

	public M setNotifyCount(java.lang.Integer notifyCount) {
		set("notify_count", notifyCount);
		return (M)this;
	}
	
	public java.lang.Integer getNotifyCount() {
		return getInt("notify_count");
	}

	public M setCreated(java.util.Date created) {
		set("created", created);
		return (M)this;
	}
	
	public java.util.Date getCreated() {
		return get("created");
	}

	public M setUpdated(java.util.Date updated) {
		set("updated", updated);
		return (M)this;
	}
	
	public java.util.Date getUpdated() {
		return get("updated");
	}

	public M setActive(java.lang.Boolean active) {
		set("active", active);
		return (M)this;
	}
	
	public java.lang.Boolean getActive() {
		return get("active");
	}

}