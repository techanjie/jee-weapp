package com.dbumama.market.model;

import io.jboot.db.annotation.Table;
import com.dbumama.market.model.base.BaseSellerUser;

/**
 * Generated by Wxmall, do not modify this file.
 * http://www.dbumama.com
 */
@SuppressWarnings("serial")
@Table(tableName = "t_seller_user", primaryKey = "id")
public class SellerUser extends BaseSellerUser<SellerUser> {
	public static final String table = "t_seller_user";
}