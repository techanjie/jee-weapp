package com.dbumama.market.model;

import io.jboot.db.annotation.Table;
import com.dbumama.market.model.base.BaseBuyerProd;

/**
 * Generated by Wxmall, do not modify this file.
 * http://www.dbumama.com
 */
@SuppressWarnings("serial")
@Table(tableName = "t_buyer_prod", primaryKey = "id")
public class BuyerProd extends BaseBuyerProd<BuyerProd> {
	public static final String table = "t_buyer_prod";
}
