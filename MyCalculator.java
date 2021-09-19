import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.text.*;

public class MyCalculator extends JFrame implements ActionListener { // main class
   static JPanel buttonPanel,textFieldPanel; //declare 2 panels
   static JButton seven,eight,nine,plus,four,five,six,minus,
          one,two,three,multiply,zero,decimal,equal,divide,
          clearButton; //declare all your buttons
   static JTextField text; //declare your textfield 
   static JLabel warningLabel;//declare your warninglabel;
   public Operatorr opt; // an object of the other class
   static int decimalCount,decimalCount2; // to track num of decimals in first and second operands
   static String firstOperand = "",secondOperand = ""; // operands for both sides of an operator
      
   public void createGUI() { // how calculator should look
                  	
      this.setLayout(new BorderLayout()); // layout for the main frame
      
      textFieldPanel = new JPanel(); // panel for the text field area
      textFieldPanel.setLayout(new FlowLayout());
      
      text = new JTextField(15); // create a text field and add it to the textfield panel
      text.setFont(new Font("Castellar",Font.BOLD,16));
      text.setForeground(Color.DARK_GRAY);
      text.setEditable(false);
      textFieldPanel.add(text);
      
      clearButton = new JButton("Clear"); //create clear button and add to panel
      clearButton.setForeground(Color.blue);
      textFieldPanel.add(clearButton);
        
      warningLabel = new JLabel("Looking good so far."); //create label and add to panel
      warningLabel.setForeground(Color.GRAY);
      warningLabel.setFont(new Font("MonoSpaced", Font.BOLD, 12));
      textFieldPanel.add(warningLabel);
        
      buttonPanel = new JPanel(); //create a new panel with gridlayout
      buttonPanel.setLayout(new GridLayout(4,4,3,3));
   
      seven = new JButton("7"); //add all buttons to the panel
      eight = new JButton("8");
      nine = new JButton("9");
      plus = new JButton("+");
      four = new JButton("4");
      five = new JButton("5");
      six = new JButton("6");
      minus = new JButton("-");
      one = new JButton("1");
      two = new JButton("2");
      three = new JButton("3");
      multiply = new JButton("*");
      zero = new JButton("0");
      decimal = new JButton(".");
      equal = new JButton("=");
      divide = new JButton("/");
      buttonPanel.add(seven);
      buttonPanel.add(eight);
      buttonPanel.add(nine);
      buttonPanel.add(plus);
      buttonPanel.add(four);
      buttonPanel.add(five);
      buttonPanel.add(six);
      buttonPanel.add(minus);
      buttonPanel.add(one);
      buttonPanel.add(two);
      buttonPanel.add(three);
      buttonPanel.add(multiply);
      buttonPanel.add(zero);
      buttonPanel.add(decimal);
      buttonPanel.add(equal);
      buttonPanel.add(divide);
      
      Font font = new Font("Ariel", Font.BOLD, 20); // font for the buttons
      for (Component i : buttonPanel.getComponents()) {
         if (i instanceof JButton) {
            ((JButton)i).setFont(font);
            }
         }
               
        //add both panels to frame
      this.add(textFieldPanel,BorderLayout.SOUTH);
      this.add(buttonPanel,BorderLayout.CENTER);
      }
    
   public void addListeners() {
      seven.addActionListener(this); //add actionlistener to all number buttons
      eight.addActionListener(this);
      nine.addActionListener(this);
      four.addActionListener(this);
      five.addActionListener(this);
      six.addActionListener(this);
      one.addActionListener(this);
      two.addActionListener(this);
      three.addActionListener(this);
      zero.addActionListener(this);
      decimal.addActionListener(this);        
            	  
      plus.addActionListener(opt); //add a different actionlistener to the operators and clear button
      minus.addActionListener(opt);
      multiply.addActionListener(opt);
      divide.addActionListener(opt);
      clearButton.addActionListener(opt);
      equal.addActionListener(opt);          
      }
    
   public MyCalculator(String title) { //call the above methods to setup your calculator
      super(title); 
      opt = new Operatorr();
      createGUI();
      addListeners();
      }

   public void actionPerformed(ActionEvent e) { //You enter this method if a number was chosen
                                                		
   //clear the label	
   //Check a boolean flag to see if a number or an operator was chosen before
   //if operator then clear text otherwise append old number to new number
                                       		
      String input = e.getActionCommand(); // get the command as a string
      
      if(Operatorr.operator != "") { // if an operator has already been chosen
      
         if(input == ".") { // dont allow more than one decimal signs in a number
            ++decimalCount2; // keep track for the second operand
            
            if(secondOperand == "") { // when inputing the second operand, if a decimal is entered without a number before it,
               secondOperand = "0"; // to avoid error, give a zero before the decimal
               }
            }
            
         if(decimalCount2 > 1) { // if a second decimal in a number attempted
            warningLabel.setForeground(Color.RED);
            warningLabel.setText("One decimal per number!");
            --decimalCount2; // to keep the flow of program when more than one decimal inputed
            }
            
         else {
            warningLabel.setForeground(Color.GRAY);
            warningLabel.setText("Looking good so far.");
            secondOperand += input; // no decimals no problems lol
            }
         }
         
      else { //like the 2nd operand, do the same things for the 1st operand
      
         if(input == ".") {
            ++decimalCount;
            
            if(firstOperand == "") {
               firstOperand = "0";
               }
            }
            
         if(decimalCount > 1) {
            warningLabel.setForeground(Color.RED);
            warningLabel.setText("One decimal per number!");
            --decimalCount;
            }
            
         else {
            warningLabel.setForeground(Color.GRAY);
            warningLabel.setText("Looking good so far.");
            firstOperand += input;
            }
         }
         
      text.setText(firstOperand + " " + Operatorr.operator + " " + secondOperand); //now that everything is put together, might as well add it to the textfield
      Operatorr.chosen = false; //update the textbox and set the boolean flag for operator to false
      }
      
