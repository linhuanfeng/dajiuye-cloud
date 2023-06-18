package springbootueditor.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      //  registry.addResourceHandler("/ueditorimage/**").addResourceLocations("file:F:\\ueditorimage");
        System.out.println("路径映射成功");
    }
}
