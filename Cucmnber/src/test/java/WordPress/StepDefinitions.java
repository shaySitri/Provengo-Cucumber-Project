package WordPress;

import io.cucumber.java.en.*;

import static org.junit.Assert.*;

public class StepDefinitions {
    private static String ADMIN_USERNAME = "shay";
    private static String ADMIN_PASSWORD = "shay123";
    private static WordPressActuator wordPressActuator;
    private static String webDriver = "webdriver.chrome.driver";
    private static String path = "C:\\Users\\shays\\Downloads\\sqe-hw3-main\\sqe-hw3-main\\Selenium\\chromedriver.exe";

    private static String title = "Eden and Itai Hey";

    private String disallowedWord = "Cucumber";
    public static void wordPressInit() {
        wordPressActuator = new WordPressActuator();
        wordPressActuator.initSession(webDriver, path);
    }

    public static void createNewPost()
    {
        // In this step, admin logged in to system and create new commentable post.
        wordPressInit();
        // admin login to system
        loginSequence();
        // admin create new post
        wordPressActuator.createPost();
        // admin fill the post
        wordPressActuator.fillTitle(title);
        wordPressActuator.fillContent("I am happy to announce we finally finish CUCUMBER part!\n Shay :)");
        // admin publish the post
        wordPressActuator.publishPost();
        // check if post has been created
        wordPressActuator.watchPost();
        wordPressActuator.isPostCreated(title);
        assertNotNull("New post didnt created!", title);
    }


    @Given("Admin logged in")
    public void admin_logged_in() {
        wordPressInit();
        // admin login to system
        loginSequence();
    }
    @When("Admin on admin dashboard")
    public void admin_on_admin_dashboard() {
        // admin open dashboard tab
        wordPressActuator.openDashboardTab();
        assertNotNull("Not on dashboard.", wordPressActuator.adminIsOnDashboard());
    }
    @When("Admin navigate to discussion settings")
    public void admin_navigate_to_discussion_settings() {
        // admin click on settings -> small menu
        wordPressActuator.openSettingsTab();
        // small menu -> discussions
        wordPressActuator.goToDiscussionSettings();
        String discussionSettings = wordPressActuator.adminIsInDiscussionSettings().getText();
        assertEquals("Not on discussion settings.", "Discussion Settings", discussionSettings);
    }
    @When("Admin insert new disallowed comment key word <word>")
    public void admin_insert_new_disallowed_comment_key_word_word() {
        // admin enter word to disallowed Word(word);
        wordPressActuator.adminDisallowedWord(disallowedWord);
        // admin submit changes
        wordPressActuator.adminSubmitChanges();
    }
    @Then("discussion settings updated")
    public void discussion_settings_updated() {
        // check if new disallowed word saved
        assertEquals("Settings didnt save.", "Settings saved.", wordPressActuator.isChangesSaved());
    }

    @Given("Post page exist")
    public void post_page_exist() {
        wordPressInit();
        createNewPost();
    }
    @When("Guest enters Comment <Comment>")
    public void guest_enters_comment_comment() {
        // guest type his comment
        wordPressActuator.guestFillComment("Guest first comment");
    }
    @When("Guest post his comment")
    public void guest_post_his_comment() {
        // guest type his comment
        wordPressActuator.guestPostComment();
    }
    @Then("New comment added to post page")
    public void new_comment_added_to_post_page() {
        // check if new comment has been added successfully
        String commentGuest = wordPressActuator.searchCommentOnPost("Guest first comment").getText();
        System.out.println(commentGuest);
        assertNotNull("Comment didnt on post...", commentGuest );
    }

    @Given("Admin disallowed comment key word <word>")
    public void admin_disallowed_comment_key_word_word() {
        wordPressInit();
        // Admin log in first
        loginSequence();
        // admin add words to disallowed
        disallowedKeyWordsSequence();
        // check successful adding
        assertEquals(wordPressActuator.isChangesSaved(), "Settings saved.");
    }
    @When("Guest click on post with title")
    public void guest_click_on_post_with_title() {
        wordPressInit();
        // Gust continue as guest
        wordPressActuator.goToLogin();
        wordPressActuator.continueAsGuest();
        // Guest click on post
        wordPressActuator.guestClickOnPost(title);
        // check if guest transfer to post
        String postTitle = wordPressActuator.guestIsOnPost(title).getText();
        assertEquals( title, postTitle);
    }
    @When("Guest add comment contains <word>")
    public void guest_add_comment_contains_word() {
        // guest type a comment contains disallowed word
        wordPressActuator.guestFillComment("Hey this is comment contain " + disallowedWord);
    }
    @When("Guest post his disallowed comment")
    public void guest_post_his_disallowed_comment() {
        // guest post his comment
        wordPressActuator.guestPostComment();
        System.out.println(wordPressActuator.searchCommentOnPost("Hey this is comment contain " + disallowedWord));
//        assertNotNull("This is comment contain " + disallowedWord, );
    }
    @Then("New comment transfer to trash")
    public void new_comment_transfer_to_trash() {
        // Admin log in first
        loginSequence();
        // admin open trash from dashboard
        wordPressActuator.openDashboardTab();
        wordPressActuator.openTrash();
        // check if comment reach to trash
        String disallowedComment = wordPressActuator.searchCommentOnTrash("Hey this is comment contain " + disallowedWord).getText();
        assertEquals("Comment with disallowed keyword didnt reach trash...", "Hey this is comment contain " + disallowedWord,disallowedComment);
    }
    public void disallowedKeyWordsSequence(){
        // All steps to add word to disallowed keywords.
        wordPressActuator.openDashboardTab();
        wordPressActuator.openSettingsTab();
        wordPressActuator.goToDiscussionSettings();
        wordPressActuator.adminDisallowedWord(disallowedWord);
        wordPressActuator.adminSubmitChanges();

    }

    private static void loginSequence() {
        // All necessary steps for login.
        wordPressActuator.goToLogin();
        wordPressActuator.enterLoginInfo(ADMIN_USERNAME, ADMIN_PASSWORD);
        wordPressActuator.submitLoginInfo();
        assertNotNull("Login failed...", wordPressActuator.adminWelcomeMessage());

    }

}

