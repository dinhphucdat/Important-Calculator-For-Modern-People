# Important Calculator For Modern People
## Prologue
The more the world develops, the more societal expectation and pressure every human being has to deal with, and hence the unhealthier they get. Many applications and software have been invented to respond to people's needs, ranging from education to amusement. However, the modern world has long forgotten one, and very crucial but underrated, aspect - physical and mental health. Although a few early health applications have emerged to address such an issue, the public health, at least in the US, is still alarming. Thus, we together designed and created this application to help users keep track of some aspects of quantitative physical health.
## Acknowledgement
In this section, I want to shout out loud to [Martin Miller](https://github.com/martinobarbino), [Trey Fellner](https://github.com/Treysgit), and [Nikhil Jangir](https://github.com/Nikhil433-dell) to express my utmost gratitude for their dedication and collaboration upon this project's completion.
## Application Demonstration

><video width="500" alt="App Demo" src="https://github.com/user-attachments/assets/4744833e-0e0d-4a13-ab78-980dca91f5a8" />

## Overview
This application is a calculator group including four different health calculators depending on users' health needs, namely [**Calorie Calculator**](src/Calorie_Calculator), [**BMI Calculator**](src/bmi_calculator), [**Pregnancy Calculator**](src/Pregnancy_calculator), and [**One Rep Max Calculator**](src/OneRepMax). More on these four calculators can be found [here](https://www.calculator.net/fitness-and-health-calculator.html) and [here](https://americanpregnancy.org/resources/pregnancy-calculator/). It uses the [Graphical User Interface](https://en.wikipedia.org/wiki/Graphical_user_interface) to provide an interactive environment that is friendlier to the users - it only needs from them a few clicks, that's it, no need to do stuff with a harder environment as the [terminal interface](https://en.wikipedia.org/wiki/Text-based_user_interface). One thing to note is that there may seem an outlier, the Pregnancy Calculator, in the midst of the other three fitness calculators. The reason why we decided to incorporate it was because we can imagine how hard it is for pregnant women to keep fit since women after childbearing tend more likely to become overweight and experience non-transmitted diseases such as Type II Diabetes, so it should be ideal to make a tool for them to monitor their pregnancy status and then come up with latter healthful moves - maybe to do some gentle yoga, but we never and ever recommend them weight-lifting!
## Discussion
### The Homepage
This serves as the main interface containing all four calculators, which looks like this when [`Homepage.java`](src/homepage/HomePage.java) is executed:

> [!NOTE]
> _Heads-up: This is the only file that needs to get executed as a whole since the other files/classes will be automatically imported into this._
> _Also, please note that as soon as it gets executed, this program attempts to parse the [`BMIDescription.txt`](`BMIDescription.txt`), so make sure to include it at the same level as the `bin` and `src` folders in your own Java Project!_

><img width="500" alt="homepageCalc" src="https://github.com/user-attachments/assets/e74cd2ca-b8e8-4374-969a-737138d7687f" />

As can be seen in the image, the application aims at [minimal graphic design](https://desygner.com/blog/minimalist-graphic-design/), which effectively reduces technical difficulties while leveraging accessibility in the meantime. There are four buttons leading to four different health calculators as labelled on each. Its design was modelled based on and adapted from the [Metal Look And Feel](https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/plaf/metal/MetalLookAndFeel.html) supported by the [Java Swing Library](https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/index.html?javax/swing/package-summary.html). Its code-based environment can be navigated using this [path](src/homepage).
### The Calorie Calculator
From the homepage, the UI should look like below when the button leading to this calculator is clicked on:

><img width="500" alt="Calorie" src="https://github.com/user-attachments/assets/f5702bdb-8f24-4da7-929c-98ead6d48e5c" />

The image illustrates the Calorie Calculator when inadvertent clicks are expected, that is, when the inputs are not numerical. It provides two fields for height and weight inputs, then a drop down menu for various activity levels. As soon as the calculate button is triggered, the result will be displayed in the text area down the lower half. Invalid non-numerical input is well-handled with descriptive error message displayed in the very same text area. Its code-based environment can be navigated using this [path](src/Calorie_Calculator).
### The BMI Calculator
If we exit the previous calculator with the "back to the homepage" button and then click on the button leading to the BMI, the UI should look like this:

><img width="500" alt="BMI" src="https://github.com/user-attachments/assets/08b558ab-48ee-42c7-983b-e4fbfefbc0dc" />

This obtains the most complex design out of all four calculators, since it has to perform different tasks concurrently as soon as the users specify their values needed for the BMI and then hit "calculate", such as reporting their BMI-specific output, visualizing it on the scale, and displaying informative guideline on a more healthful life peculiar to their specific BMI class (there are 7 BMI classes) - which explains why the `BMIDescription.txt` is needed. This harmony between tasks was accounted for by the [Model-View-Controller](https://www.geeksforgeeks.org/mvc-design-pattern/) architecture, a typical Object-Oriented Design. An [error log message window](https://www.google.com/url?sa=i&url=https%3A%2F%2Flearn.microsoft.com%2Fen-us%2Fwindows%2Fwin32%2Fuxguide%2Fmess-error&psig=AOvVaw3mWXPpi35QagvSFOWJyTML&ust=1735085156621000&source=images&cd=vfe&opi=89978449&ved=0CBQQjhxqFwoTCPC4yoWOv4oDFQAAAAAdAAAAABAE) will pop up if non-numerical data are input. Its code-based environment can be navigated using this [path](src/bmi_calculator). A potential problem arising when this calculator's interface is magnified is that a portion of the scale's labels can be eaten up on both two sides, and developers are working on improving this in the near future.
### The Pregnancy Calculator
Similarly, the UI will look like this as soon as the button to the pregnancy calculator is triggered:

<img width="500" alt="BMI" src="https://github.com/user-attachments/assets/42f45020-9f9f-41e0-8e91-15fbece89595" />

If the BMI calculator is told to have the most complex design, it should be followed by this second candidate, the pregnancy calculator, since it provides five different pregnancy calculating methods via a drop-down menu to choose from based on various pregnancy settings and contexts. Such a feature was successfully achieved using the built-in [JComboBox](https://docs.oracle.com/javase/8/docs/api/javax/swing/JComboBox.html) provided by the [Java Swing Library](https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/index.html?javax/swing/package-summary.html). Inadvertent clicks are handled with an [error log message window](https://www.google.com/url?sa=i&url=https%3A%2F%2Flearn.microsoft.com%2Fen-us%2Fwindows%2Fwin32%2Fuxguide%2Fmess-error&psig=AOvVaw3mWXPpi35QagvSFOWJyTML&ust=1735085156621000&source=images&cd=vfe&opi=89978449&ved=0CBQQjhxqFwoTCPC4yoWOv4oDFQAAAAAdAAAAABAE). One big issue here is that it may be hard for users to adhere to the strict format rule when typing the date, so input failure may always happen. Therefore, user's convenience should be one of the key goals our team is working on going forward.
### The One Rep Max Calculator
Finally, the interface for the One Rep Max Calculator looks like this:

><img width="500" alt="OneRepMax" src="https://github.com/user-attachments/assets/795591a4-5162-4b15-bb5d-a1835ea8585d" />

This calculator is the most successful in terms of program minimalization and simplification, specifically from around 4 Java classes down to only one class that encapsulates all necessary functionality. This makes it easier for later developers if they intend to enhance its performance. Despite being reduced, it still supports three different weight-lifting calculating methods shown in the demonstration video or the first link provided in the overview section. Invalid non-numerical input is well-handled with informative message displayed down the lower half.
## Set up and execution
- The first step is to get the [`src.tar`](src.tar) file installed and decompressed.
    - For the Eclipse Integrated Development Environment, import the `src` folder that has just been decompressed into an already created Java Project. This src folder should be at the same level as the `bin` folder.
    - For Visual Studio Code, the step is similar.
- You totally can download individual files, one by one, and then import all the files into an already created `src` folder.
- The only Java file you need to care about is [`HomePage.java`](src/homepage/HomePage.java), which you need to execute and any other Java file content will automatically be imported into this file. Make sure to put [`BMIDescription.txt`](BMIDescription.txt) at the same level as the `src` and `bin` folders in your Java Project!
## Epilogue
If you have gotten this far, thank you so much for your attention! I hope that you have gained an 10,000-feet view of what our project looks like. Have a good day, or night! :star_struck: