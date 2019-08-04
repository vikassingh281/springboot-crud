package com.example.demo.utils;

public final class StringUtil {
	   
	   private StringUtil() {
	   }
	   
	   public static boolean isNullOrEmpty(String content) {
			 boolean status = false;
			 if (content == null || content.isEmpty())
				    status = true;
			 return status;
	   }
}
