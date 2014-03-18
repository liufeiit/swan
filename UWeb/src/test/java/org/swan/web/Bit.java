package org.swan.web;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年3月18日 下午3:22:08
 */
public class Bit {

	public static void main(String[] args) {
		int relationShip = 0;
//		relationShip = relationShip | (1 << 0);
//		relationShip = relationShip | (1 << 1);
//		relationShip = relationShip | (1 << 2);
		relationShip = relationShip | (1 << 3);
		relationShip = relationShip | (1 << 4);
		
		System.out.println("Bit : " + Integer.toBinaryString(relationShip));
		System.out.println("Bit Count : " + Integer.bitCount(relationShip));
		System.out.println("Hex : " + Integer.toHexString(relationShip));
	}
}
