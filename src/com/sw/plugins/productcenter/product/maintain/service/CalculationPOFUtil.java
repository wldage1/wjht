package com.sw.plugins.productcenter.product.maintain.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.sw.plugins.productcenter.product.maintain.entity.POFData;
import com.sw.thirdparty.crm.webservice.FcTrustFQNav;
import com.sw.thirdparty.crm.webservice.FcTrustNav;

public class CalculationPOFUtil {

	public static POFData calculationDetail(String trustCode,List<FcTrustNav> navList,List<FcTrustFQNav> fqNavList)throws Exception{
		if(navList != null && navList.size() > 0 && fqNavList != null && fqNavList.size() > 0){
			//日期转换
			SimpleDateFormat sdf_yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
			Calendar calendar = Calendar.getInstance();
			//定义最新净值日期
			String newNavDate = "";
			//定义最新净值
			float newNav = navList.get(0).getNav();
			//定义第一个复权净值
			float oldNav = navList.get(navList.size()-1).getNav();
			//定义最新复权净值
			Float newFQNav = null;
			//定义一个月收益
			String m1NavG = null;
			//定义三个月收益
			String m3NavG = null;
			//定义六个月收益
			String m6NavG = null;
			//定义一年收益
			String y1NavG = null;
			//定义二年收益
			String y2NavG = null;
			//定义今年以来收益
			String tyNavG = null;
			//定义成立以来收益
			String createNavG = null;
			//定义一个月收益目标复权净值
			Float m1FQNav = null;
			//定义三个月收益目标复权净值
			Float m3FQNav = null;
			//定义六个月收益目标复权净值
			Float m6FQNav = null;
			//定义一年收益目标复权净值
			Float y1FQNav = null;
			//定义两年收益目标复权净值
			Float y2FQNav = null;
			//定义今年以来收益目标复权净值
			Float tyFQNav = null;
			//定义成立以来收益目标复权净值
			Float createFQNav = null;
			//定义一个月收益的目标时间
			Date m1Date = null;
			//定义三个月收益的目标时间
			Date m3Date = null;
			//定义六个月收益的目标时间
			Date m6Date = null;
			//定义一年收益的目标时间
			Date y1Date = null;
			//定义二年收益的目标时间
			Date y2Date = null;
			//定义今年以来收益的目标时间
			Date tyDate = null;
			//定义一个月收益最小时间差变量
			long m1DiffDate = 0;
			//定义三个月收益最小时间差变量
			long m3DiffDate = 0;
			//定义六个月收益最小时间差变量
			long m6DiffDate = 0;
			//定义一年收益最小时间差变量
			long y1DiffDate = 0;
			//定义二年收益最小时间差变量
			long y2DiffDate = 0;
			//定义今年以来收益最小时间差变量
			long tyDiffDate = 0;
			//定义一个月收益首次时间差标识
			boolean m1DiffDateIsFirst = true;
			//定义三个月收益首次时间差标识
			boolean m3DiffDateIsFirst = true;
			//定义六个月收益首次时间差标识
			boolean m6DiffDateIsFirst = true;
			//定义一年收益首次时间差标识
			boolean y1DiffDateIsFirst = true;
			//定义二年收益首次时间差标识
			boolean y2DiffDateIsFirst = true;
			//定义今年以来收益首次时间差标识
			boolean tyDiffDateIsFirst = true;
			int flag = 0;
			for (int i = 0; i < fqNavList.size(); i++) {
				FcTrustFQNav fqNav = fqNavList.get(i);
				BigDecimal ltdr = fqNav.getLtdr();
				String dateTemp = fqNav.getDate();
				if( ltdr != null && ( newFQNav != null || flag == 0 ) ){
					Date date = sdf_yyyyMMdd.parse(dateTemp);
					float fqn = new BigDecimal(oldNav).multiply(ltdr).floatValue();
					if(flag==0){
						newNavDate = dateTemp;
						newFQNav = fqn;
						calendar.setTime(date);
						calendar.add(Calendar.MONTH, -1);
						m1Date = calendar.getTime();
						calendar.setTime(date);
						calendar.add(Calendar.MONTH, -3);
						m3Date = calendar.getTime();
						calendar.setTime(date);
						calendar.add(Calendar.MONTH, -6);
						m6Date = calendar.getTime();
						calendar.setTime(date);
						calendar.add(Calendar.YEAR, -1);
						y1Date = calendar.getTime();
						calendar.setTime(date);
						calendar.add(Calendar.YEAR, -2);
						y2Date = calendar.getTime();
						calendar.setTime(date);
						calendar.set(Calendar.DAY_OF_YEAR,1);
						tyDate = calendar.getTime();
						flag++;
					}else{
						if(i==fqNavList.size()-1)
							createFQNav = fqn;
						long m1diffDateValue = m1Date.getTime() - date.getTime();
						long m3diffDateValue = m3Date.getTime() - date.getTime();
						long m6diffDateValue = m6Date.getTime() - date.getTime();
						long y1diffDateValue = y1Date.getTime() - date.getTime();
						long y2diffDateValue = y2Date.getTime() - date.getTime();
						long tydiffDateValue = tyDate.getTime() - date.getTime();
						if((m1diffDateValue/(24*60*60*1000))<= 15 && (m1diffDateValue/(24*60*60*1000))>=0){
							if(m1DiffDate == 0 && m1DiffDateIsFirst==true){
								m1DiffDate = m1diffDateValue;
								m1FQNav = fqn;
								m1DiffDateIsFirst = false;
							}else{
								if(m1diffDateValue < m1DiffDate){
									m1DiffDate = m1diffDateValue;
									m1FQNav = fqn;
								}
							}
						}
						if((m3diffDateValue/(24*60*60*1000))<= 15 && (m3diffDateValue/(24*60*60*1000))>=0){
							if(m3DiffDate == 0 && m3DiffDateIsFirst==true){
								m3DiffDate = m3diffDateValue;
								m3FQNav = fqn;
								m3DiffDateIsFirst = false;
							}else{
								if(m3diffDateValue < m3DiffDate){
									m3DiffDate = m3diffDateValue;
									m3FQNav = fqn;
								}
							}
						}
						if((m6diffDateValue/(24*60*60*1000))<= 15 && (m6diffDateValue/(24*60*60*1000))>=0){
							if(m6DiffDate == 0 && m6DiffDateIsFirst==true){
								m6DiffDate = m6diffDateValue;
								m6FQNav = fqn;
								m6DiffDateIsFirst = false;
							}else{
								if(m6diffDateValue < m6DiffDate){
									m6DiffDate = m6diffDateValue;
									m6FQNav = fqn;
								}
							}
						}
						if((y1diffDateValue/(24*60*60*1000))<= 15 && (y1diffDateValue/(24*60*60*1000))>=0){
							if(y1DiffDate == 0 && y1DiffDateIsFirst==true){
								y1DiffDate = y1diffDateValue;
								y1FQNav = fqn;
								y1DiffDateIsFirst = false;
							}else{
								if(y1diffDateValue < y1DiffDate){
									y1DiffDate = y1diffDateValue;
									y1FQNav = fqn;
								}
							}
						}
						if((y2diffDateValue/(24*60*60*1000))<= 15 && (y2diffDateValue/(24*60*60*1000))>=0){
							if(y2DiffDate == 0 && y2DiffDateIsFirst==true){
								y2DiffDate = y2diffDateValue;
								y2FQNav = fqn;
								y2DiffDateIsFirst = false;
							}else{
								if(y2diffDateValue < y2DiffDate){
									y2DiffDate = y2diffDateValue;
									y2FQNav = fqn;
								}
							}
						}
						if((tydiffDateValue/(24*60*60*1000))<= 15 && (tydiffDateValue/(24*60*60*1000))>=0){
							if(tyDiffDate == 0 && tyDiffDateIsFirst==true){
								tyDiffDate = tydiffDateValue;
								tyFQNav = fqn;
								tyDiffDateIsFirst = false;
							}else{
								if(tydiffDateValue < tyDiffDate){
									tyDiffDate = tydiffDateValue;
									tyFQNav = fqn;
								}
							}
						}
					}
				}
			}
			if(m1FQNav!=null){
				m1NavG = processNumberFor2F((newFQNav/m1FQNav-1)*100);
			}
			if(m3FQNav!=null){
				m3NavG = processNumberFor2F((newFQNav/m3FQNav-1)*100);
			}
			if(m6FQNav!=null){
				m6NavG = processNumberFor2F((newFQNav/m6FQNav-1)*100);
			}
			if(y1FQNav!=null){
				y1NavG = processNumberFor2F((newFQNav/y1FQNav-1)*100);
			}
			if(y2FQNav!=null){
				y2NavG = processNumberFor2F((newFQNav/y2FQNav-1)*100);
			}
			if(tyFQNav!=null){
				tyNavG = processNumberFor2F((newFQNav/tyFQNav-1)*100);
			}
			if(createFQNav!=null){
				createNavG = processNumberFor2F((newFQNav/createFQNav-1)*100);
			}
			POFData pofData = new POFData();
			pofData.setTrustCode(trustCode);
			pofData.setNavDate(newNavDate);
			pofData.setNav(newNav);
			pofData.setFqNav(newFQNav);
			pofData.setM1NavG(m1NavG);
			pofData.setM3NavG(m3NavG);
			pofData.setM6NavG(m6NavG);
			pofData.setY1NavG(y1NavG);
			pofData.setY2NavG(y2NavG);
			pofData.setTyNavG(tyNavG);
			pofData.setCreateNavG(createNavG);
			return pofData;
		}else{
			return null;
		}
	}
	
	private static String processNumberFor2F(float number){
		String temp = String.format("%.2f", number);
		if( temp.equals("-0.00")){
			temp = "0.00";
		}
		return temp;
	}
	

	
}
