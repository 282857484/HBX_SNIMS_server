package dealfileupload;

/**
 *  缩略图实现，将图片(jpg、bmp、png、gif等等)真实的变成想要的大小
 */

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class PhotoCompressTool {
	static Logger logger = Logger.getLogger(PhotoCompressTool.class.getName());


	/*******************************************************************************
	 * 缩略图类（通用） 本java类能将jpg、bmp、png、gif图片文件，进行等比或非等比的大小转换。 具体使用方法
	 * compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))
	 */
		 private File file = null; // 文件对象 
		 private String inputDir; // 输入图路径
		 private String outputDir; // 输出图路径
		 private String inputFileName; // 输入图文件名
		 private String outputFileName; // 输出图文件名
		 private int outputWidth = 100; // 默认输出图片宽
		 private int outputHeight = 100; // 默认输出图片高
		 private boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)
		 public PhotoCompressTool() { // 初始化变量
			 inputDir = ""; 
			 outputDir = ""; 
			 inputFileName = ""; 
			 outputFileName = ""; 
			 outputWidth = 100; 
			 outputHeight = 100; 
		 } 
		 public void setInputDir(String inputDir) { 
			 this.inputDir = inputDir; 
		 } 
		 public void setOutputDir(String outputDir) { 
			 this.outputDir = outputDir; 
		 } 
		 public void setInputFileName(String inputFileName) { 
			 this.inputFileName = inputFileName;
		 } 
		 public void setOutputFileName(String outputFileName) { 
			 this.outputFileName = outputFileName; 
		 } 
		 public void setOutputWidth(int outputWidth) {
			 this.outputWidth = outputWidth; 
		 } 
		 public void setOutputHeight(int outputHeight) { 
			 this.outputHeight = outputHeight; 
		 } 
		 public void setWidthAndHeight(int width, int height) { 
			 this.outputWidth = width;
			 this.outputHeight = height; 
		 } 
		 
		 /* 
		  * 获得图片大小 
		  * 传入参数 String path ：图片路径 
		  */ 
		 public long getPicSize(String path) { 
			 file = new File(path); 
			 return file.length(); 
		 }
		 
		 // 图片处理 
		 public String compressPic() { 
			 try { 
				 //获得源文件 
				 file = new File(inputDir + inputFileName); 
				 if (!file.exists()) { 
					 return ""; 
				 } 
				 Image img = ImageIO.read(file); 
				 // 判断图片格式是否正确 
				 if (img.getWidth(null) == -1) {
					 System.out.println(" can't read,retry!" + "<BR>"); 
					 return "no"; 
				 } else { 
					 int newWidth; int newHeight; 
					 // 判断是否是等比缩放 
					 if (this.proportion == true) { 
						 // 为等比缩放计算输出的图片宽度及高度 
						 double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1; 
						 double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1; 
						 // 根据缩放比率大的进行缩放控制 
						 double rate = rate1 > rate2 ? rate1 : rate2; 
						 newWidth = (int) (((double) img.getWidth(null)) / rate); 
						 newHeight = (int) (((double) img.getHeight(null)) / rate); 
					 } else { 
						 newWidth = outputWidth; // 输出的图片宽度 
						 newHeight = outputHeight; // 输出的图片高度 
					 } 
				 	BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB); 
				 	
				 	/*
					 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的
					 * 优先级比速度高 生成的图片质量比较好 但速度慢
					 */ 
				 	tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
				 	FileOutputStream out = new FileOutputStream(outputDir + outputFileName);
				 	// JPEGImageEncoder可适用于其他图片类型的转换 
				 	JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); 
				 	encoder.encode(tag); 
				 	out.close(); 
				 } 
			 } catch (IOException ex) { 
				 ex.printStackTrace(); 
				 logger.error(log.ExceptionLogTool.getTrace(ex));
			 } 
			 return "ok"; 
		} 
	 	public String compressPic (String inputDir, String outputDir, String inputFileName, String outputFileName) { 
	 		// 输入图路径 
	 		this.inputDir = inputDir; 
	 		// 输出图路径 
	 		this.outputDir = outputDir; 
	 		// 输入图文件名 
	 		this.inputFileName = inputFileName; 
	 		// 输出图文件名
	 		this.outputFileName = outputFileName; 
	 		return compressPic(); 
	 	} 
	 	public String compressPic(String inputDir, String outputDir, String inputFileName, String outputFileName, int width, int height, boolean gp) { 
	 		// 输入图路径 
	 		this.inputDir = inputDir; 
	 		// 输出图路径 
	 		this.outputDir = outputDir; 
	 		// 输入图文件名 
	 		this.inputFileName = inputFileName; 
	 		// 输出图文件名 
	 		this.outputFileName = outputFileName; 
	 		// 设置图片长宽
	 		setWidthAndHeight(width, height); 
	 		// 是否是等比缩放 标记 
	 		this.proportion = gp; 
	 		return compressPic(); 
	 	} 
	 	
	 	// main测试 
	 	// compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))
	 	/**
	 	 * 路径需要最后以反斜杠结尾
	 	 * @param inputFile 大图片路径
	 	 * @param outputFile 生成小图片路径
	 	 * @param inputFileName 大图片文件名
	 	 * @param outputFileName 生成小图片文名
	 	 * @param width 生成小图片宽度
	 	 * @param height 生成小图片高度
	 	 * @param gp 是否等比缩放(默认为true)
	 	 */
	 	public static void CompressPhoto(String inputFilePath, String outputFilePath, String inputFileName, String outputFileName, int width, int height, boolean gp) { 
	 		PhotoCompressTool mypic = new PhotoCompressTool(); 
	 		System.out.println("输入的图片大小：" + mypic.getPicSize(inputFilePath + inputFileName)/1024 + "KB"); 
	 		int start = (int) System.currentTimeMillis();	// 开始时间 
	 		mypic.compressPic(inputFilePath, outputFilePath, inputFileName, outputFileName, width, height, gp); 
	 		int end = (int) System.currentTimeMillis(); // 结束时间 
	 		int re = end-start; // 但图片生成处理时间 
	 		System.out.println("本张图片压缩处理使用了: " + re + "毫秒"); 
	 		System.out.println("输出的图片大小：" + mypic.getPicSize(outputFilePath + outputFileName)/1024 + "KB"); 
	 	} 

}
