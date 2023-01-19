

defineEvent(SeleniumSession, 'PostComment', function(session){
   session.click("//input[@id='submit']")
})

defineEvent(SeleniumSession, 'WriteComment', function(session, e){
    bp.log.info(e)
    session.click("//a[contains(text(),'" + e.title + "')]")
    bp.log.info(e)
    session.waitForVisibility("//textarea[@id='comment']")
    session.scrollToBottom("//textarea[@id='comment']")
    session.writeText("//textarea[@id='comment']", e.DKW)
})

defineEvent(SeleniumSession, 'DisallowKeyWord', function(session, e){

    bp.log.info(e)
    // admin insert Username text
    session.writeText("//*[@id='user_login']", e.Username);
    // admin insert Password text
    session.writeText("//*[@id='user_pass']", e.Password);
    // admin press Log in - submit button
    session.click("//input[@id='wp-submit']");
    // maybe add wait until adminWelcomeMessage event occur?
    session.waitForVisibility("//*[contains(text(),'Welcome to WordPress!')]")
    session.moveToElement("//body/div[@id='wpwrap']/div[@id='adminmenumain']/div[@id='adminmenuwrap']/ul[@id='adminmenu']/li[@id='menu-settings']/a[1]/div[2]")
    session.click("//a[contains(text(),'Discussion')]");
    bp.log.info(e)
    session.moveToElement("//textarea[@id='disallowed_keys']")
    session.writeText("//textarea[@id='disallowed_keys']", "\n" + e.DKW + "\n", true);
    session.click("//input[@id='submit']");
})

defineEvent(SeleniumSession, "CheckTrash", function(session, e){
    bp.log.info(e)

    session.click("//body/div[@id='wpwrap']/div[@id='adminmenumain']/div[@id='adminmenuwrap']/ul[@id='adminmenu']/li[@id='menu-dashboard']/a[1]/div[2]")
    session.click("//body/div[@id='wpwrap']/div[@id='wpcontent']/div[@id='wpbody']/div[@id='wpbody-content']/div[3]/div[2]/div[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/ul[2]/li[6]/a[1]")
    session.waitForVisibility("//body[1]/div[1]/div[2]/div[2]/div[1]/div[3]/form[1]/table[1]/tbody[1]/tr[1]/td[2]/p[1]")
})