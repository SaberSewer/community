package online.cangjie.comumit.po;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String access_id;
    private String name;
    private String token;
    private Long gmt_create;
    private Long gmt_ified;
    private String avatar_url;
    private String bio;
}
