package online.cangjie.comumit.controller;

import com.alibaba.fastjson.JSONObject;
import online.cangjie.comumit.utils.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UploadController {

    @RequestMapping(value = "/upload")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile, HttpSession session) throws IOException {
        String suffix = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String path = FileUtils.save(multipartFile.getBytes(), suffix, session.getServletContext().getRealPath("/"));
        JSONObject json = new JSONObject();
        json.put("errno", '0');
        json.put("data", path);
        return json.toJSONString();
    }
}
