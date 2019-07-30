package com.example.demo.utils;

public final class Utility {
	   
	   private Utility() {
	   }
	   
	   public static boolean isNullOrEmpty(String str) {
			 boolean status = false;
			 if (str == null || str.isEmpty())
				    status = true;
			 return status;
	   }
}
