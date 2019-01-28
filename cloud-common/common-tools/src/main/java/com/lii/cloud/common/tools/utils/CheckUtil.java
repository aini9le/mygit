package com.lii.cloud.common.tools.utils;

public class CheckUtil {

	public static boolean checkInt(String value) {
		boolean bCheckResult = false;
		try {
			Integer iCheckValue = Integer.parseInt(value);

			if (iCheckValue instanceof Integer) {
				if(iCheckValue>=0){
					bCheckResult = true;
				}
				
			}
		} catch (NumberFormatException e) {
			bCheckResult = false;
		}

		return bCheckResult;
	}

	public static boolean checkDouble(String value) {
		boolean bCheckResult = false;
		try {
			Double dCheckValue = Double.parseDouble(value);
			if (dCheckValue instanceof Double) {
				if(dCheckValue>0){
					bCheckResult = true;
				}
				
			}
		} catch (NumberFormatException e) {
			bCheckResult = false;
		}
		return bCheckResult;
	}

}
