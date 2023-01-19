///* @Provengo summon selenium */
//
///*
//guestClickOnPost define when Guest choose post to enter with given Title
//*/
//defineEvent(SeleniumSession, "ClickOnPost", function(session, e){
//    bp.log.info(e)
//    session.click("//a[contains(text(),'" + e.title + "')]")
//
//})
//
//defineEvent(SeleniumSession, "FillComment", function(session, e){
//    bp.log.info(e)
//    session.waitForVisibility("//textarea[@id='comment']")
//    session.scrollToBottom("//textarea[@id='comment']")
//    session.writeText("//textarea[@id='comment']", e.DKW)
//})
//
//defineEvent(SeleniumSession, "PostComment", function(session){
////    session.click("//input[@id='submit']")
//})
//
//defineEvent(SeleniumSession, "Login", function(session, e){
//    bp.log.info(e)
//    // admin insert Username text
//    session.writeText("//*[@id='user_login']", e.Username);
//    // admin insert Password text
//    session.writeText("//*[@id='user_pass']", e.Password);
//    // admin press Log in - submit button
//    session.click("//input[@id='wp-submit']");
//    // maybe add wait until adminWelcomeMessage event occur?
//    session.waitForVisibility("//*[contains(text(),'Welcome to WordPress!')]")
//})
//
//defineEvent(SeleniumSession, 'GoToDiscussionSettings', function(session){
////    session.moveToElement("//body/div[@id='wpwrap']/div[@id='adminmenumain']/div[@id='adminmenuwrap']/ul[@id='adminmenu']/li[@id='menu-settings']/a[1]/div[2]")
////    session.click("//a[contains(text(),'Discussion')]");
//})
//
//defineEvent(SeleniumSession, 'DisallowKeyWord', function(session, e){
//    bp.log.info(e)
//    session.moveToElement("//textarea[@id='disallowed_keys']")
//    session.writeText("//textarea[@id='disallowed_keys']", "\n" + e.DKW + "\n", true);
//    session.click("//input[@id='submit']");
//})
//
//defineEvent(SeleniumSession, 'CheckTrash', function(session, e){
//
//})
