package com.dbumama.market.model.base;

import com.dbumama.market.model.gen.WxmModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Wxmall, do not modify this file.
 * http://www.dbumama.com
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseBuyerUser<M extends BaseBuyerUser<M>> extends WxmModel<M> implements IBean {

	public M setId(java.lang.Long id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}

	public M setAppId(java.lang.Long appId) {
		set("app_id", appId);
		return (M)this;
	}
	
	public java.lang.Long getAppId() {
		return getLong("app_id");
	}

	public M setMemberRankId(java.lang.Long memberRankId) {
		set("member_rank_id", memberRankId);
		return (M)this;
	}
	
	public java.lang.Long getMemberRankId() {
		return getLong("member_rank_id");
	}

	public M setNickname(java.lang.String nickname) {
		set("nickname", nickname);
		return (M)this;
	}
	
	public java.lang.String getNickname() {
		return getStr("nickname");
	}

	public M setUsername(java.lang.String username) {
		set("username", username);
		return (M)this;
	}
	
	public java.lang.String getUsername() {
		return getStr("username");
	}

	public M setPhone(java.lang.String phone) {
		set("phone", phone);
		return (M)this;
	}
	
	public java.lang.String getPhone() {
		return getStr("phone");
	}

	public M setPassword(java.lang.String password) {
		set("password", password);
		return (M)this;
	}
	
	public java.lang.String getPassword() {
		return getStr("password");
	}

	public M setHeadimgurl(java.lang.String headimgurl) {
		set("headimgurl", headimgurl);
		return (M)this;
	}
	
	public java.lang.String getHeadimgurl() {
		return getStr("headimgurl");
	}

	public M setOpenId(java.lang.String openId) {
		set("open_id", openId);
		return (M)this;
	}
	
	public java.lang.String getOpenId() {
		return getStr("open_id");
	}

	public M setEmail(java.lang.String email) {
		set("email", email);
		return (M)this;
	}
	
	public java.lang.String getEmail() {
		return getStr("email");
	}

	public M setSex(java.lang.Integer sex) {
		set("sex", sex);
		return (M)this;
	}
	
	public java.lang.Integer getSex() {
		return getInt("sex");
	}

	public M setLanguage(java.lang.String language) {
		set("language", language);
		return (M)this;
	}
	
	public java.lang.String getLanguage() {
		return getStr("language");
	}

	public M setCity(java.lang.String city) {
		set("city", city);
		return (M)this;
	}
	
	public java.lang.String getCity() {
		return getStr("city");
	}

	public M setProvince(java.lang.String province) {
		set("province", province);
		return (M)this;
	}
	
	public java.lang.String getProvince() {
		return getStr("province");
	}

	public M setCountry(java.lang.String country) {
		set("country", country);
		return (M)this;
	}
	
	public java.lang.String getCountry() {
		return getStr("country");
	}

	public M setSubscribe(java.lang.Integer subscribe) {
		set("subscribe", subscribe);
		return (M)this;
	}
	
	public java.lang.Integer getSubscribe() {
		return getInt("subscribe");
	}

	public M setSubscribeTime(java.util.Date subscribeTime) {
		set("subscribe_time", subscribeTime);
		return (M)this;
	}
	
	public java.util.Date getSubscribeTime() {
		return get("subscribe_time");
	}

	public M setUnSubscribeTime(java.util.Date unSubscribeTime) {
		set("un_subscribe_time", unSubscribeTime);
		return (M)this;
	}
	
	public java.util.Date getUnSubscribeTime() {
		return get("un_subscribe_time");
	}

	public M setSubscribeScene(java.lang.String subscribeScene) {
		set("subscribe_scene", subscribeScene);
		return (M)this;
	}
	
	public java.lang.String getSubscribeScene() {
		return getStr("subscribe_scene");
	}

	public M setTagidList(java.lang.String tagidList) {
		set("tagid_list", tagidList);
		return (M)this;
	}
	
	public java.lang.String getTagidList() {
		return getStr("tagid_list");
	}

	public M setGroupid(java.lang.Integer groupid) {
		set("groupid", groupid);
		return (M)this;
	}
	
	public java.lang.Integer getGroupid() {
		return getInt("groupid");
	}

	public M setRemark(java.lang.String remark) {
		set("remark", remark);
		return (M)this;
	}
	
	public java.lang.String getRemark() {
		return getStr("remark");
	}

	public M setAccessIp(java.lang.String accessIp) {
		set("access_ip", accessIp);
		return (M)this;
	}
	
	public java.lang.String getAccessIp() {
		return getStr("access_ip");
	}

	public M setAccessToken(java.lang.String accessToken) {
		set("access_token", accessToken);
		return (M)this;
	}
	
	public java.lang.String getAccessToken() {
		return getStr("access_token");
	}

	public M setRefreshToken(java.lang.String refreshToken) {
		set("refresh_token", refreshToken);
		return (M)this;
	}
	
	public java.lang.String getRefreshToken() {
		return getStr("refresh_token");
	}

	public M setTokenExpiresIn(java.lang.String tokenExpiresIn) {
		set("token_expires_in", tokenExpiresIn);
		return (M)this;
	}
	
	public java.lang.String getTokenExpiresIn() {
		return getStr("token_expires_in");
	}

	public M setLastLoginTime(java.util.Date lastLoginTime) {
		set("last_login_time", lastLoginTime);
		return (M)this;
	}
	
	public java.util.Date getLastLoginTime() {
		return get("last_login_time");
	}

	public M setUnionid(java.lang.String unionid) {
		set("unionid", unionid);
		return (M)this;
	}
	
	public java.lang.String getUnionid() {
		return getStr("unionid");
	}

	public M setScore(java.lang.Integer score) {
		set("score", score);
		return (M)this;
	}
	
	public java.lang.Integer getScore() {
		return getInt("score");
	}

	public M setLat(java.lang.String lat) {
		set("lat", lat);
		return (M)this;
	}
	
	public java.lang.String getLat() {
		return getStr("lat");
	}

	public M setLng(java.lang.String lng) {
		set("lng", lng);
		return (M)this;
	}
	
	public java.lang.String getLng() {
		return getStr("lng");
	}

	public M setPrecision(java.lang.String precision) {
		set("precision", precision);
		return (M)this;
	}
	
	public java.lang.String getPrecision() {
		return getStr("precision");
	}
	
	public M setSessionKey(java.lang.String sessionKey) {
		set("session_key", sessionKey);
		return (M)this;
	}
	
	public java.lang.String getSessionKey() {
		return getStr("session_key");
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
