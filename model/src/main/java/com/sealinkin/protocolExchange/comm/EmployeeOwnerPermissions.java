package com.sealinkin.protocolExchange.comm;

import java.util.HashMap;
import java.util.Map;

public class EmployeeOwnerPermissions {
	public static Map<String, String> eomap=new HashMap<String, String>();

	public static Map<String, String> getEomap() {
		return eomap;
	}

	public static void setEomap(Map<String, String> eomap) {
		EmployeeOwnerPermissions.eomap = eomap;
	}
	
}
