package com.Orders.SpringRestfulAPI.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordUtil {
	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String password = "welcome";
		
		String cipherText1 = passwordEncoder.encode(password);
		String cipherText2 = passwordEncoder.encode(password);
		String cipherText3 = passwordEncoder.encode(password);
		String cipherText4 = passwordEncoder.encode(password);
		String cipherText5 = passwordEncoder.encode(password);
		
		System.out.println(cipherText1);
		System.out.println(cipherText2);
		System.out.println(cipherText3);
		System.out.println(cipherText4);
		System.out.println(cipherText5);
		
		System.out.println(passwordEncoder.matches(password, cipherText1));
		System.out.println(passwordEncoder.matches(password, cipherText2));
		System.out.println(passwordEncoder.matches(password, cipherText3));
		System.out.println(passwordEncoder.matches(password, cipherText4));
		System.out.println(passwordEncoder.matches(password, cipherText5));
	}
}
