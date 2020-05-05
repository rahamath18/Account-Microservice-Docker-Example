package com.example.account.util;

import org.apache.commons.text.RandomStringGenerator;

public class RandomAlphaNumericGenerator {
	
	public static String getRandomNumber(int length) throws Exception {
		if (length <= 0)
			throw new Exception("Can't generate Random for given lenth");
		return new RandomStringGenerator.Builder().withinRange('0', '9').build().generate(length).trim();
	}

	public static String getRandomAlpha(int length) throws Exception {
		if (length <= 0)
			throw new Exception("Can't generate Random for given lenth");
		return new RandomStringGenerator.Builder().withinRange('a', 'z').build().generate(length);
	}

	public static String getRandomAlphaNumeric(int length) throws Exception {
		if (length <= 0)
			throw new Exception("Can't generate Random for given lenth");
		int num2 = length / 2;
		int num1 = length - num2;

		String gen1 = "";
		
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', '9').build();
		gen1 = generator.generate(num1);

		String gen2 = "";
		if (num2 >= 1) {

			RandomStringGenerator generator1 = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
			gen2 = generator1.generate(num2);
		} else {
			return gen1;
		}
		StringBuffer sb = new StringBuffer();
		if (gen2.equals(""))
			return gen1;
		else {

			char[] genArr1 = gen1.toCharArray();
			for (int i = 0; i < genArr1.length; i++) {
				sb.append(genArr1[i]);

				try {
					sb.append(gen2.charAt(i));
				} catch (Exception e) {
				}
			}
		}
		return sb.toString();
	}

}
