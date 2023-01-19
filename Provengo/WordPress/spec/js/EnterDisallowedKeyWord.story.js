/* @provengo summon selenium */
/* @provengo summon constraints */


/*
This story about user writing comment on post named "Hello Provengo", which contain Disallowed key word
that the admin should insert during the process, DKW - Watermelon
*/
story('Guest writing a Comment on Post', function(){
    let s = new SeleniumSession().start('http://localhost/wordpress/')
    s.writeComment({ title: 'Hello Provengo', DKW : 'Watermelon'})
    s.postComment()
})

/*
This Story about Admin adding Disallowed Key Word by navigating to
Settings->Discussion->Write Text 'Watermelon'
Then check Trash for Comment if User commented after the admin added the disallowed key word
Then the Trash would contain the comment, and the Feature works.
navigate: DashBoard->ScrollDown->Trash->Search comment contains 'Watermelon'
*/

story('Admin Insert Disallowed key word', function(){
    let s = new SeleniumSession().start('http://localhost/wordpress/wp-login.php');
    s.disallowKeyWord({Username: "admin", Password: "e1234", DKW : 'Watermelon'});
    s.checkTrash({DKW: "Watermelon"})
})


///*
//    Testing the Main scenario:
//    1. Admin adding Disallowed Keyword 'Watermelon'
//    2. Guest Comment on post name "Hello Provengo", 'Watermelon'
//    3. Admin check that the Guest comment moved to trash 'Watermelon'
//*/
story('Test', function(){
    block(Any("PostComment"), function(){
        waitFor(Any("EndOfAction").and(Any({eventName: "DisallowKeyWord"})))
    })

    block(Any("CheckTrash"), function(){
                waitFor(Any("EndOfAction").and(Any({eventName: "PostComment"})))
    })
})
