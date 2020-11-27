# Creating a single-jar (FatJar) JavaFX application with JFoenix (WIP)

Ever since JavaFX stopped shipping with the Java JDK, it is "hell" to properly create a working single-jar application (also called FatJar) with JavaFX, even more with external UI libraries like [JFoenix](https://github.com/jfoenixadmin/JFoenix). I went through several days of hell so you don't have to. I will be going through, step-by-step what you need to do to end up with a single .jar file that runs on double click and doesn't open a command prompt, with JFoenix.

## Prerequisites

1. Any version of the [JavaFX JDK](https://gluonhq.com/products/javafx/), prefereably the LTS or the latest release.
2. Any version of the [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html), prefereably the LTS or the latest release.
3. IntelliJ IDEA or any IDE that allows you to change the version Java JDK version for JavaFX projects.
4. Optional if you want to use JFoenix: [JFoenix 9.0.10](https://github.com/jfoenixadmin/JFoenix) or the latest version.

I'm not sure how using different versions of the JavaFX JDK and Java JDK will affect things, So to be safe just use similar versions, i.e. JavaFX JDK 15 with Java JDK 15

## Creating the Project

I'm using IntelliJ IDEA for creating the JavaFX application.

### Project Setup

1. Create a new JavaFX project. `New Project -> JavaFX -> Choose your preferred Java JDK version -> Give your project a name -> Finish`
2. Inside your new project `src` folder, contains a `sample` package which has a fxml file, `sample.fxml`, with its controller, `Controller.java`
3. You will see that all the JavaFX imports have errors. To fix this, go to `File -> Project Structure -> Libraries` or `CTRL + SHIFT + ALT + S` then navigate to `Libraries`.
4. Press the `+` button and select `Java` from the dropdown menu.
5. Navigate to the `lib` folder within your JavaFX JDK install location. i.e `C:\Program Files\Java\javafx-sdk-15.0.1\lib`
6. Click `OK` and Click `OK` again on the `Choose Modules` popup.

This next step is optional if you don't want to use JFoenix.

7. Press the `+` button again, select `Java` again. This time, navigate to your JFoenix .jar file location. i.e `C:\java-ext-lib\jfoenix-9.0.10.jar`

## Building and Distributing
