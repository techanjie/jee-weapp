package com.dbumama.market.model;

import io.jboot.db.annotation.Table;
import com.dbumama.market.model.base.BaseCashbackRcd;

/**
 * Generated by Wxmall, do not modify this file.
 * http://www.dbumama.com
 */
@SuppressWarnings("serial")
@Table(tableName = "t_cashback_rcd", primaryKey = "id")
public class CashbackRcd extends BaseCashbackRcd<CashbackRcd> {
	public static final String table = "t_cashback_rcd";
	public static final CashbackRcd dao = new CashbackRcd().dao();
}
