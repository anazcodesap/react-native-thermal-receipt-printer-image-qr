// BarcodeUtils.java
package com.pinmi.react.printer.adapter;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer; // Use Code128Writer instead of QRCodeWriter
import com.google.zxing.EncodeHintType;

import java.util.HashMap;
import java.util.Map;

public class BarcodeUtils {
    public static Bitmap generateBarcodeImage(String barcodeData, int width, int height) {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 0); // No margin

        // Use Code128Writer for CODE_128 barcodes
        Code128Writer writer = new Code128Writer();
        BitMatrix bitMatrix = writer.encode(
            barcodeData,
            BarcodeFormat.CODE_128,
            width,  // Barcode width
            height, // Barcode height
            hints
        );

        // Convert BitMatrix to Bitmap
        int[] pixels = new int[bitMatrix.getWidth() * bitMatrix.getHeight()];
        for (int y = 0; y < bitMatrix.getHeight(); y++) {
            for (int x = 0; x < bitMatrix.getWidth(); x++) {
                pixels[y * bitMatrix.getWidth() + x] = bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrix.getWidth(), bitMatrix.getHeight(), Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, bitMatrix.getWidth(), 0, 0, bitMatrix.getWidth(), bitMatrix.getHeight());
        return bitmap;
    }
}
