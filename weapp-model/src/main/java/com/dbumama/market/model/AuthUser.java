package com.dbumama.market.model;

import io.jboot.db.annotation.Table;
import com.dbumama.market.model.base.BaseAuthUser;

/**
 * Generated by Wxmall, do not modify this file.
 * http://www.dbumama.com
 */
@SuppressWarnings("serial")
@Table(tableName = "t_auth_user", primaryKey = "id")
public class AuthUser extends BaseAuthUser<AuthUser> {
	public static final String table = "t_auth_user";
}