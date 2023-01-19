# Testing WordPress using Cucumber
This directory contains the cucumber files for testing the post module of the WordPress application.

## Running the tests
Run ```mvn test``` to run all the tests.

## Feature files
The behaviors that we tested are in the feature files that inside the [resources/WordPress](resources/WordPress) directory. See the files for a detailed description of the tests.

Contains 1 feature, 3 stories:

Admin adds disallowed key word.
Guest comment on some post.
Admin enable keyword, guest comment with the disallowed key word.
Pre-Conditions:

Admin is logged in.
Commentable post is exist.
Step files
The step files in the src/test/java/WordPress directory contain the code that defines how each sentence in the feature files is translated to Selenium actions. See the files for a detailed description of the implementation.

## Step files
The step files in the [src/test/java/WordPress](src/test/java/WordPress) directory contain the code that defines how each sentence in the feature files is translated to Selenium actions. See the files for a detailed description of the implementation.
