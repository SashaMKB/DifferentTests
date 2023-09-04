import com.fasterxml.jackson.annotation.JsonProperty;

public class BookInsert {
    private String title;
    private int pages;
    private int year;
    @JsonProperty("author_id")
    private int authorId;

    public BookInsert(String title, int pages, int year, int authorId) {
        this.title = title;
        this.pages = pages;
        this.year = year;
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}

