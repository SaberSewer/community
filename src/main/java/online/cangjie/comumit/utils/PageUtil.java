package online.cangjie.comumit.utils;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {
    //储存跳转的页码信息基于BootStrap的分页
    private String page;
    private List pageData;
    private String url;
    private Integer count;
    private Integer pageSize;
    private Integer pageNo;

    public PageUtil(String url, Integer count, Integer pageSize, Integer pageNo) {
        this.url = url;
        this.count = count;
        this.pageSize = pageSize;
        this.pageNo = pageNo;

        setPage();
    }

    private void setPage() {
        List<String> page = new ArrayList<>();
        page.add("<ul class=\"pagination\">");
        page.add("<li><a href=\"" + url + "&pageNo=1\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>");
        //i为页码
        for (int i = pageNo < 3 ? pageNo : pageNo - 2; i <= ((count % pageSize) == 0 ? count / pageSize : count / pageSize + 1 ); i++) {
            if (pageNo == i) {
                page.add("<li class=\"active\"><a href=\"" + url + "&pageNo=" + i + "\">" + i + "</a></li>");
                continue;
            }
            page.add(" <li ><a href=\"" + url + "&pageNo=" + i + "\">" + i + "</a></li>");
        }
        page.add("<li><a href=\"" + url + "&pageNo=" + ((count % pageSize) == 0 ? count / pageSize : count / pageSize + 1) + "\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li></ul>");
        StringBuffer stringBuffer = new StringBuffer();
        page.forEach((str) -> stringBuffer.append(str));
        this.page = new String(stringBuffer);
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List getPageData() {
        return pageData;
    }

    public void setPageData(List pageData) {
        this.pageData = pageData;
    }
}
