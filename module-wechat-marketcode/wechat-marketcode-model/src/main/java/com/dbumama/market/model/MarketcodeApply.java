package com.dbumama.market.model;

import io.jboot.db.annotation.Table;
import com.dbumama.market.model.base.BaseMarketcodeApply;

/**
 * Generated by Wxmall, do not modify this file.
 * http://www.dbumama.com
 */
@SuppressWarnings("serial")
@Table(tableName = "t_marketcode_apply", primaryKey = "id")
public class MarketcodeApply extends BaseMarketcodeApply<MarketcodeApply> {
	public static final String table = "t_marketcode_apply";
	public static final MarketcodeApply dao = new MarketcodeApply().dao();
}