package springbootueditor.controller.blog.util;


import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


public class Base64Util {

	// 图片转化成base64字符串
	public static String encodeToString(String imagePath) throws IOException {
	    String type = StringUtils.substring(imagePath, imagePath.lastIndexOf(".") + 1);
	    BufferedImage image = ImageIO.read(new File(imagePath));
	    String imageString = null;
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    try {
	        ImageIO.write(image, type, bos);
	        byte[] imageBytes = bos.toByteArray();
	        BASE64Encoder encoder = new BASE64Encoder();
	        imageString = encoder.encode(imageBytes);
	        bos.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return imageString;
	}
	
	
	// 图片转化成base64字符串  这个方法有误 。不能使用
	public static String GetImageStr(String imageUrl) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imageUrl);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	//base64字符串转化成图片
	public static boolean GenerateImage(String base64,String imgSrc) { // 对字节数组字符串进行Base64解码并生成图片
		if (base64 == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(base64);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			String imgFilePath = imgSrc;// 新生成的图片d://222.jpg
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
