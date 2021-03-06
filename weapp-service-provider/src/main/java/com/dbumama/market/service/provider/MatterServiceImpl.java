package com.dbumama.market.service.provider;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dbumama.market.base.ApiResult;
import com.dbumama.market.service.api.*;
import com.dbumama.market.utils.DateTimeUtil;
import com.dbumama.weixin.api.CompMediaApi;
import com.dbumama.weixin.api.CompMediaApi.MediaType;
import com.dbumama.weixin.api.MediaArticles;
import com.jfinal.aop.Inject;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.aop.annotation.Bean;
import io.jboot.components.rpc.annotation.RPCBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Bean
@RPCBean
public class MatterServiceImpl implements MatterService{
	
	@Inject
	private AuthUserService authUserService;
	
	@Override
	public Page<MatterResultDto> list(MatterListParamDto matterListParamDto) throws MatterException {
		if(matterListParamDto == null) throw new MatterException("调用图文素材列表接口缺少必要参数");
		
		final String accessToken = authUserService.getAccessToken(authUserService.findById(matterListParamDto.getAuthUserId()));
		
		ApiResult matterResult=CompMediaApi.batchGetMaterial(MediaType.NEWS, accessToken, 0, matterListParamDto.getPageSize());
		if(matterResult == null || !matterResult.isSucceed() || matterResult.isAccessTokenInvalid()) throw new MatterException("调用微信批量获取素材列表接口异常," + matterResult != null ? matterResult.getErrorMsg() : "");
		int totalPage = matterResult.getInt("total_count") % matterListParamDto.getPageSize() == 0 ? matterResult.getInt("total_count")/matterListParamDto.getPageSize() : matterResult.getInt("total_count")/matterListParamDto.getPageSize() + 1;
		JSONObject json = JSONObject.parseObject(matterResult.getJson());
		JSONArray jarr = JSONArray.parseArray(json.getString("item"));
		if(jarr == null || jarr.size() <=0) throw new MatterException("没有素材返回");
		List<MatterResultDto> matterResultDto=new ArrayList<MatterResultDto>();
		for(Iterator<?> iterator = jarr.iterator(); iterator.hasNext();){
			JSONObject job = (JSONObject) iterator.next(); 
			MatterResultDto dto=new MatterResultDto();
			dto.setMedia_id(job.getString("media_id"));
			dto.setUpdateTime(DateTimeUtil.FORMAT_YYYY_MM_DD.format(new Date(job.getLong("update_time")*1000)));
			JSONObject jsonContent = JSONObject.parseObject(job.getString("content"));
			JSONArray jarrItem = JSONArray.parseArray(jsonContent.getString("news_item"));
			for(int i=0;i<jarrItem.size();i++){
				JSONObject jsonObj = jarrItem.getJSONObject(0);
				dto.setTitle(jsonObj.getString("title"));
				dto.setAuthor(jsonObj.getString("author"));
				dto.setContent(jsonObj.getString("content"));
				dto.setDigest(jsonObj.getString("digest"));
				dto.setThumb_media_id(jsonObj.getString("thumb_media_id"));
				dto.setContent_source_url(jsonObj.getString("content_source_url"));
//				dto.setImgUrl(getImgBase64(jsonObj.getString("thumb_url")));
				dto.setUrl(jsonObj.getString("url"));
			}
			matterResultDto.add(dto);
		}
		return new Page<MatterResultDto> (matterResultDto, matterListParamDto.getPageNo(), matterListParamDto.getPageSize(), totalPage, matterResult.getInt("total_count"));
	}
	
	@Override
	public String save2Weixin(String accessToken, String MatterData,String mediaId) throws MatterException {
		if( StrKit.isBlank(accessToken) || StrKit.isBlank(MatterData))
			throw new MatterException("保存图文素材缺少必要参数");
		 String media_id="";
		List<MediaArticles> mediaArticles=new ArrayList<MediaArticles>();
		JSONArray jarr = null;
		try {
			jarr = JSONArray.parseArray(MatterData);
		} catch (Exception e) {
			throw new MatterException(e.getMessage());
		}
		for(int i=0;i<jarr.size();i++){
			JSONObject jsonObj = jarr.getJSONObject(i);
			MediaArticles mediaArticle=new MediaArticles();
			mediaArticle.setTitle(jsonObj.getString("title"));
			mediaArticle.setAuthor(jsonObj.getString("author"));
			mediaArticle.setContent(jsonObj.getString("content"));
			mediaArticle.setDigest(jsonObj.getString("digest"));
			mediaArticle.setThumb_media_id(jsonObj.getString("thumb_media_id"));
			mediaArticle.setContent_source_url(jsonObj.getString("content_source_url"));
			mediaArticle.setShow_cover_pic(true);
			mediaArticles.add(mediaArticle);
		}
		ApiResult result=null;
		int index=0;
		if(StrKit.isBlank(mediaId)){
			result=CompMediaApi.addNews(accessToken, mediaArticles);	
		}else{
			for (MediaArticles mediaArticle : mediaArticles) {
				result=CompMediaApi.updateNews(accessToken, mediaId, index, mediaArticle);	
				index=index+1;
			}
		}
 
		if(result != null && result.isSucceed()){
			media_id=result.getStr("media_id");	
		}
		return media_id;
	}

	   /*private String getImgBase64(final String url){
			String result = "";
			//获取公众号二维码的base64图片数据
			OkHttpClient okHttpClient = new OkHttpClient();
			Request request = new Request.Builder().
	               url(url)
	               .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116Safari/537.36")
	               .build();
			try {
				Response okResponse = okHttpClient.newCall(request).execute();
				Base64 base64 = new Base64();
	           result = "data:image/jpg;base64," + base64.encodeAsString(okResponse.body().bytes());
			} catch (IOException e) {
				e.printStackTrace();
				result = null;
			}
			return result;
		}*/

	@Override
	public List<MatterResultDto> getMatter(String accessToken, String media_id) throws MatterException {
		if(StrKit.isBlank(accessToken) || StrKit.isBlank(media_id)) throw new MatterException("调用图文素材列表接口缺少必要参数");
		ApiResult matterResult=CompMediaApi.getMaterial(accessToken, media_id);
		List<MatterResultDto> matterResultDtos=new ArrayList<MatterResultDto>();
		JSONObject json = JSONObject.parseObject(matterResult.getJson());
		JSONArray jarr = JSONArray.parseArray(json.getString("news_item"));
		for(int i=0;i<jarr.size();i++){
			JSONObject jsonObj = jarr.getJSONObject(i);
			MatterResultDto dto=new MatterResultDto();
			dto.setTitle(jsonObj.getString("title"));
			dto.setAuthor(jsonObj.getString("author"));
			dto.setContent(jsonObj.getString("content"));
			dto.setDigest(jsonObj.getString("digest"));
			dto.setThumb_media_id(jsonObj.getString("thumb_media_id"));
			dto.setContent_source_url(jsonObj.getString("content_source_url"));
//			dto.setImgUrl(getImgBase64(jsonObj.getString("thumb_url")));
			matterResultDtos.add(dto);
		}
		return matterResultDtos;
	}

}
