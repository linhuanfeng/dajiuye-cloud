package springbootueditor.controller.blog;



import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import springbootueditor.controller.blog.util.Base64Util;
import springbootueditor.controller.blog.util.DateUtil;
import springbootueditor.controller.blog.util.FileUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/ueditor")
public class UEditorController {
	
	
	/**
	 * 文件 上传，
	 * /ueditor/uploadimage
	 * @param file
	 */
	@ResponseBody
	@RequestMapping("/uploadimage")
	public JSONObject uploadimage(@RequestParam(value = "upfile") MultipartFile file, HttpServletRequest request)
			throws Exception {
		JSONObject object = new JSONObject();
		System.out.println(file.getOriginalFilename());
		if (!file.isEmpty()) {
//			String webPath = request.getServletContext().getRealPath("");
	String webPath =Thread.currentThread().getContextClassLoader().getResource("").getPath()+"static";

			System.out.println("webpath"+webPath);
			String filePath = "/ueditor_upload/uploadimage/" + DateUtil.formatDate(new Date(), "yyyy-MM-dd")
					+ "/";
			System.out.println("filePath hhaha:"+(webPath+filePath));
			// 把文件名子换成（时间搓.png）
			// 检测文件夹
			FileUtil.makeDirs(webPath + filePath);

			String imageName = DateUtil.formatDate(new Date(), "yyyyMMddHHmm_ssSSS") + "_" + file.getOriginalFilename();
			file.transferTo(new File(webPath + filePath + imageName));
			object.put("path", filePath + imageName);
			object.put("url", filePath + imageName);
		}

		object.put("state", "SUCCESS");

		object.put("title", file.getOriginalFilename());
		object.put("original", file.getOriginalFilename());

		return object;
	}
	
	/**
	 *   远程 图片上传。 
	 * /ueditor/catchimage
	 *  接受参数  source[]:https://img.alicdn.com/imgextra/i4/2176796437/TB2p7bbsrZnBKNjSZFKXXcGOVXa_!!2176796437.jpg
	 */
	@ResponseBody
	@RequestMapping("/catchimage")
	public JSONObject catchimage(@RequestParam(value = "source[]", required = false) String[] source
			, HttpServletRequest request)throws Exception {
		JSONObject object = new JSONObject();
//    	String webPath = request.getServletContext().getRealPath("");
		String webPath =Thread.currentThread().getContextClassLoader().getResource("").getPath()+"static";
		System.out.println("webPath="+webPath);
		String filePath = "/ueditor_upload/catchimage/" + DateUtil.formatDate(new Date(), "yyyy-MM-dd")
				+ "/";
		
		System.out.println(source.length);
		List<JSONObject> list = new ArrayList<JSONObject>();
		
		for(String imgUrl :source ) {
			//下载本地  返回服务器
			System.out.println(imgUrl);
			JSONObject temp = new JSONObject();
			String fileName = DateUtil.formatDate(new Date(), "HHmmssSSS")+".jpg";
			FileUtil.downImgToLocal(imgUrl, webPath+filePath+fileName);
			temp.put("state", "SUCCESS");
			temp.put("title", fileName);
			temp.put("source", imgUrl);
			temp.put("url", filePath+fileName);
			list.add(temp);
		}
		
		object.put("list", list);
		object.put("state", "SUCCESS");
		return object;
	}
	
	
	
	
	
	/**
	 * @author   涂鸦上传 
	 * @param   /ueditor/uploadscrawl
	 * @param upfile = base64保存在这里。
	 */
	@ResponseBody
	@RequestMapping("/uploadscrawl")
	public JSONObject uploadscrawl(String upfile, HttpServletRequest request) throws Exception {
		JSONObject object = new JSONObject();
		
		//去掉这个东西。 不过百度编辑器，没有带。
		upfile = upfile.replace("data:image/png;base64,", "");
		
//		String webPath = request.getServletContext().getRealPath("");
		String webPath =Thread.currentThread().getContextClassLoader().getResource("").getPath()+"static";
		// 定义根目录下面的文件夹
		String filePath = "/ueditor_upload/uploadscrawl/" + DateUtil.formatDate(new Date(), "yyyy-MM-dd")
		+ "/";
		
		String fileName = DateUtil.formatDate(new Date(), "yyyyMMddHHmmss") + ".jpg";
		// 调用产生文件夹的方法
		FileUtil.makeDirs(webPath + filePath);
		
		Base64Util.GenerateImage(upfile, webPath + filePath + fileName);
		
		object.put("url", filePath + fileName);
		object.put("state", "SUCCESS");
		object.put("title", fileName);
		object.put("original", fileName);
		return object;
	}
	
	
	/**
	 * 文件上传
	 * @param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/uploadfile")
	public JSONObject uploadfile(@RequestParam(value = "upfile") MultipartFile file, HttpServletRequest request) throws Exception {
		JSONObject object = new JSONObject();
		System.out.println(file.getOriginalFilename());
		if (!file.isEmpty()) {
//			String webPath = request.getServletContext().getRealPath("");
			String webPath =Thread.currentThread().getContextClassLoader().getResource("").getPath()+"static";
			String filePath = "/ueditor_upload/uploadfile/" + DateUtil.formatDate(new Date(), "yyyy-MM-dd")
					+ "/";
			// 把文件名子换成（时间搓.png）
			// 检测文件夹
			FileUtil.makeDirs(webPath + filePath);
			
			String imageName = DateUtil.formatDate(new Date(), "yyyyMMddHHmm_ssSSS") + "_" + file.getOriginalFilename();
			file.transferTo(new File(webPath + filePath + imageName));
			object.put("path", filePath + imageName);
			object.put("url", filePath + imageName);
		}

		object.put("state", "SUCCESS");

		object.put("title", file.getOriginalFilename());
		object.put("original", file.getOriginalFilename());
		return object;
	}
	
	
	
	/**
	 * 视频 上传
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/uploadvideo")
	public JSONObject uploadvideo(@RequestParam(value = "upfile") MultipartFile file, HttpServletRequest request) throws Exception {
		JSONObject object = new JSONObject();
		System.out.println(file.getOriginalFilename());
		if (!file.isEmpty()) {
//			String webPath = request.getServletContext().getRealPath("");
			String webPath =Thread.currentThread().getContextClassLoader().getResource("").getPath()+"static";
			String filePath = "/ueditor_upload/uploadvideo/" + DateUtil.formatDate(new Date(), "yyyy-MM-dd")
					+ "/";
			// 把文件名子换成（时间搓.png）
			// 检测文件夹
			FileUtil.makeDirs(webPath + filePath);
			
			String imageName = DateUtil.formatDate(new Date(), "yyyyMMddHHmm_ssSSS") + "_" + file.getOriginalFilename();
			file.transferTo(new File(webPath + filePath + imageName));
			object.put("path", filePath + imageName);
			object.put("url", filePath + imageName);
		}

		object.put("state", "SUCCESS");

		object.put("title", file.getOriginalFilename());
		object.put("original", file.getOriginalFilename());

		return object;
	}
	
	
	
	
	
	
	
}
