package com.zhaiyz.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import junit.framework.Assert;

import org.junit.Test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;

public class BarcodeUtilTest {

    @Test
    public void testEncode() {
        String contents = "Hello, world!你好，世界！";
        int width = 300, height = 300;
        try {
            Assert.assertNotNull(BarcodeUtil.encode(BarcodeFormat.QR_CODE, contents, width, height));
            Assert.assertNotNull(BarcodeUtil.encode(BarcodeFormat.QR_CODE, contents));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        } catch (WriterException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testDecode() {
        String contents = "http://zhaiyz.com 我的网站";
        try {
            BufferedImage image = BarcodeUtil.encode(BarcodeFormat.QR_CODE, contents);
            ImageIO.write(image, "png", new File(System.getProperty("user.dir") + "/target/barcode.jpg"));
            Assert.assertEquals(contents, BarcodeUtil.decode(image));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        } catch (WriterException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        } catch (NotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

}
