package online.cangjie.comumit.po;

public class QuestionQuery extends Question {
    private Integer startPage = 0;
    //默认分页大小
    private Integer pageSize = 5;

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = (startPage - 1) * pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "QuestionQuery{" +
                "startPage=" + startPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
