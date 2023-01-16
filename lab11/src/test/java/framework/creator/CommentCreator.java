package framework.creator;


import framework.model.Comment;
import framework.utils.TestDataReader;

public class CommentCreator {

    public static final String TESTDATA_COMMENT_TEXT = "testdata.text.comment";
    public static final String TESTDATA_FEEDBACK_TEXT = "testdata.text.feedback";
    public static final String TESTDATA_SEARCH_TEXT = "testdata.text.search";
    public static final String TESTDATA_NUMBER_OF_GOODS = "testdata.number.goods";


    public static Comment getCommentWithAllData() {
        return new Comment(
                TestDataReader.getTestData(TESTDATA_FEEDBACK_TEXT),
                TestDataReader.getTestData(TESTDATA_SEARCH_TEXT),
                TestDataReader.getTestData(TESTDATA_COMMENT_TEXT),
                Integer.parseInt(TestDataReader.getTestData(TESTDATA_NUMBER_OF_GOODS)));
    }

    public static Comment getCommentText() {
        Comment comment = new Comment();
        comment.setCommentText(TestDataReader.getTestData(TESTDATA_COMMENT_TEXT));
        return comment;
    }

    public static Comment getFeedbackText() {
        Comment comment = new Comment();
        comment.setFeedbackText(TestDataReader.getTestData(TESTDATA_FEEDBACK_TEXT));
        return comment;
    }

    public static Comment getSearchText() {
        Comment comment = new Comment();
        comment.setSearchText(TestDataReader.getTestData(TESTDATA_SEARCH_TEXT));
        return comment;
    }

    public static Comment getNumberOfGoods() {
        Comment comment = new Comment();
        comment.setNumberOfGoods(Integer.parseInt(TestDataReader.getTestData(TESTDATA_NUMBER_OF_GOODS)));
        return comment;
    }
}
