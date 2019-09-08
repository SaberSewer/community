package online.cangjie.comumit.controller;

import com.alibaba.fastjson.JSONObject;
import online.cangjie.comumit.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class UploadController {
    @Value("${img.server.host}")
    private String host;

    @RequestMapping(value = "/upload")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String path[] = new String[1];
        path[0] = host + FileUtils.saveWithCloud(multipartFile.getBytes(), multipartFile.getOriginalFilename(), multipartFile.getSize());
        JSONObject json = new JSONObject();
        json.put("errno", 0);
        json.put("date",path);
        return json.toJSONString();
    }
}
