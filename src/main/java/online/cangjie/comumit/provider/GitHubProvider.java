package online.cangjie.comumit.provider;

import com.alibaba.fastjson.JSONObject;


import okhttp3.*;
import online.cangjie.comumit.dto.AccessTokenDto;
import online.cangjie.comumit.dto.GithubUser;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubProvider {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public String getAccessToken(AccessTokenDto accessTokenDto) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, JSONObject.toJSONString(accessTokenDto));
        Request request = new Request.Builder().url("https://github.com/login/oauth/access_token").post(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string().split("&")[0].split("=")[1];
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.github.com/user?access_token=" + accessToken).build();
        try (Response response = client.newCall(request).execute()) {
            JSONObject json = (JSONObject) JSONObject.parse(response.body().string());
            GithubUser githubUser = new GithubUser(json.getLong("id"), json.getString("login"), json.getString("bio"));
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
