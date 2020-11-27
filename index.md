# Creating a single-jar (FatJar) JavaFX application with JFoenix
Ever since JavaFX stopped shipping with the Java JDK, it is "hell" to properly create a working single-jar application (also called FatJar) with JavaFX, even more with external UI libraries like [JFoenix](https://github.com/jfoenixadmin/JFoenix). I went through several days of hell so you don't have to. I will be going through, step-by-step what you need to do to end up with a single .jar file that runs on double click and doesn't open a command prompt, with JFoenix.

## Prerequisites

1. Any version of the [JavaFX JDK](https://gluonhq.com/products/javafx/), prefereably the LTS or the latest release.

2. Any version of the [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html), prefereably the LTS or the latest release.

3. IntelliJ IDEA or any IDE that allows you to change the Java JDK version for JavaFX projects.

4. Latest version of [SceneBuilder](https://gluonhq.com/products/scene-builder/#download), which is a GUI drag-and-drop application for JavaFX.

5. Optional if you want to use JFoenix: [JFoenix 9.0.10](https://github.com/jfoenixadmin/JFoenix) or the latest version.

I'm not sure how using different versions of the JavaFX JDK and Java JDK will affect things, So to be safe just use similar versions, i.e. JavaFX JDK 15 with Java JDK 15

## Creating the Project

I'm using IntelliJ IDEA for creating the JavaFX application.

### Project Setup

Create a new JavaFX project. `New Project -> JavaFX -> Choose your preferred Java JDK version -> Give your project a name -> Finish`

Inside your new project `src` folder, contains a `sample` package which has a fxml file, `sample.fxml`, with its controller, `Controller.java`

You will see that all the JavaFX imports have errors. To fix this, go to `File -> Project Structure -> Libraries` or `CTRL + SHIFT + ALT + S` then navigate to `Libraries`.

Press the `+` button and select `Java` from the dropdown menu.

Navigate to the `lib` folder within your JavaFX JDK install location. i.e. `C:\Program Files\Java\javafx-sdk-15.0.1\lib`

Click `OK` and Click `OK` again on the `Choose Modules` popup.

Optional: Press the `+` button again, select `Java` again. This time, navigate to your JFoenix .jar file location. i.e. `C:\java-ext-lib\jfoenix-9.0.10.jar`

Hit `Apply` then `Ok`

### Creating a Launcher class

Create a new class by right-clicking the `sample` package then `New -> Java Class -> Give it a name. I named it "Launcher"`.

Make sure the `Launcher.java` class follows the below code:

```java
package sample;

public class Launcher {
    public static void main(String[] args) {
        // Where 'Main' is the main class that runs the JavaFX application, in our case it is 'Main.java'
        Main.main(args);
    }
}
```

### Configuring the Running configuration

Go to `Run -> Edit Configurations`.

Change the `Main class` to the `Launcher` class. i.e. `sample.Launcher`.

Add the following to `VM options`:

```
--module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml
or
--module-path "C:\Program Files\Java\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml
```

`%PATH_TO_FX%` is the path to the `lib` folder within the JavaFX JDK you installed. i.e. `C:\Program Files\Java\javafx-sdk-15.0.1\lib`

![Running Configuration](https://i.imgur.com/gzgK65m.png)

Click `Apply` and `Ok`

You should be able to run it now by pressing `Shift+F10` on your keyboard or clicking the green arrow on the top right corner of IntelliJ IDEA.

The following steps are optional if you want to use JFoenix.

Open `sample.fxml` in SceneBuilder.

Click the cog icon next to the search bar on the toolbox and select `JAR/FXML Manager`

![Figure1](https://i.imgur.com/cI46P8S.png)

Click `Add Library/FXML from file system` and navigate to where you have the JFoenix .jar file.

![Figure2](https://i.imgur.com/Ou7TpFQ.png)

Check all the components in the `Import Dialog` and click `Import Components`

![Figure3](https://i.imgur.com/XWDr0I3.png)

Style it according to what you want, make sure atleast one JFoenix component is added onto the file. For example, I added a button. Under the `Properties` section, I gave it the text `Print Message` and under the code section, give it an `fx:id` and an `On Action` function name. i.e. `btnPrintMessage` for `fx:id` and `PrintMessage` for `On Action`

![Figure4](https://i.imgur.com/TmD5CD4.png)

Go to `View -> Show Sample Controller Skeleton`

![Figure5](https://i.imgur.com/8zC8WEq.png)

Check the `Full` checkbox and copy the whole thing. Save the .fxml file.

![Figure6](https://i.imgur.com/PdAlir2.png)

Paste and overwrite everything except `package sample;`, rename `PleaseProvideControllerName` to `Controller` or whatever you named the controller class as. Now your `Controller.java` should look something like:

```java
package sample;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnPrintMessage;

    @FXML
    void PrintMessage(ActionEvent event) {
        System.out.println("Button is pressed");
    }

    @FXML
    void initialize() {
        assert btnPrintMessage != null : "fx:id=\"btnPrintMessage\" was not injected: check your FXML file 'sample.fxml'.";

    }
}
```

Open `sample.fxml` as text and add a `fx:controller="sample.Controller"` attribute to the parent element. i.e. `AnchorPane` in my case. Your `sample.fxml` should look something like the code below:

```xml
<AnchorPane maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity" prefHeight="400.0"
            prefWidth="600.0" xmlns="http://javafx.com/javafx/11.0.1" xmlns:fx="http://javafx.com/fxml/1"
            fx:controller="sample.Controller">
            <!-- fx:controller specifies the controller for the fxml file -->
    <children>
        <VBox alignment="CENTER" layoutX="220.0" layoutY="90.0" prefHeight="400.0" prefWidth="600.0"
              AnchorPane.bottomAnchor="0.0" AnchorPane.leftAnchor="0.0" AnchorPane.rightAnchor="0.0"
              AnchorPane.topAnchor="0.0">
            <children>
                <JFXButton fx:id="btnPrintMessage" onAction="#PrintMessage" style="-fx-background-color: #a8323a;"
                           textFill="WHITE">
                    <font>
                        <Font size="18.0"/>
                    </font>
                </JFXButton>
            </children>
        </VBox>
    </children>
</AnchorPane>
```

Now when you run it, you should see your window popup with the JFoenix controls. If you see that your window is smaller than you set it within SceneBuilder, check your `Main.java` file and you should see a line of code that looks like `primaryStage.setScene(new Scene(root, 300, 275));`. Just get rid of the last two arguments which are the width and height of the window. It should now just be `primaryStage.setScene(new Scene(root));`

![Figure7](https://i.imgur.com/cIUXn81.png)

## Building and Distributing

### Build setup

Go to `File ->  Project Structure` or `CTRL+SHIFT+ALT+S` and go to `Artifacts`.

Press the `+` button and select `JAR -> From modules and dependencies...`

A `Create JAR from Modules` window should pop up. Change the `Main Class` to the Launcher class

![Figure8](https://i.imgur.com/45TXfE4.png)

You should see that a new artifact is created with all the dependencies included.

![Figure9](https://i.imgur.com/vyqHxMV.png)

Press the `+` button and select `Directory Content`. Navigate to the `bin` folder in where you install your JavaFX JDK. i.e. `C:\Program Files\Java\javafx-sdk-15.0.1\bin`. Hit `Apply` and `OK`.

![DirectoryContent](https://i.imgur.com/scTXuwC.png)

Now you can build your .jar file by going to `Build -> Build Artifacts...` and on the popup that appears, click the newly created JAR artifact setting to build it.

![Figure10](https://i.imgur.com/ymXJz9H.png)

The built JAR file is located within `your project folder\out\artifacts\ProjectName_jar\ProjectName.jar`. You can run it by double-clicking the JAR file.

![Figure11](https://i.imgur.com/eeEDO8p.png)

## References

https://stackoverflow.com/questions/53533486/how-to-open-javafx-jar-file-with-jdk-11

https://openjfx.io/openjfx-docs/#IDEA-IDE
