# FrameworkChallenge

## Type of framework

I choose to work with and hybrid framework, in it I'm aplying linear and data driven frameworks. The linear because for the mayority of available methods it required to have a request token and a session ID, and that process consist of 3 linear methods, after those are executed and you have the variables you can execute the others methods. 

And the data driven framework is used when data provide by external files ares used as parameters or part of the body in the desiremethod.

## Tools

### Built automation
Maven was use as a build automation tool, because it have an simple building and organization process, and allows to work with different dependecies to apply other tools that are necesary in the fremework just by adding it in the POM file, sush us API SUT-oriented tools, report tools, BDD tools and test runners. In addition that can be used in java, wich was a requeriment for the challenge. 

### Test runner
I decided to used TestNG as a test runner tool over Junit because it allows to work with depending methods in an easier way, and considering that the it's important a linear approch for and important part of the execution it was a very useful tool to implements; it have a report tool embedded, it's a little plain compare to tools like Allure, nontheless is practical for fast testing; it have a method called 'DataProvider' that allows to execute a test for an x number of input data, which is useful for the data driven approach that I decide to implemented; and it allows to run parallel testing, which can be a really helpfull technique when an automation testing is being executed. 

### API
For the API SUT-oriented tools I selected Rest-Assured because it supports given-when-then anotations which makes easier to undertand and create the test cases; it allows the integration with several test runners like TestNG and Junit. In addition, because is one of the most used tools in Endava for the proyects and in our training is the tool that is being explained, so I considered that this challenge was a good way to getting started in the tool and how it works. 

## Test cases
The 5 test cases that I execute where:

1. Create a list
2. Add movies to a list
3. Clear a list
4. Delete a list
5. Search for a movie

For all it's necesary to request a token, authenticated the token and create a session. 