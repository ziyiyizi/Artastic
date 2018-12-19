# The Artastic Project

## Overview
Artastic is an experimental project of software engineering with a deployment of JavaEE backend and React Frontend. 

Project ‘Artastic’ is designed as a complete solution of an online community for creative artists. Which means it's a platform where members can exhibit artworks of their own, share their ideas, and make comments on works of others.

Link to Frontend: https://github.com/Aquamarino/ArtasticFrontend

### Let's have a peek at it:
![Alt text](/src/Artastic_Community.gif "Project Artastic")

## Software Requirement Specifications

The purpose of Artastic Community is to:
* provide an easy approach to store user's artwork, bring exposure to artworks of all kinds. 
* provide easy access to all users with latest publicity from favored artists.
* prompt interactions by various means between artists and followers for inspiration.

According to the requirements of users, the following functionalities will be taken into consideration:
* Acount Creation and management
* Artwork Submission (Fan-arts or Originals)
* Exploring the community for artworks
* Adding tags to works
* Searching for artworks
* Making comments on artworks
* Following Artastic members for subscription
* Adding artworks to favorites or records
* Receiving notifications from other members
* For experimental purpose, Analysis for artwork popularity will be provided to all users who play an role of artwork provider.

Hightlight of Creativity will be:
* Analysis charts will be provided, so that submitters can learn detailed information of artworks’ popularity, such as trending of click volume, audience analysis, etc.

## Project Environment & toolkits

* eclipse-javaEE
* apache tomcat v9.0
* react.js v16.6.3
* maven v3.6
* spring boot v1.4.1
* webpack v4.0
* spring data jpa
* thymeleaf
* apache shiro
* websocket
* react-bootstrap 3 & 4
* material-ui
* babel v7.2.1
* aliyun object storage service

### Why choose spring?
---
Modern software programming benefits from IoC(inversion of control) and AOP(Aspest oriented programming), DI(dependency injection), DL(dependency lookup). inversion of control (IoC) is a programming technique in which object coupling is bound at run time by an assembler object and is typically not known at compile time using static analysis. There is a decoupling of the execution of a certain task from implementation.

Every module can focus on what it is designed for. Replacing modules has no side effect on other modules. Modules make no assumptions about what other systems do but rely on their contracts. 

Dependency injection is a pattern used to create instances of objects that other objects rely on without knowing at compile time which class will be used to provide that functionality. IoC relies on dependency injection because a mechanism is needed in order to activate the components providing the specific functionality.

The BeanFactory interface provides an advanced configuration mechanism capable of managing objects of any nature. The ApplicationContext interface builds on top of the BeanFactory (it is a sub-interface) and adds other functionality such as easier integration with Spring’s AOP features, message resource handling (for use in internationalization), event propagation, and application-layer specific contexts such as the WebApplicationContext for use in web applications.

### Why choose Spring boot?
---
 Spring Boot is designed to get you up and running as quickly as possible, with minimal upfront configuration of Spring. We will enjoy a great convenience utilizing springboot because it’s out-of-box easy to use. 
 
 We gain unique convenience from Springboot in several ways below: Build anything: REST API, WebSocket, web, streaming, tasks, and more. Simplified security. Rich support for SQL and NoSQL. Embedded runtime support: Tomcat, Jetty, and Undertow. Curated dependencies that just work.

### Why choose all in Javascript over JSP?
---
 Actually this comparison makes no sense because JSP is a sophisticated Java servlet. JSPs are loaded into the server, and from the Java EE Web application, operated and packaged as .war or .ear file archives. It allows Java code and specific predefined actions to communicate with static web markup content. The page that results from this interaction is compiled and executed on this specific server, and is viewed as an HTML or XML document.
 
 However, Javascript is used to give programmatic access to objects that are found in both client application and other applications pertinent to the running of a specific program. It was intentionally created client-side (meaning, client orientated and run) in order to provide support for dynamic websites. 
 
 JavaScript helps to separate frontend programming from backend programming, allowing a specific application to run on the client side, it also help to relieve server side calculation resources. So I guess you all know what I mean now, the goal of adopting from these two technologies is to provide a dynamic asynchronized web application. 
 
 For frontend, we choose React because while react is powerful with all javascript capabilities, it also makes it painless to create interactive UIs. Design simple views for each state in your application, and React will efficiently update and render just the right components when your data changes. 

