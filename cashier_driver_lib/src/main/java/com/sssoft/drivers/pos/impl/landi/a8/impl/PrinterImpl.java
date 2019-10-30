package com.sssoft.drivers.pos.impl.landi.a8.impl;

import static com.landicorp.android.eptapi.device.Printer.Format.HZ_DOT24x24;
import static com.landicorp.android.eptapi.utils.QrCode.ECLEVEL_Q;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;

import com.landicorp.android.eptapi.device.Printer;
import com.landicorp.android.eptapi.device.Printer.Format;
import com.landicorp.android.eptapi.exception.RequestException;
import com.landicorp.android.eptapi.utils.ImageTransformer;
import com.landicorp.android.eptapi.utils.QrCode;
import com.sssoft.drivers.pos.aidl.PrinterListener;
import com.sssoft.drivers.pos.impl.landi.a8.err.PrinterError;

/**
 * 针对无打印机终端需使用外接打印机，如蓝牙打印机等。该示例针对内置打印机。
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
public class PrinterImpl{
    private com.landicorp.android.eptapi.device.Printer.Progress progress;
    private List<com.landicorp.android.eptapi.device.Printer.Step> stepList;
    private Context context;

    public PrinterImpl(Context context) {
        this.context = context;
    }

    public int getPrinterStatus() {
        try {
            int status = Printer.getInstance().getStatus();
            return status;
        } catch (RequestException e) {
            e.printStackTrace();
        }
        return PrinterError.FAIL;
    }

    public void init() {
        stepList = new ArrayList<com.landicorp.android.eptapi.device.Printer.Step>();
    }
    public Format setFontType(Bundle format){
		String font = format.getString("font","");
		Format formatTmp = new Format();
		if(font.equals("small")){
			formatTmp.setAscScale(Format.ASC_SC1x1);
			formatTmp.setAscSize(Format.ASC_DOT16x8);
			formatTmp.setHzScale(Format.HZ_SC1x1);
			formatTmp.setHzSize(Format.HZ_DOT16x16);
		}else if(font.equals("large")){
			formatTmp.setAscScale(Format.ASC_SC2x2);
			formatTmp.setAscSize(Format.ASC_DOT24x12);
			formatTmp.setHzScale(Format.HZ_SC2x2);
			formatTmp.setHzSize(HZ_DOT24x24);
		}else{
			formatTmp.setAscScale(Format.ASC_SC1x1);
			formatTmp.setAscSize(Format.ASC_DOT24x12);
			formatTmp.setHzScale(Format.HZ_SC1x1);
			formatTmp.setHzSize(Format.HZ_DOT24x24);
		}
		return formatTmp;
	}
	public Printer.Alignment setAlgin(Bundle format){
		String align = format.getString("align","");
		Printer.Alignment alignment = Printer.Alignment.LEFT;
		if(align.equals("center")){
			alignment = Printer.Alignment.CENTER;
		}else if(align.equals("right")){
			alignment = Printer.Alignment.RIGHT;
		}else{
			alignment = Printer.Alignment.LEFT;
		}
		return alignment;
	}
    public boolean addText(final Bundle format, final String text) {
        if (stepList == null) {
            return false;
        }
        stepList.add(new com.landicorp.android.eptapi.device.Printer.Step() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                printer.setFormat(setFontType(format));
                printer.setAutoTrunc(false);
                printer.printText(setAlgin(format), text+"\n");
            }
        });
        return true;
    }

    public boolean addBitmap(final Bundle format, final Bitmap bitmap) {
        if (stepList == null) {
            return false;
        }
        stepList.add(new com.landicorp.android.eptapi.device.Printer.Step() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                InputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                final int MAX_WIDTH = getPrinterWidth();
                if (bitmap.getWidth() > MAX_WIDTH) {
                    bitmap = scaleBitmap(bitmap, format.getInt("offset",0), MAX_WIDTH);
                    if (bitmap == null) {
                        return;
                    }
                }
                ByteArrayOutputStream outputStream = ImageTransformer.convert1BitBmp(bitmap);
                inputStream = new ByteArrayInputStream(outputStream.toByteArray());
                printer.printImage(com.landicorp.android.eptapi.device.Printer.Alignment.LEFT, inputStream);
                // 若是打印大位图，需使用printer.printMonochromeBmp接口
                inputStream.close();
//                printer.printMonochromeBmp(0, outputStream.toByteArray());
                outputStream.close();

                // printer.printMonochromeBmp(offset, path)接口打印位图，需将sdcard文件路径进行转义，否则底层打印机无法找到对应文件
//                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/pay.bmp";
//                filePath = convertBmpAbsolutePath(Build.MODEL, filePath);
//                printer.printMonochromeBmp(0, filePath);
            }
        });
        return true;
    }

    private Bitmap scaleBitmap(Bitmap bm, int offset, int maxWidth) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 设置想要的大小
        int newWidth = maxWidth - offset;
        if (newWidth <= 0) {
            return null;
        }
        int newHeight = height;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newbmp = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbmp;
    }

    /**
     * 将上层转义后的sd卡绝对路径反转义为底层可访问的绝对路径
     * @param terminalType 终端型号
     * @param originalPath 原始绝对路径
     * @return 反转义后的绝对路径
     */
    private String convertBmpAbsolutePath(String terminalType, String originalPath) {
        String result = originalPath;
        if (terminalType.equals("APOS A8") || terminalType.equals("W280PV3") || terminalType.equals("W280P")
                || terminalType.equals("P990V2") || terminalType.equals("P990")) {
            if (originalPath.startsWith("/storage/emulated/0")) {
                result = originalPath.replace("/storage/emulated/0", "/storage/emulated/legacy");
            }
        } else if (terminalType.equals("AECR C10")) {
            if (originalPath.startsWith("/storage/emulated/0")) {
                result = originalPath.replace("/storage/emulated/0", "/storage/self/primary");
            }
        }
        return result;
    }

    public boolean addBarcode(final Bundle format, final String barCode) {
        if (stepList == null) {
            return false;
        }
        stepList.add(new com.landicorp.android.eptapi.device.Printer.Step() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                printer.printBarCode(setAlgin(format),barCode);
            }
        });
        return true;
    }

    public boolean addQRcode(final Bundle format, final String qrCode) {
        if (stepList == null) {
            return false;
        }
        stepList.add(new com.landicorp.android.eptapi.device.Printer.Step() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                printer.printQrCode(setAlgin(format),
                        new QrCode(qrCode, ECLEVEL_Q),
                        200);
            }
        });
        return true;
    }

    public boolean feedLine(final int line) {
        if (stepList == null) {
            return false;
        }
        stepList.add(new com.landicorp.android.eptapi.device.Printer.Step() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                printer.feedLine(line);
            }
        });
        return true;
    }

    public boolean cutPaper() {
        if (stepList == null) {
            return false;
        }
        stepList.add(new com.landicorp.android.eptapi.device.Printer.Step() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                printer.cutPaper();
            }
        });
        return true;
    }

    public void startPrint(final PrinterListener printerLis) throws Exception{
        if (stepList == null) {
            return;
        }
        progress = new com.landicorp.android.eptapi.device.Printer.Progress() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                // never call
            }

            @Override
            public void onFinish(int error){
                stepList.clear();
                if (error == com.landicorp.android.eptapi.device.Printer.ERROR_NONE) {
						try {
							printerLis.onFinish();
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                } else {
                	switch (error) {
                    case com.landicorp.android.eptapi.device.Printer.ERROR_BUSY:
                    	try {
                    		printerLis.onError("04", "打印机处于忙状态");
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    case com.landicorp.android.eptapi.device.Printer.ERROR_OVERHEAT:
                    	try {
                    		printerLis.onError("03", "打印头过热");
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    case com.landicorp.android.eptapi.device.Printer.ERROR_PAPERENDED:
                    	try {
                    		printerLis.onError("02", "缺纸，不能打印");
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    default:
                        try {
                        	printerLis.onError("05", "打印机错误，请检查");
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                }
            }

            @Override
            public void onCrash() {
                stepList.clear();
            }
        };
        for (com.landicorp.android.eptapi.device.Printer.Step step : stepList) {
            progress.addStep(step);
        }
        try {
            progress.start();
        } catch (RequestException e) {
            e.printStackTrace();
            printerLis.onError("05", "打印错误，请检查");
        }
    }

    private int getPrinterWidth() {
        int width = Printer.getInstance().getValidWidth();
        if (width <= 0) {
            return 384;
        }
        return width;
    }
}
