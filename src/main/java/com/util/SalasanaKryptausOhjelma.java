package com.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SalasanaKryptausOhjelma {

  public static void main(String[] args) {

		String password = "user";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
		
  }
}

