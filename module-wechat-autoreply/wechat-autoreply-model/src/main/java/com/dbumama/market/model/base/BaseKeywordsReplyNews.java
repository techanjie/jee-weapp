package com.dbumama.market.model.base;

import com.dbumama.market.model.gen.WxmModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Wxmall, do not modify this file.
 * http://www.dbumama.com
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseKeywordsReplyNews<M extends BaseKeywordsReplyNews<M>> extends WxmModel<M> implements IBean {

	public M setId(java.lang.Long id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}

	public M setKeywordsConfigId(java.lang.Long keywordsConfigId) {
		set("keywords_config_id", keywordsConfigId);
		return (M)this;
	}
	
	public java.lang.Long getKeywordsConfigId() {
		return getLong("keywords_config_id");
	}

	public M setMsgOrgType(java.lang.Integer msgOrgType) {
		set("msg_org_type", msgOrgType);
		return (M)this;
	}
	
	public java.lang.Integer getMsgOrgType() {
		return getInt("msg_org_type");
	}

	public M setMsgTitle(java.lang.String msgTitle) {
		set("msg_title", msgTitle);
		return (M)this;
	}
	
	public java.lang.String getMsgTitle() {
		return getStr("msg_title");
	}

	public M setMsgDesc(java.lang.String msgDesc) {
		set("msg_desc", msgDesc);
		return (M)this;
	}
	
	public java.lang.String getMsgDesc() {
		return getStr("msg_desc");
	}

	public M setMsgPic(java.lang.String msgPic) {
		set("msg_pic", msgPic);
		return (M)this;
	}
	
	public java.lang.String getMsgPic() {
		return getStr("msg_pic");
	}

	public M setMsgUrl(java.lang.String msgUrl) {
		set("msg_url", msgUrl);
		return (M)this;
	}
	
	public java.lang.String getMsgUrl() {
		return getStr("msg_url");
	}

	public M setActive(java.lang.Boolean active) {
		set("active", active);
		return (M)this;
	}
	
	public java.lang.Boolean getActive() {
		return get("active");
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

}
