package com.zhaiyz.qrcode;

import junit.framework.Assert;

import org.junit.Test;

public class BarcodeUtilTest {

	@Test
	public void testEncode() {
		String imgPath = System.getProperty("user.dir") + "/target/barcode.png";
        String contents = "Hello, world!你好，世界！";
        int width = 300, height = 300;
        Assert.assertTrue(BarcodeUtil.encode(contents, width, height, imgPath));
	}
	
	@Test
	public void testDecode() {
		String imgPath = System.getProperty("user.dir") + "/target/barcodetest.png";
        String contents = "Hello, world!你好，世界！";
        int width = 300, height = 300;
        Assert.assertTrue(BarcodeUtil.encode(contents, width, height, imgPath));
        Assert.assertEquals(contents, BarcodeUtil.decode(imgPath));
	}

}
