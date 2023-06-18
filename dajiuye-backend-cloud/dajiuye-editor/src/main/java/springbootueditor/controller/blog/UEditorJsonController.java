package springbootueditor.controller.blog;

import com.baidu.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class UEditorJsonController {
    @RequestMapping("/aa")
    private String showPage(){
        return "index";
    }

    @RequestMapping(value="/config")
    @ResponseBody
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
//        String rootPath = request.getSession().getServletContext().getRealPath("/");
        //配置config.json的位置
        String rootPath =Thread.currentThread().getContextClassLoader().getResource("").getPath(); //+"templates/";

        System.out.println("rootpath:"+rootPath);
        try {
            response.setCharacterEncoding("UTF-8");
            String exec = new ActionEnter(request, rootPath).exec();
            System.out.println("exec="+exec);
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
