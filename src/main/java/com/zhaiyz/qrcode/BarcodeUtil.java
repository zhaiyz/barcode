package com.zhaiyz.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class BarcodeUtil {

    /**
     * 编码
     * @param contents
     * @param width
     * @param height
     * @param imgPath
     */
    public static boolean encode(String contents, int width, int height, String imgPath) {
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        // 指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "GBK");
        
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.QR_CODE, width, height, hints);

            MatrixToImageWriter.writeToFile(bitMatrix, "png", new File(imgPath));
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
        return true;
    }
    
    /** 
     * @param imgPath 
     * @return String 
     */  
    public static String decode(String imgPath) {  
        BufferedImage image = null;  
        Result result = null;  
        try {  
            image = ImageIO.read(new File(imgPath));  
            if (image == null) {  
                System.out.println("the decode image may be not exit.");  
            }  
            LuminanceSource source = new BufferedImageLuminanceSource(image);  
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));  
  
            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();  
            hints.put(DecodeHintType.CHARACTER_SET, "GBK");  
  
            result = new MultiFormatReader().decode(bitmap, hints);  
            return result.getText();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }
}
