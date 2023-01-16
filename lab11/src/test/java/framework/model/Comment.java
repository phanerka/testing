package framework.model;
import java.util.Objects;


public class Comment {

    private String feedbackText;
    private String searchText;
    private String commentText;
    private int numberOfGoods;

    public Comment() { }

    public Comment(String feedbackText, String searchText, String commentText, int numberOfGoods) {
        this.feedbackText = feedbackText;
        this.searchText = searchText;
        this.commentText = commentText;
        this.numberOfGoods = numberOfGoods;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getNumberOfGoods() {
        return numberOfGoods;
    }

    public void setNumberOfGoods(int numberOfGoods) {
        this.numberOfGoods = numberOfGoods;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return numberOfGoods == comment.numberOfGoods && Objects.equals(feedbackText, comment.feedbackText) && Objects.equals(searchText, comment.searchText) && Objects.equals(commentText, comment.commentText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackText, searchText, commentText, numberOfGoods);
    }
}
