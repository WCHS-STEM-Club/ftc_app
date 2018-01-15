[![Build Status](https://travis-ci.org/WCHS-STEM-Club/ftc_app.svg?branch=unit-tests)](https://travis-ci.org/WCHS-STEM-Club/ftc_app)

# Unit testing
These unit tests are written using Junit 4. They should be available under the MIT licence.

## Writing a unit test
All classes should have one unit testing class. The testing class should be named <ClassToTest>Test.
Each testing class should have one method with the test annotation per type of input. For example,
a method to divide 3 by a supplied integer should have three methods: one to test division by a
positive number, one to test division by a negative number, and one to test division by 0. The
method should be named <nameOfMethodToTest>_<typeOfInput>. In the example, the methods should be
named divide3By_positive, divide3By_negative, and divide3By_0.