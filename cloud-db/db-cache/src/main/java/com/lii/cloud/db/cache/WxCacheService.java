//package com.lii.cloud.db.cache;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//
//import com.lii.cloud.common.entity.basis.vo.CompanyApplyVo;
//import com.lii.cloud.common.tools.https.HttpClientJSONResponse;
//import com.lii.cloud.common.tools.https.HttpSendClient;
//import com.lii.cloud.db.mysql.mapper.iMapper.basis.organization.CompanyApplyMapper;
//
///**
// * 对接微信相关缓存问题
// * @author Administrator
// */
//@Service
//public class WxCacheService {
//	@Autowired
//	private CompanyApplyMapper comApplyMapper;
//	
//	/**
//	 * 通过应用id（basis_company_apply 表）  获取公司 在微信应用中的 access_token
//	 * @param apply  
//	 * @return 返回的token值
//	 */
//	@Cacheable(value = "accessToken", key = "'findWxAccessToken:id_'+#apply")
//	public String findWxAccessToken(String apply){
//		String accessToken = null;
//		if(StringUtils.isBlank(apply)){
//			return null;
//		}
//		CompanyApplyVo comVo = comApplyMapper.findCompanyByApplyId(apply);
//		if(null == comVo){
//			return null;
//		}
////		String corpId = "wxf33555fe2dc77155";
////		String secret = "mEvHtq-wRcTvz7NRIeOgrgT7OWg7dhiTnbRFmoX1tQw";
//		String corpId = comVo.getCorpId();
//		String secret = comVo.getCorpSecret();
//		if(StringUtils.isBlank(corpId) || StringUtils.isBlank(secret)){
//			return null;
//		}
//		String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+corpId+"&corpsecret="+secret;
//		HttpClientJSONResponse clientJson = HttpSendClient.clientJsonGet(url);
//		if(null != clientJson && 200 == clientJson.getStatus()){
//			accessToken = clientJson.getResponse().getString("access_token");
//		}
//		return accessToken;
//	}
//
//	/**
//	 * 通过公司的应用id 获取公司相关信息
//	 * @param apply
//	 * @return
//	 */
//	public CompanyApplyVo findCompanyByApplyId(String apply){
//		CompanyApplyVo comVo = comApplyMapper.findCompanyByApplyId(apply);
//		return comVo;
//	}
//	
//}
