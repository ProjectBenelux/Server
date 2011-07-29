
import java.awt.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.*;

// class CalcFrame for creating a calculator frame and added windolistener to
// close the calculator
class CalcFrame extends Frame {
 CalcFrame( String str) {
// call to superclass
super(str);
// to close the calculator(Frame)
addWindowListener(new WindowAdapter() {
 public void windowClosing (WindowEvent we) {
//exit
 }
});
 }
}
// main class Calculator implemnets two interfaces ActionListener
// and ItemListener
public class Calculator implements ActionListener, ItemListener {
 // creating instances of objects
 CalcFrame fr;
 MenuBar mb;
 Menu view, font, about;
 MenuItem bold, regular, author;
 CheckboxMenuItem basic, scientific;
 CheckboxGroup cbg;
 Checkbox radians, degrees;
 TextField display;
 Button key[] = new Button[20]; // creates a button object array of 20
 Button clearAll, clearEntry, round;
 Button scientificKey[] = new Button[10]; // creates a button array of 8
 // declaring variables
 boolean addButtonPressed, subtractButtonPressed, multiplyButtonPressed;
 boolean divideButtonPressed, decimalPointPressed, powerButtonPressed;
 boolean roundButtonPressed = false;
 double initialNumber;// the first number for the two number operation
 double currentNumber = 0; // the number shown in the screen while it is being pressed
 int decimalPlaces = 0;
 // main function



public static void d() {
// constructor
Calculator calc = new Calculator();
calc.makeCalculator();


 }

 public static void x () {
// constructor

 }


