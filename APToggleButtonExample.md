# APToggleButtonExample #

```
import apwidgets.*;

APWidgetContainer widgetContainer; 
APToggleButton toggleButton;

void setup(){
  
  widgetContainer = new APWidgetContainer(this); //create new container for widgets
  toggleButton = new APToggleButton(10, 200, 100, 70, "Show rectangle"); //create new toggle button from x- and y-pos. and label.
  widgetContainer.addWidget(toggleButton); //place toggle button in container
  toggleButton.setChecked(true); //toggle button is on from the beginning
  
  //the following methods specify individual labels for on and off
//  toggleButton.setTextOn("Rectangle on");
//  toggleButton.setTextOff("Rectangle off");
}

void draw(){
  
  background(0); //black background
  
  if(toggleButton.isChecked()){ //if the toggle button is on
    rect(50, 20, 100, 100); // draw the rectangle
  }
}
```