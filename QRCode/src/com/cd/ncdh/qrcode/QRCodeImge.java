package com.cd.ncdh.qrcode;

import com.swetake.util.Qrcode;

/**
 * @author Administrator
 *
 */
public class QRCodeImge {
	
	/**
	 * @param content
	 * @param imgPth
	 */
	public void getImage(String content, String imgPth)
	{
		Qrcode qrCode = new Qrcode();
		
		qrCode.setQrcodeEncodeMode('B');
		
		qrCode.setQrcodeErrorCorrect('M');
		
		qrCode.setQrcodeVersion(15);
		
		byte[] output = content.getBytes();
		
		boolean [][] booleans = qrCode.calQrcode(output);
		
		for(int i=0; i<booleans.length; i++)
		{
			for(int j=0; j<booleans[i].length; j++)
			{
				qrCode.
			}
		}
	}
	public static void main(String[] args) {

	}

}
