package com.dbumama.market.model;

import io.jboot.db.annotation.Table;
import com.dbumama.market.model.base.BaseKeywords;

/**
 * Generated by Wxmall, do not modify this file.
 * http://www.dbumama.com
 */
@SuppressWarnings("serial")
@Table(tableName = "t_keywords", primaryKey = "id")
public class Keywords extends BaseKeywords<Keywords> {
	public static final String table = "t_keywords";
	public static final Keywords dao = new Keywords().dao();
}
