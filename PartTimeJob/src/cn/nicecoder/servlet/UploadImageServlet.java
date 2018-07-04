package cn.nicecoder.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import net.coobird.thumbnailator.Thumbnails;
import net.sf.json.JSONObject;

/**
 * 上传文件servlet
 *-------------------------------
 * @author longtian
 * @date 2018年4月23日下午11:09:00
 * @description nicecoder.cn
 *-------------------------------
 */
@WebServlet(name="imageUpload", urlPatterns="/imageUpload")  
@MultipartConfig  
public class UploadImageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;  

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  req.setCharacterEncoding("UTF-8");  
		  Collection<Part> parts = req.getParts(); 
		  
		  JSONObject result = new JSONObject();
		  for (Part part : parts) {
			  String disposition = part.getHeader("content-disposition");  
		      System.out.println("文件描述：" + disposition);  
		      
		      //文件名，文件类型，文件大小
		      String fileName = disposition.substring(disposition.lastIndexOf("=")+2, disposition.length()-1);  
		      String fileType = part.getContentType();  
		      long fileSize = part.getSize();  
		      System.out.println("fileName: " + fileName);  
		      System.out.println("fileType: " + fileType);  
		      System.out.println("fileSize: " + fileSize);  
		      
		      //1.服务器保存文件路径
		      String uploadPath = this.getServletConfig().getServletContext().getRealPath("/");
		      //2.文件夹按日期分类
		      String folder = new SimpleDateFormat("yyyyMMdd").format(new Date());
		      //3.拼接文件名
		      if(!new File(uploadPath + File.separator + folder).exists()) {
		    	  	new File(uploadPath + File.separator + folder).mkdirs();
		      }
		      //重命名并写入文件
		      fileName = new SimpleDateFormat("yyyyMMdd_HHmmSS").format(new Date()) + fileName.subSequence(fileName.indexOf("."), fileName.length());
		      part.write(uploadPath  + folder + File.separator + fileName);
		      
		      //返回存储地址
		      String src = uploadPath + folder + File.separator + fileName;
		      BufferedImage bufferedImage = ImageIO.read(new File(src));   
		      int width = bufferedImage.getWidth(); 
		      int height = bufferedImage.getHeight(); 
		      
		      //图片过大的压缩一下
		      if(width > 762){
		    	  double scale = new BigDecimal((float)762/width).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
		    	  Thumbnails.of(src).size(762,(int) (height * scale)).keepAspectRatio(false).toFile(src);  
		      }
		      
		      result.put("code", 0);
		      result.put("msg", "ok");
		      JSONObject subObject = new JSONObject();
		      subObject.put("src", folder + File.separator + fileName );
		      subObject.put("title", fileName);
		      result.put("data", subObject);

		      /*适应layui，所以返回如下格式{
		    	  "code": 0 //0表示成功，其它失败
		    	  ,"msg": "" //提示信息 //一般上传失败后返回
		    	  ,"data": {
		    	    "src": "图片路径"
		    	    ,"title": "图片名称" //可选
		    	  }
		      }*/
		  }
		  PrintWriter wp = resp.getWriter();
	      wp.write(result.toString());
	      wp.close();
	}

}
