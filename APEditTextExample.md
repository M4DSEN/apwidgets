# APEditTextExample #

![http://www.lundstedt.biz/apwidgets/images/PEditTextExample.jpg](http://www.lundstedt.biz/apwidgets/images/PEditTextExample.jpg)

```

import apwidgets.*;

APWidgetContainer widgetContainer; 
APEditText textField;

void setup(){
  
  widgetContainer = new APWidgetContainer(this); //create new container for widgets
  textField = new APEditText(20, 100, 150, 50); //create a textfield from x- and y-pos., width and height
  widgetContainer.addWidget(textField); //place textField in container
  
}

void draw(){
  
  background(0); //black background
  
  text(textField.getText(), 10, 10); //display the text in the text field
}

```