   public static void main(String[] args) { // setup the frame
      MyCalculator calc = new MyCalculator("My Calculator");
      calc.setDefaultCloseOperation(EXIT_ON_CLOSE);
      calc.setSize(500,400);
      calc.setVisible(true); 
      }
   }

class Operatorr extends JFrame implements ActionListener { // when an operator or the clear button is clicked        
   static boolean chosen = false; // check if an operator has been chosen before 
   static String operator = ""; //point to an operator. Process previous first
      
   void init() { // call when clear button is clicked
      chosen = false;  
      MyCalculator.warningLabel.setForeground(Color.GRAY);
      MyCalculator.warningLabel.setText("Looking good so far.");
      MyCalculator.firstOperand = operator = MyCalculator.secondOperand = "";
      MyCalculator.text.setText(MyCalculator.firstOperand + operator + MyCalculator.secondOperand);
      MyCalculator.decimalCount = 0;
      MyCalculator.decimalCount2 = 0;
      }
    
   public void actionPerformed(ActionEvent e) { //when an operator or clear button is clicked
      String input = e.getActionCommand(); // get the label of the button as a string
      
      if(input == "Clear") { // when the clear nafarak is clicked
         init();
         }
      
      else if(chosen) { //when an operator is clicked more than 2 times in a row
         MyCalculator.warningLabel.setForeground(Color.RED);
         MyCalculator.warningLabel.setText("Cant choose an opeator again!");
         }
         
      else if(MyCalculator.firstOperand == "" && operator == "" && MyCalculator.secondOperand == "") { //if an operand is clicked before anything else is entered
         MyCalculator.warningLabel.setForeground(Color.RED);
         MyCalculator.warningLabel.setText("Need an Operand first.");
         operator = "";
         }
         
      else if (input == "=") { //if = sign is entered
         double total; 
      
         if (operator == "+") { //if the operator entered before the current input was +
            total = (Double.parseDouble(MyCalculator.firstOperand) + Double.parseDouble(MyCalculator.secondOperand)); 
            }
            
         else if (operator == "-") { //if the operator entered before the current input was -
            total = (Double.parseDouble(MyCalculator.firstOperand) - Double.parseDouble(MyCalculator.secondOperand)); 
            }
            
         else if (operator == "/") { //if the operator entered before the current input was /
            total = (Double.parseDouble(MyCalculator.firstOperand) / Double.parseDouble(MyCalculator.secondOperand));
            } 
            
         else { //if the operator entered before the current input was *
            total = (Double.parseDouble(MyCalculator.firstOperand) * Double.parseDouble(MyCalculator.secondOperand));
            } 
       
         MyCalculator.text.setText(MyCalculator.firstOperand + " " + operator + " " + MyCalculator.secondOperand + " = " + total); // update the textfield
       
         MyCalculator.firstOperand = Double.toString(total); // store the total in the 1st operand so 2 numbers are dealt with at a time then move on to the next 
      
         operator = MyCalculator.secondOperand = ""; // clear the operator and the 2nd operand to get them ready for the next round
         } 
         
      else { // when an operator other than = is entered 
      	 
         if (operator == "" || MyCalculator.secondOperand == "") { //if is was no second operand yet or the operator is empty yet
            operator = input; 
            }
          
         else { // in case of other operators
            double total; 
            if (operator == "+") { 
               total = (Double.parseDouble(MyCalculator.firstOperand) + Double.parseDouble(MyCalculator.secondOperand)); 
               }
            else if (operator == "=") {
               total = (Double.parseDouble(MyCalculator.firstOperand) - Double.parseDouble(MyCalculator.secondOperand)); 
               }
            else if (operator == "/") {
               total = (Double.parseDouble(MyCalculator.firstOperand) / Double.parseDouble(MyCalculator.secondOperand)); 
               }
            else {
               total = (Double.parseDouble(MyCalculator.firstOperand) * Double.parseDouble(MyCalculator.secondOperand)); 
               }
          
            MyCalculator.firstOperand = Double.toString(total); // convert it to string, store the total in the first operand
         
            operator = input; //now its time to update the operator so it can act as previous for the next round
          
            MyCalculator.secondOperand = ""; // clear the second operand to get it ready for next round
            } 
      
         MyCalculator.text.setText(MyCalculator.firstOperand + " " + operator + " " + MyCalculator.secondOperand); //update the textfield
         }
   
      chosen = true;  //for the purpose of checking if an operand is entered more than once in a row
         
   	 //if clear was chosen then reset the calculator, the labels, textfield and flags (init function)
       //if operator is chosen, make sure it wasn't chosen before (check boolean flag)
       //based on the operator that was chosen before, do the proper calculations 
       //and update total
      //set the flag for operator having been chosen to true
      //point to operator to be processed next time to the operator chosen
      //otherwise
      //two operators were chosen back to back and it is illegal
     //come up with an error message in the label section
      }
   }