## Architechture , Deployment & Database Design
> ![Alt text](/src/Architecture.jpg "Architecture") 

    There are three Layers in the overall structure. The presentation layer is settled in the client side while is depends on the service layer, which is deployed in the private server.

> ![Alt text](/src/Deployment_Diagram.jpg "Deployment") 

    We use Aliyun QOS for Picture storage because mysql is lightweight and favourable for basic relationship database, however the pictures will be a bottleneck in data travel streaming, where it would lead clients to unbearable time consumption. Aliyun provides a well-rounded solution for huge picture storage and is easy for configuration in Springboot.

> ![Alt text](/src/Database.png "Deployment") 

    The whole Database design is surrounded with User and Artwork. At first we thought it should be easy but well, it turned out to be far more sophisticated than we initially thought it would be. 

## Component Design

### Backend Design

* HomeController: Processing view mapping, render the corresponding view according to the URL address
* ArtworkController: Dealing with requests related to artworks, including exhibition artworks’ information, reviews, collections, clicks, etc.
* UserController: Dealing with requests related to users, including login, followers, presentation of user information, etc.
* NotificationController: Dealing with requests related to notifications, including sending and receiving notifications
* RegisterController: Dealing with requests related to registration
* SearchController: Dealing with requests related to search, searches can be performed based on specific keywords
* UploadPicController: Dealing with requests related to upload pictures, which use API of Aliyun Object Storage Service
* WeeklyController: return weekly summary of artworks

### Frontend Design

This part is under construction. Cuz i'm lazy and this part is non-related to JavaEE.

## Strategies of Collaboration

* Full usage of Github: We adopt github as a collaboration platform as well as a version control tool.

* (However, because the learning process is not smooth we find it necessary for core developers to surveil the code minor developers has produced before committing to git in order to avoid functional mismatch and codestyle cohesion. That's why only two members' activitiness is shown at github since the rest two submit their code to the major executive first.)

* Incremental development: Develop core functions in the first increment -- several main interfaces and login, registration, search functions. Add more advanced functions to the main interface in the second increment, and so on. 

* XP (Extreme Programming): Every week, we will determine some user stories which describe the various situations that users encounter when using our products. Then  we discuss and select the user stories with higher weights, concretely convert them into functions, and develop them in groups. After a week, we summarize the development progress and discard unnecessary or redundant functions.

## Issues encountered
* As for the notification subsystem we have spread blood and sweat trying to integrate ActiveMQ into the project. However the attempt failed because the techniques of ajax is not adopted in our project(we wisely switched to http5 standard 'fetch in promise' standard so ajax is no longer needed) Web applications without ajax didn't agree with activeMQ in springboot and no documentation could be found on the internet. Although ActiveMQ is great but without guidance we are fruitless. Actually it's the shortcoming of adopting Springboot because we expect everthing functions just out of the box but when it doesn't, the configuration would be much more painful.

* Javascript is a very fragile language and leaves developers with tons of questionable issues. Such situation I have walked into as you cannot read contents in the lists nested in lists[[],[]] with index. And in paticular circumstances you cannot read maps by dot(exampleMap.key) but you can achieve that goal by array indexes(exampleMap['key']). When you find out that javascript is such unreasonably buggy you will feel the same annoyance as i am right now.

## Code Contributors

* Wen Chao Jie
* Xia Jin Lei
* Meng Fan Yu
* Huang Shou Yu
* Yang Yan

