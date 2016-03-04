# APRadioButtonExample #

```
import apwidgets.*;

APWidgetContainer widgetContainer; 
APRadioButton redAlt;
APRadioButton greenAlt;
APRadioButton blueAlt;
APRadioGroup radioGroup;

void setup(){
  
  widgetContainer = new APWidgetContainer(this); //create new container for widgets
  
  radioGroup = new APRadioGroup(10, 10); //create a new radiogroup
  radioGroup.setOrientation(APRadioGroup.HORIZONTAL);
 // radioGroup.setOrientation(APRadioGroup.VERTICAL);
  
  redAlt = new APRadioButton("Red  "); //create new radiobutton from label.
  greenAlt = new APRadioButton("Green "); //create new radiobutton from label.
  blueAlt = new APRadioButton("Blue"); //create new radiobutton from label.
  
  
  
  radioGroup.addRadioButton(redAlt); //place radiobutton in radiogroup
  radioGroup.addRadioButton(greenAlt); //place radiobutton in radiogroup
  radioGroup.addRadioButton(blueAlt); //place radiobutton in radiogroup
    
  redAlt.setChecked(true); //redAlt is chosen from the start
    
  widgetContainer.addWidget(radioGroup);
  
  
  
 /* 
 Do not add radio buttons to the radio group after 
 the radio group is added to the widget container.
 Do not call setChecked in setup after the radiogroup is added to a container.
 */
  
}

void draw(){
  
  if(redAlt.isChecked()){
    background(255, 0, 0); //red background
  }else if(greenAlt.isChecked()){
    background(0, 255, 0); //green background
  }else if(blueAlt.isChecked()){
    background(0, 0, 255); //blue background
  }
}
```