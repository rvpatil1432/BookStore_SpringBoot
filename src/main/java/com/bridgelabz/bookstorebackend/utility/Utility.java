package com.bridgelabz.bookstorebackend.utility;

public class Utility {
		
public static Response getResponse(String msg, Object data) {	
		
		return new Response(msg, data);
	}
}