 public void makeCalculator() {
// size of the button
final int BWIDTH = 25;
final int BHEIGHT = 25;
int count =1;
// create frame for the calculator
fr = new CalcFrame("Basic Calculator");
// set the size
fr.setSize(200,270);
fr.setBackground(Color.blue);;
// create a menubar for the frame
mb = new MenuBar();
// add menu the menubar
view = new Menu("View");
font = new Menu ("Font");
about = new Menu("About");
// create instance of object for View menu
basic = new CheckboxMenuItem("Basic",true);
// add a listener to receive item events when the state of an item changes
basic.addItemListener(this);
scientific = new CheckboxMenuItem("Scientific");
// add a listener to receive item events when the state of an item changes
scientific.addItemListener(this);
// create instance of object for font menu
bold = new MenuItem("Arial Bold");
bold.addActionListener(this);
regular = new MenuItem("Arial Regular");
regular.addActionListener(this);
// for about menu
author = new MenuItem("Author");
author.addActionListener(this);
// add the items in the menu
view.add(basic);
view.add(scientific);
font.add(bold);
font.add(regular);
about.add(author);
// add the menus in the menubar
mb.add(view);
mb.add(font);
mb.add(about);
// add menubar to the frame
fr.setMenuBar(mb);
// override the layout manager
fr.setLayout(null);
// set the initial numbers that is 1 to 9
for (int row = 0; row < 3; ++row) {
for (int col = 0; col < 3; ++col) {
 // this will set the key from 1 to 9
 key[count] = new Button(Integer.toString(count));
 key[count].addActionListener(this);
 // set the boundry for the keys
 key[count].setBounds(30*(col + 1), 30*(row + 4),BWIDTH,BHEIGHT);
 key[count].setBackground(Color.yellow);
 // add to the frame
 fr.add(key[count++]);
}
}
// Now create, addlistener and add to frame all other keys
//0
key[0] = new Button("0");
key[0].addActionListener(this);
key[0].setBounds(30,210,BWIDTH,BHEIGHT);
key[0].setBackground(Color.yellow);
fr.add(key[0]);
//decimal
key[10] = new Button(".");
key[10].addActionListener(this);
key[10].setBounds(60,210,BWIDTH,BHEIGHT);
key[10].setBackground(Color.yellow);
fr.add(key[10]);
//equals to
key[11] = new Button("=");
key[11].addActionListener(this);
key[11].setBounds(90,210,BWIDTH,BHEIGHT);
key[11].setBackground(Color.yellow);
fr.add(key[11]);
//multiply
key[12] = new Button("*");
key[12].addActionListener(this);
key[12].setBounds(120,120,BWIDTH,BHEIGHT);
key[12].setBackground(Color.yellow);
fr.add(key[12]);
//divide
key[13] = new Button("/");
key[13].addActionListener(this);
key[13].setBounds(120,150,BWIDTH,BHEIGHT);
key[13].setBackground(Color.yellow);
fr.add(key[13]);
//addition
key[14] = new Button("+");
key[14].addActionListener(this);
key[14].setBounds(120,180,BWIDTH,BHEIGHT);
key[14].setBackground(Color.yellow);
fr.add(key[14]);
//subtract
key[15] = new Button("-");
key[15].addActionListener(this);
key[15].setBounds(120,210,BWIDTH,BHEIGHT);
key[15].setBackground(Color.yellow);
fr.add(key[15]);
//reciprocal
key[16] = new Button("1/x");
key[16].addActionListener(this);
key[16].setBounds(150,120,BWIDTH,BHEIGHT);
key[16].setBackground(Color.yellow);
fr.add(key[16]);
//power
key[17] = new Button("x^n");
key[17].addActionListener(this);
key[17].setBounds(150,150,BWIDTH,BHEIGHT);
key[17].setBackground(Color.yellow);
fr.add(key[17]);
//change sign
key[18] = new Button("+/-");
key[18].addActionListener(this);
key[18].setBounds(150,180,BWIDTH,BHEIGHT);
key[18].setBackground(Color.yellow);
fr.add(key[18]);
//factorial
key[19] = new Button("x!");
key[19].addActionListener(this);
key[19].setBounds(150,210,BWIDTH,BHEIGHT);
key[19].setBackground(Color.yellow);
fr.add(key[19]);
// CA
clearAll = new Button("CA");
clearAll.addActionListener(this);
clearAll.setBounds(30, 240, BWIDTH+20, BHEIGHT);
clearAll.setBackground(Color.yellow);
fr.add(clearAll);
// CE
clearEntry = new Button("CE");
clearEntry.addActionListener(this);
clearEntry.setBounds(80, 240, BWIDTH+20, BHEIGHT);
clearEntry.setBackground(Color.yellow);
fr.add(clearEntry);
// round
round = new Button("Round");
round.addActionListener(this);
round.setBounds(130, 240, BWIDTH+20, BHEIGHT);
round.setBackground(Color.yellow);
fr.add(round);
// set display area
display = new TextField("0");
display.setBounds(30,90,150,20);
display.setBackground(Color.white);
// key for scientific calculator
// Sine
scientificKey[0] = new Button("Sin");
scientificKey[0].addActionListener(this);
scientificKey[0].setBounds(180, 120, BWIDTH + 10, BHEIGHT);
scientificKey[0].setVisible(false);
scientificKey[0].setBackground(Color.yellow);
fr.add(scientificKey[0]);
// cosine
scientificKey[1] = new Button("Cos");
scientificKey[1].addActionListener(this);
scientificKey[1].setBounds(180, 150, BWIDTH + 10, BHEIGHT);
scientificKey[1].setBackground(Color.yellow);
scientificKey[1].setVisible(false);
fr.add(scientificKey[1]);
// Tan
scientificKey[2] = new Button("Tan");
scientificKey[2].addActionListener(this);
scientificKey[2].setBounds(180, 180, BWIDTH + 10, BHEIGHT);
scientificKey[2].setBackground(Color.yellow);
scientificKey[2].setVisible(false);
fr.add(scientificKey[2]);
// PI
scientificKey[3] = new Button("Pi");
scientificKey[3].addActionListener(this);
scientificKey[3].setBounds(180, 210, BWIDTH + 10, BHEIGHT);
scientificKey[3].setBackground(Color.yellow);
scientificKey[3].setVisible(false);
fr.add(scientificKey[3]);
// aSine
scientificKey[4] = new Button("aSin");
scientificKey[4].addActionListener(this);
scientificKey[4].setBounds(220, 120, BWIDTH + 10, BHEIGHT);
scientificKey[4].setBackground(Color.yellow);
scientificKey[4].setVisible(false);
fr.add(scientificKey[4]);
// aCos
scientificKey[5] = new Button("aCos");
scientificKey[5].addActionListener(this);
scientificKey[5].setBounds(220, 150, BWIDTH + 10, BHEIGHT);
scientificKey[5].setBackground(Color.yellow);
scientificKey[5].setVisible(false);
fr.add(scientificKey[5]);
// aTan
scientificKey[6] = new Button("aTan");
scientificKey[6].addActionListener(this);
scientificKey[6].setBounds(220, 180, BWIDTH + 10, BHEIGHT);
scientificKey[6].setBackground(Color.yellow);
scientificKey[6].setVisible(false);
fr.add(scientificKey[6]);
// E
scientificKey[7] = new Button("E");
scientificKey[7].addActionListener(this);
scientificKey[7].setBounds(220, 210, BWIDTH + 10, BHEIGHT);
scientificKey[7].setBackground(Color.yellow);
scientificKey[7].setVisible(false);
fr.add(scientificKey[7]);
// to degrees
scientificKey[8] = new Button("todeg");
scientificKey[8].addActionListener(this);
scientificKey[8].setBounds(180, 240, BWIDTH + 10, BHEIGHT);
scientificKey[8].setBackground(Color.yellow);
scientificKey[8].setVisible(false);
fr.add(scientificKey[8]);
// to radians
scientificKey[9] = new Button("torad");
scientificKey[9].addActionListener(this);
scientificKey[9].setBounds(220, 240, BWIDTH + 10, BHEIGHT);
scientificKey[9].setBackground(Color.yellow);
scientificKey[9].setVisible(false);
fr.add(scientificKey[9]);
cbg = new CheckboxGroup();
degrees = new Checkbox("Degrees", cbg, true);
radians = new Checkbox("Radians", cbg, false);
degrees.addItemListener(this);
radians.addItemListener(this);
degrees.setBounds(185, 75, 3 * BWIDTH, BHEIGHT);
radians.setBounds(185, 95, 3 * BWIDTH, BHEIGHT);
degrees.setVisible(false);
radians.setVisible(false);
fr.add(degrees);
fr.add(radians);
fr.add(display);
fr.setVisible(true);
 } // end of makeCalculator
 public void actionPerformed(ActionEvent ae) {
String buttonText = ae.getActionCommand();
double displayNumber = Double.valueOf(display.getText()).doubleValue();
// if the button pressed text is 0 to 9
if((buttonText.charAt(0) >= '0') & (buttonText.charAt(0) <= '9')) {
if(decimalPointPressed) {
 for (int i=1;i <=decimalPlaces; ++i)
 currentNumber *= 10;
 currentNumber +=(int)buttonText.charAt(0)- (int)'0';
 for (int i=1;i <=decimalPlaces; ++i) {
 currentNumber /=10;
 }
 ++decimalPlaces;
 display.setText(Double.toString(currentNumber));
}
else if (roundButtonPressed) {
 int decPlaces = (int)buttonText.charAt(0) - (int)'0';
 for (int i=0; i< decPlaces; ++i)
 displayNumber *=10;
 displayNumber = Math.round(displayNumber);
 for (int i = 0; i < decPlaces; ++i) {
 displayNumber /=10;
 }
 display.setText(Double.toString(displayNumber));
 roundButtonPressed = false;
}
else {
 currentNumber = currentNumber * 10 + (int)buttonText.charAt(0)-(int)'0';
 display.setText(Integer.toString((int)currentNumber));
}
}
// if button pressed is addition
if(buttonText == "+") {
addButtonPressed = true;
initialNumber = displayNumber;
currentNumber = 0;
decimalPointPressed = false;
}
// if button pressed is subtract
if (buttonText == "-") {
subtractButtonPressed = true;
initialNumber = displayNumber;
currentNumber = 0;
decimalPointPressed = false;
}
// if button pressed is divide
if (buttonText == "/") {
divideButtonPressed = true;
initialNumber = displayNumber;
currentNumber = 0;
decimalPointPressed = false;
}
// if button pressed is multiply
if (buttonText == "*") {
multiplyButtonPressed = true;
initialNumber = displayNumber;
currentNumber = 0;
decimalPointPressed = false;
}
// if button pressed is reciprocal
if (buttonText == "1/x") {
// call reciprocal method
display.setText(reciprocal(displayNumber));
currentNumber = 0;
decimalPointPressed = false;
}
// if button is pressed to change a sign
if (buttonText == "+/-") {
// call changesign meyhod to change the sign
display.setText(changeSign(displayNumber));
currentNumber = 0;
decimalPointPressed = false;
}
// factorial button
if (buttonText == "x!") {
display.setText(factorial(displayNumber));
currentNumber = 0;
decimalPointPressed = false;
}
// power button
if (buttonText == "x^n") {
powerButtonPressed = true;
initialNumber = displayNumber;
currentNumber = 0;
decimalPointPressed = false;
}
// now for scientific buttons
if (buttonText == "Sin") {
if (degrees.getState())
 display.setText(Double.toString(Math.sin(Math.PI * displayNumber/180)));
else {
 display.setText(Double.toString(Math.sin(displayNumber)));
 currentNumber = 0;
 decimalPointPressed = false;
}
}
if (buttonText == "Cos") {
if (degrees.getState())
 display.setText(Double.toString(Math.cos(Math.PI * displayNumber/180)));
else{
 display.setText(Double.toString(Math.cos(displayNumber)));
 currentNumber = 0;
 decimalPointPressed = false;
}
}
if (buttonText == "Tan") {
if (degrees.getState())
 display.setText(Double.toString(Math.tan(Math.PI * displayNumber/180)));
else {
 display.setText(Double.toString(Math.tan(displayNumber)));
 currentNumber = 0;
 decimalPointPressed = false;
}
}
if (buttonText == "aSin") {
if (degrees.getState())
 display.setText(Double.toString(Math.asin(displayNumber)* 180/Math.PI ));
else {
 display.setText(Double.toString(Math.asin(displayNumber)));
 currentNumber = 0;
 decimalPointPressed = false;
}
}
if (buttonText == "aCos") {
if (degrees.getState())
 display.setText(Double.toString(Math.acos(displayNumber)* 180/Math.PI ));
else {
 display.setText(Double.toString(Math.acos(displayNumber)));
 currentNumber = 0;
 decimalPointPressed = false;
}
}
if (buttonText == "aTan") {
if (degrees.getState())
 display.setText(Double.toString(Math.atan(displayNumber)* 180/Math.PI ));
else {
 display.setText(Double.toString(Math.atan(displayNumber)));
 currentNumber = 0;
 decimalPointPressed = false;
}
}
// this will convert the numbers displayed to degrees
if (buttonText == "todeg")
 display.setText(Double.toString(Math.toDegrees(displayNumber)));
// this will convert the numbers displayed to radians
if (buttonText == "torad")
 display.setText(Double.toString(Math.toRadians(displayNumber)));
if (buttonText == "Pi") {
display.setText(Double.toString(Math.PI));
currentNumber =0;
decimalPointPressed = false;
}
if (buttonText == "Round")
roundButtonPressed = true;
// check if decimal point is pressed
if (buttonText == ".") {
String displayedNumber = display.getText();
boolean decimalPointFound = false;
int i;
decimalPointPressed = true;
// check for decimal point
for (i =0; i < displayedNumber.length(); ++i) {
 if(displayedNumber.charAt(i) == '.') {
decimalPointFound = true;
continue;
 }
}
if (!decimalPointFound)
 decimalPlaces = 1;
}
if(buttonText == "CA"){
 // set all buttons to false
 resetAllButtons();
 display.setText("0");
 currentNumber = 0;
}
if (buttonText == "CE") {
display.setText("0");
currentNumber = 0;
decimalPointPressed = false;
}
if (buttonText == "E") {
display.setText(Double.toString(Math.E));
currentNumber = 0;
decimalPointPressed = false;
}
// the main action
if (buttonText == "=") {
currentNumber = 0;
// if add button is pressed
if(addButtonPressed)
 display.setText(Double.toString(initialNumber + displayNumber));
// if subtract button is pressed
if(subtractButtonPressed)
 display.setText(Double.toString(initialNumber - displayNumber));
// if divide button is pressed
if (divideButtonPressed) {
// check if the divisor is zero
if(displayNumber == 0) {
 MessageBox mb = new MessageBox ( fr, "Error ", true, "Cannot divide by zero.");
 mb.show();
}
else
 display.setText(Double.toString(initialNumber/displayNumber));
}
// if multiply button is pressed
if(multiplyButtonPressed)
display.setText(Double.toString(initialNumber * displayNumber));
// if power button is pressed
if (powerButtonPressed)
display.setText(power(initialNumber, displayNumber));
// set all the buttons to false
resetAllButtons();
}
if (buttonText == "Arial Regular") {
for (int i =0; i < 10; ++i)
 key[i].setFont(new Font("Arial", Font.PLAIN, 12));
}
if (buttonText == "Arial Bold") {
for (int i =0; i < 10; ++i)
 key[i].setFont(new Font("Arial", Font.BOLD, 12));
}
if (buttonText == "Author") {
 MessageBox mb = new MessageBox ( fr, "Calculator ver 1.0 ", true, "Author: Arrowzflame.");
 mb.show();
}
 } // end of action events
 public void itemStateChanged(ItemEvent ie) {
if (ie.getItem() == "Basic") {
 basic.setState(true);
 scientific.setState(false);
 fr.setTitle("PF basic Calc");
 fr.setSize(200,270);
 // check if the scientific keys are visible. if true hide them
 if (scientificKey[0].isVisible()) {
for (int i=0; i < 8; ++i)
 scientificKey[i].setVisible(false);
 radians.setVisible(false);
 degrees.setVisible(false);
 }
}
if (ie.getItem() == "Scientific") {
 basic.setState(false);
 scientific.setState(true);
 fr.setTitle("PF Scientific Calc");
 fr.setSize(270,270);
 // check if the scientific keys are visible. if true display them
 if (!scientificKey[0].isVisible()) {
for (int i=0; i < 10; ++i)
 scientificKey[i].setVisible(true);
 radians.setVisible(true);
 degrees.setVisible(true);
 }
}
 } // end of itemState
 // this method will reset all the buttonPressed property to false
 public void resetAllButtons() {
addButtonPressed = false;
subtractButtonPressed = false;
multiplyButtonPressed = false;
divideButtonPressed = false;
decimalPointPressed = false;
powerButtonPressed = false;
roundButtonPressed = false;
 }
 public String factorial(double num) {
int theNum = (int)num;
if (theNum < 1) {
 MessageBox mb = new MessageBox (fr, "Facorial Error", true,
"Cannot find the factorial of numbers less than 1.");
 mb.show();
 return ("0");
}
else {
 for (int i=(theNum -1); i > 1; --i)
theNum *= i;
return Integer.toString(theNum);
}
 }
 public String reciprocal(double num) {
if (num ==0) {
 MessageBox mb = new MessageBox(fr,"Reciprocal Error", true,
 "Cannot find the reciprocal of 0");
 mb.show();
}
else
 num = 1/num;
 return Double.toString(num);
 }
 public String power (double base, double index) {
return Double.toString(Math.pow(base, index));
 }
 public String changeSign(double num) {
return Double.toString(-num);
 }
}
class MessageBox extends Dialog implements ActionListener {
Button ok;
MessageBox(Frame f, String title, boolean mode, String message) {
 super(f, title, mode);
 Panel centrePanel = new Panel();
 Label lbl = new Label(message);
 centrePanel.add(lbl);
 add(centrePanel, "Center");
 Panel southPanel = new Panel();
 ok = new Button ("OK");
 ok.addActionListener(this);
 southPanel.add(ok);
 add(southPanel, "South");
 pack();
 addWindowListener (new WindowAdapter() {
public void windowClosing (WindowEvent we) {
 //exit
}
 });
}
public void actionPerformed(ActionEvent ae) {
 dispose();
}
}