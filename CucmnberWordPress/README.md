# Testing WordPress using Cucumber
This directory contains the cucumber files for testing the disallowed keywords module of the WordPress application.

## Running the tests
Run ```mvn test``` to run all the tests.

## Feature files
The behaviors that we tested are in the feature files that inside the [resources/WordPress](resources/WordPress) directory. See the files for a detailed description of the tests.

Contains 1 feature, 3 stories:
1. Admin adds disallowed key word.
2. Guest comment on some post.
3. Admin enable keyword, guest comment with the disallowed key word.

Pre-Conditions:
1. Admin is logged in.
2. Commentable post is exist.
## Step files
The step files in the [src/test/java/WordPress](src/test/java/WordPress) directory contain the code that defines how each sentence in the feature files is translated to Selenium actions. See the files for a detailed description of the implementation.

$$*TODO* Make sure that each step is documented and properly writen (meaningful variable names, no magic number, etc.). See the [StepDefinitions.java](src/test/java/hellocucumber/StepDefinitions.java) file for an example.$$
