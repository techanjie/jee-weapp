package com.dbumama.market.model.base;

import com.dbumama.market.model.gen.WxmModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Wxmall, do not modify this file.
 * http://www.dbumama.com
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BasePaymentRecord<M extends BasePaymentRecord<M>> extends WxmModel<M> implements IBean {

	public M setId(java.lang.Long id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}

	public M setPayeeId(java.lang.Long payeeId) {
		set("payee_id", payeeId);
		return (M)this;
	}
	
	public java.lang.Long getPayeeId() {
		return getLong("payee_id");
	}

	public M setCertId(java.lang.Long certId) {
		set("cert_id", certId);
		return (M)this;
	}
	
	public java.lang.Long getCertId() {
		return getLong("cert_id");
	}

	public M setAppId(java.lang.Long appId) {
		set("app_id", appId);
		return (M)this;
	}
	
	public java.lang.Long getAppId() {
		return getLong("app_id");
	}

	public M setPaymentMoney(java.lang.Integer paymentMoney) {
		set("payment_money", paymentMoney);
		return (M)this;
	}
	
	public java.lang.Integer getPaymentMoney() {
		return getInt("payment_money");
	}

	public M setStatus(java.lang.Boolean status) {
		set("status", status);
		return (M)this;
	}
	
	public java.lang.Boolean getStatus() {
		return get("status");
	}

	public M setExplain(java.lang.String explain) {
		set("explain", explain);
		return (M)this;
	}
	
	public java.lang.String getExplain() {
		return getStr("explain");
	}

	public M setFailReason(java.lang.String failReason) {
		set("fail_reason", failReason);
		return (M)this;
	}
	
	public java.lang.String getFailReason() {
		return getStr("fail_reason");
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
