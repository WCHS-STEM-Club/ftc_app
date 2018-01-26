# Contributing to this app

## First thing you need: learn Git

[Git Book](https://git-scm.com/book/en/v2)  
[Interactive Git Tutorial](https://try.github.io)

Go ahead and read those. Understand git enough to be able to use it in some way (through Android Studio, the command line, or Github desktop) and to know what the terminology means.

## Making a change

### Branching

Wneh you want to add or remove something from the app, the first step is to create a new branch. Make sure to name it something descriptive, for example `travis-no-emulator` instead of `changes`. You are reccomended to use GitHub's built in functionality to make the branch. Use `git pull` to update your local repository, then check out the branch you've just created.

### Commits and pushes

As you make the changes you wanted to make, commit and push frequently. Make sure to use good, descriptive names for your commit messages, for example `enabled config file parsing` instead of `my hands are typing words`.

### Unit testing

If you create a class, remove a class, add a method to a class, remove a method from a class, or change the function of a method, make sure to update unit tests. Every method should have at least one test, likley more. See the README.md in the unit tests folder for more information.

### Pull request

When you're done, and all of your changes have been commited and your commits pushed to GH, create a pull request. Make sure that the pull request doesn't go to the official FTC repository! You'll have to change a dropdown for that. Give the pull request a good title, a good description, and assign someone to review it. If all unit tests pass, and your reviewer gives it the thumbs up, merge your branch into master and delete the old branch.

## Reporting a bug

If you're running the robot and something doesn't work right, or you're programming and something doesn't seen to be designed correctly, create an issue on GH.

Give it a descriptive title and description and assign a programmer to work on it. Be sure to say how the bug appears and any information you have on why it's happening.
