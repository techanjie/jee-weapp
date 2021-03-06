package com.dbumama.market.model.base;

import com.dbumama.market.model.gen.WxmModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Wxmall, do not modify this file.
 * http://www.dbumama.com
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseAuthShop<M extends BaseAuthShop<M>> extends WxmModel<M> implements IBean {

	public M setId(java.lang.Long id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}

	public M setSellerId(java.lang.Long sellerId) {
		set("seller_id", sellerId);
		return (M)this;
	}
	
	public java.lang.Long getSellerId() {
		return getLong("seller_id");
	}

	public M setShopType(java.lang.Integer shopType) {
		set("shop_type", shopType);
		return (M)this;
	}
	
	public java.lang.Integer getShopType() {
		return getInt("shop_type");
	}

	public M setSid(java.lang.String sid) {
		set("sid", sid);
		return (M)this;
	}
	
	public java.lang.String getSid() {
		return getStr("sid");
	}

	public M setShopName(java.lang.String shopName) {
		set("shop_name", shopName);
		return (M)this;
	}
	
	public java.lang.String getShopName() {
		return getStr("shop_name");
	}

	public M setShopLogo(java.lang.String shopLogo) {
		set("shop_logo", shopLogo);
		return (M)this;
	}
	
	public java.lang.String getShopLogo() {
		return getStr("shop_logo");
	}

	public M setShopUrl(java.lang.String shopUrl) {
		set("shop_url", shopUrl);
		return (M)this;
	}
	
	public java.lang.String getShopUrl() {
		return getStr("shop_url");
	}

	public M setShopPhysicalUrl(java.lang.String shopPhysicalUrl) {
		set("shop_physical_url", shopPhysicalUrl);
		return (M)this;
	}
	
	public java.lang.String getShopPhysicalUrl() {
		return getStr("shop_physical_url");
	}

	public M setCertType(java.lang.Integer certType) {
		set("cert_type", certType);
		return (M)this;
	}
	
	public java.lang.Integer getCertType() {
		return getInt("cert_type");
	}

	public M setAccessToken(java.lang.String accessToken) {
		set("access_token", accessToken);
		return (M)this;
	}
	
	public java.lang.String getAccessToken() {
		return getStr("access_token");
	}

	public M setExpiresIn(java.lang.Long expiresIn) {
		set("expires_in", expiresIn);
		return (M)this;
	}
	
	public java.lang.Long getExpiresIn() {
		return getLong("expires_in");
	}

	public M setRefreshToken(java.lang.String refreshToken) {
		set("refresh_token", refreshToken);
		return (M)this;
	}
	
	public java.lang.String getRefreshToken() {
		return getStr("refresh_token");
	}

	public M setLoginTime(java.util.Date loginTime) {
		set("login_time", loginTime);
		return (M)this;
	}
	
	public java.util.Date getLoginTime() {
		return get("login_time");
	}

	public M setLoginIp(java.lang.String loginIp) {
		set("login_ip", loginIp);
		return (M)this;
	}
	
	public java.lang.String getLoginIp() {
		return getStr("login_ip");
	}
	
	public M setClientId(java.lang.String clientId) {
		set("client_id", clientId);
		return (M)this;
	}
	
	public java.lang.String getClientId() {
		return getStr("client_id");
	}

	public M setActive(java.lang.Integer active) {
		set("active", active);
		return (M)this;
	}
	
	public java.lang.Integer getActive() {
		return getInt("active");
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
