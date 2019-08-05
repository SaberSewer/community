package online.cangjie.comumit.po;

public class User {
    private Integer id;
    private String access_id;
    private String name;
    private String token;
    private Long gmt_create;
    private Long gmt_ified;

    public User() {
    }

    public User(Integer id, String access_id, String name, String token, Long gmt_create, Long gmt_ified) {
        this.id = id;
        this.access_id = access_id;
        this.name = name;
        this.token = token;
        this.gmt_create = gmt_create;
        this.gmt_ified = gmt_ified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccess_id() {
        return access_id;
    }

    public void setAccess_id(String access_id) {
        this.access_id = access_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Long gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Long getGmt_ified() {
        return gmt_ified;
    }

    public void setGmt_ified(Long gmt_ified) {
        this.gmt_ified = gmt_ified;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", access_id='" + access_id + '\'' +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", gmt_create=" + gmt_create +
                ", gmt_ified=" + gmt_ified +
                '}';
    }
}
