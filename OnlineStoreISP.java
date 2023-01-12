/**
 * Name: Julia Zhu
 * Date: Nov. 11, 2019
 * Description: Allows user to choose between the status as a store manager or customer, then one is able to perform a series of actions under the selected status.
 * The password for the store manager is "Rings".
 */

import java.io.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.*;

public class OnlineStoreISP extends JFrame implements ActionListener {
    static Random randGen = new Random();
    static JFrame topFrame;

    //variables and arrays declaration and initialization
    static int customerNum = 0;
    static int productNum = 5;
    static double salesTax = 0.13;
    static String[] productName = {"Small hoop earrings with star charm", "X diamond ring", "Moonstone pendant necklace", "BFF bracelets", "Simple heart ankle bracelet", "", "", "", "", ""};
    static double[] price = {24.99, 29.99, 32.99, 22.99, 20.99, 0, 0, 0, 0, 0, 0};
    static int[] stock = {20, 23, 21, 13, 18, 0, 0, 0, 0, 0};
    static String gift[] = {"key chain", "bookmark", "stress ball","squishy","box of slime"};
    static int[] sold = new int[10];
    static String password = "Rings"; //set password to Rings
    static int[] cart = new int[10];
    static int flag = 0; //initialize flag which would be later used in random gift generation
    FlowLayout flow; //declare flow layout as it will be commonly used

    //declare objects for the welcome panel
    JPanel welcomePan;
    static boolean welcomePanDrawed=false;
    JLabel welcomeLabel,instructionLabel,noticeLabel, imageLabel;
    JButton customerBtn, managerBtn, exitBtn;
    ImageIcon image;
    //declare objects for the password panel
    JPanel passwordPan;
    JTextField passwordText;
    JButton enterBtn,canclBtn;
    //declare objects for the manager menu
    JPanel managerPan=null;
    JButton addProductBtn, updateInventoryBtn, displayInventoryBtn, salesUpdateBtn, changePasswordBtn, backToMainBtn;
    JButton returnToManagerBtn;

    //declare objects for add product panel
    JPanel addProductPan;
    JLabel welcomeInstructionLabel,managerInstructionLabel,customerInstructionLabel,instructionLabel2, instructionLabel3;
    JTextField newProductText, newPriceText, newStockText;
    //declare objects for update inventory panel
    JPanel updateInventoryPan;
    JTextField changeStockText;
    JTextField stockItemText;
    GridLayout grid3=new GridLayout(4,2);
    //declare objects for display inventory panel
    JTextArea displayInventoryTextArea;
    JPanel displayInventoryPan;
    JLabel productLabel;
    //declare objects for sales update panel
    JPanel salesUpdatePan;
    //declare objects for change password panel
    JPanel changePasswordPan;
    JTextField oldPasswordText, newPasswordText;
    //declare objects for creating customer menu
    JPanel customerPan=null;
    JPanel displayProductsPan;
    JTextArea displayProducts;
    JButton viewCartBtn, checkoutBtn, pickForMeBtn, randomGiftBtn;
    JButton returnToCustomerBtn=new JButton("Return to Customer Menu");//create global button to return to customer menu
    //declare objects for view cart panel
    JPanel viewCartPan;
    JTextArea viewCartTextArea;
    JButton addToCartBtn, removeFromCartBtn;
    //declare objects to create add to cart panel
    JPanel addToCartPan;
    JTextField itemNumText, itemQuantityText;
    //declare objects to create remove from cart panel
    JPanel removeFromCartPan;
    JButton allBtn;
    //declare objects to create checkout panel
    String[] locationArray={"Alberta","British Columbia","Manitoba","New-Brunswick","Newfoundland and Labrador","Northwest Territories","Nova Scotia","Ontario","PEI","Québec", "Saskatchewan","Yukon"};
    JComboBox locationBox=new JComboBox(locationArray);
    JPanel checkoutPan;
    JLabel noticeLabel2, noticeLabel3;
    JButton digitalReceiptBtn;
    //declare objects for creating pick for me panel
    JPanel pickForMePan;
    //declare objects for creating random gift panel
    JPanel randomGiftPan;
    //declare objects for creating exit panel
    JPanel exitPan;



    public OnlineStoreISP() { //frame constructor
        setBackground(new Color(176,224,230));
        flow = new FlowLayout(); //declare flow layout as it will be commonly used
        setLayout(flow);
        setSize(1500, 800);

        createLabels();
        createPanels();
        showWelcomePanel();

        setVisible(true);
        topFrame = this;

    }

    //method to clear the panel
    public static void clearPanel(JPanel pan){
        pan.removeAll();
        pan.repaint();
        pan.revalidate();
    }

    public void createLabels(){
        instructionLabel = new JLabel();

        instructionLabel2=new JLabel();
        instructionLabel3=new JLabel();
        noticeLabel=new JLabel();
        noticeLabel2=new JLabel();
        noticeLabel3=new JLabel();

        returnToManagerBtn=new JButton("Return to Manager Menu");
        returnToManagerBtn.addActionListener(this);
    }

    public void createPanels(){ //method to create panels
        exitPan=new JPanel();
    }


    public void showWelcomePanel(){
        if (welcomePanDrawed){
            welcomePan.setVisible(true);
        }
        else {
            welcomePan = new JPanel();
            welcomePan.setVisible(true);
            this.add(welcomePan);

            welcomeLabel = new JLabel("Welcome to Julia's Jewelry Boutique!"); //create objects
            welcomePan.add(welcomeLabel); //add objects to panel

            image = new ImageIcon(getClass().getResource("jewelry.jpg"));
            imageLabel = new JLabel(image);
            welcomePan.add(imageLabel);

            welcomeInstructionLabel = new JLabel("Please select one of the following options:");
            welcomePan.add(welcomeInstructionLabel);

            managerBtn = new JButton("Manager");
            managerBtn.addActionListener(this);

            customerBtn = new JButton("Customer");
            customerBtn.addActionListener(this);

            exitBtn = new JButton("Exit Store");
            exitBtn.addActionListener(this); //implement action listeners

            managerBtn.setPreferredSize(new Dimension(100, 50));
            customerBtn.setPreferredSize(new Dimension(100, 50));
            exitBtn.setPreferredSize(new Dimension(100, 50));

            welcomePan.add(managerBtn);
            welcomePan.add(noticeLabel);
            welcomePan.add(customerBtn);
            welcomePan.add(exitBtn);

            welcomeLabel.setFont(new Font("Brush Script MT", Font.BOLD, 35)); //set colour, size, and font of welcomeLabel
            welcomeLabel.setPreferredSize(new Dimension(1000, 1000));
            welcomeLabel.setForeground(new Color(153, 50, 204));
            welcomeLabel.setBackground(new Color(100, 20, 70));
            welcomePan.setBackground(new Color(176, 224, 230));
            BoxLayout box = new BoxLayout(welcomePan, BoxLayout.Y_AXIS);
            welcomePan.setLayout(box);

            welcomePanDrawed = true;
        }
    }

    public void createPasswordGUI(){ //create the password panel
        passwordPan=new JPanel();
        //clearPanel(passwordPan);//clear panel
        JLabel passInstructionLabel=new JLabel("Enter your password: (Enter 'return' to return to the main menu)"); //prompt for password
        passwordText=new JTextField(10);
        enterBtn=new JButton("Enter password");
        JButton canclBtn=new JButton("Cancel");
        canclBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                passwordPan.setVisible(false);
                showWelcomePanel();
            }
        });
        noticeLabel.setText("");

        enterBtn.addActionListener(this); //add action listener
        canclBtn.addActionListener(this); //add action listener

        passwordPan.add(passInstructionLabel); //add objects to panel
        passwordPan.add(passwordText);
        passwordPan.add(enterBtn);
        passwordPan.add(canclBtn);
        passwordPan.add(noticeLabel);
        passwordPan.setLayout(flow);
        add(passwordPan);//add password panel to frame

        passwordPan.setVisible(true);
        welcomePan.setVisible(false);
    }

    public void createManagerMenu() { //create the manager menu
        if (managerPan !=null){
            managerPan.setVisible(true);
            return;
        }
        managerPan = new JPanel(new GridLayout(0,1,5,10));

        managerInstructionLabel = new JLabel("Choose an option from the following: "); //prompt user to choose from a list of actions they can execute
        addProductBtn = new JButton("Add a New Product"); //create objects
        updateInventoryBtn = new JButton("Update Inventory");
        displayInventoryBtn = new JButton("Display Inventory");
        salesUpdateBtn = new JButton("Sales Update");
        changePasswordBtn = new JButton("Change Password");

        JButton backToMainBtn = new JButton("Return to Main Menu");
        managerPan.add(backToMainBtn);
        backToMainBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                managerPan.setVisible(false);
                showWelcomePanel();
            }
        });


        managerPan.add(managerInstructionLabel); //add objects to panel
        managerPan.add(new JLabel(""));
        managerPan.add(new JLabel(""));
        managerPan.add(addProductBtn);
        managerPan.add(new JLabel(""));
        managerPan.add(updateInventoryBtn);
        managerPan.add(new JLabel(""));
        managerPan.add(displayInventoryBtn);
        managerPan.add(new JLabel(""));
        managerPan.add(salesUpdateBtn);
        managerPan.add(new JLabel(""));
        managerPan.add(changePasswordBtn);
        managerPan.add(new JLabel(""));
        managerPan.add(backToMainBtn);
        managerPan.add(new JLabel(""));
        managerPan.add(noticeLabel);

        addProductBtn.addActionListener(this); //add action listener to buttons
        updateInventoryBtn.addActionListener(this);
        displayInventoryBtn.addActionListener(this);
        salesUpdateBtn.addActionListener(this);
        changePasswordBtn.addActionListener(this);

        //managerPan.setLayout(flow); //set layout
        add(managerPan); //add manager panel to frame
        managerPan.setVisible(true); //make panel visible
    }

    //GridLayout grid=new GridLayout(5,2);

    public void addProduct() { //create panel for adding an product to store
        //clearPanel(addProductPan);//clear panel
        addProductPan=new JPanel();

        JLabel nameLabel=new JLabel("Enter the name of the new product: ");
        JLabel priceLabel=new JLabel("Enter the price of the new product: ");
        JLabel stockLabel=new JLabel("Enter the stock of the new product: ");
        newProductText = new JTextField();
        newPriceText = new JTextField();
        newStockText = new JTextField();

        enterBtn = new JButton("Add Product");
        enterBtn.addActionListener(this);

        noticeLabel.setText("");
        addProductPan.add(nameLabel);
        addProductPan.add(newProductText);
        addProductPan.add(priceLabel);
        addProductPan.add(newPriceText);
        addProductPan.add(stockLabel);
        addProductPan.add(newStockText);
        addProductPan.add(enterBtn);

        //addProductPan.add(returnToManagerBtn);
        JButton rtn = new JButton("Return");
        addProductPan.add(rtn);
        rtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                addProductPan.setVisible(false);
                managerPan.setVisible(true);
            }
        });

        //addProductPan.add(returnToManagerBtn);

        addProductPan.add(noticeLabel);
        addProductPan.add(noticeLabel2);
        addProductPan.setLayout(new GridLayout(5,2));

        add(addProductPan);

        managerPan.setVisible(false);
        addProductPan.setVisible(true);

    }

    public void updateInventory() {// create panel to modify stock
        updateInventoryPan=new JPanel();
        JLabel oldStock = new JLabel("Enter the item's stock you would like to modify: "); //prompt the user for the item's stock to modify
        JLabel newStock= new JLabel("Enter the new stock:");
        changeStockText=new JTextField();
        stockItemText=new JTextField();
        enterBtn=new JButton("Change Stock");
        enterBtn.addActionListener(this); //add action listener to buttons
        returnToManagerBtn.addActionListener(this);
        updateInventoryPan.add(oldStock);
        updateInventoryPan.add(stockItemText);
        updateInventoryPan.add(newStock);
        updateInventoryPan.add(changeStockText);
        updateInventoryPan.add(enterBtn);

        JButton rtn = new JButton("Return");
        updateInventoryPan.add(rtn);
        rtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                updateInventoryPan.setVisible(false);
                managerPan.setVisible(true);
            }
        });

        updateInventoryPan.add(noticeLabel);
        updateInventoryPan.setLayout(grid3);
        add(updateInventoryPan);
        updateInventoryPan.setVisible(true);
        managerPan.setVisible(false);
    }

    public void displayInventory(){ //create panel for displaying inventory
        displayInventoryPan=new JPanel();
        displayInventoryPan.setLayout(new BoxLayout(displayInventoryPan, BoxLayout.Y_AXIS));

        productLabel=new JLabel("Products");
        displayInventoryPan.add(productLabel); //add objects to panel

        productLabel.setFont(new Font("Brush Script MT", Font.PLAIN,20));
        String displayText= "";
        for (int i=0; i<productNum; i++) { //for loop to put all products' name, price, and stock into a string
            displayText += (i + 1) + ". " + productName[i] + "  ";
            displayText += "$ " + price[i] + "  ";
            displayText += stock[i] + "\n";
        }
        displayInventoryTextArea=new JTextArea(displayText);
        displayInventoryPan.add(displayInventoryTextArea);

        JButton rtn = new JButton("Return");
        //rtn.setPreferredSize(new Dimension(640, 50));
        displayInventoryPan.add(rtn);
        rtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                displayInventoryPan.setVisible(false);
                managerPan.setVisible(true);
            }
        });
        //displayInventoryPan.add(returnToManagerBtn);

        displayInventoryPan.setVisible(true); //set display inventory panel to visible
        add(displayInventoryPan);
        managerPan.setVisible(false); //set manager menu to invisible
    }

    public void salesUpdate(double[] price, int[] sold) { //create panel for viewing sales update
        salesUpdatePan = new JPanel();
        salesUpdatePan.setLayout(new BoxLayout(salesUpdatePan, BoxLayout.Y_AXIS));
        double total = 0; //declare and initiate accumulator total
        for (int i = 0; i < sold.length; i++) { //for each element in sold
            total += price[i] * sold[i]; //add the value of the price of the product multiplied by the number of that product sold to total
        }
        total = Math.round(total * 100.0) / 100.0; //round the total to two decimal places
        noticeLabel.setText("The total revenue you have made is $" + total+".");
        salesUpdatePan.add(noticeLabel); //add objects to panel

        JButton rtn = new JButton("Return");
        salesUpdatePan.add(rtn);
        rtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                salesUpdatePan.setVisible(false);
                managerPan.setVisible(true);
            }
        });

        add(salesUpdatePan); //add panel to frame
        salesUpdatePan.setVisible(true); //set panel to visible
        managerPan.setVisible(false);
    }

    public void changePassword() { //create panel to change the password of store manager
        changePasswordPan=new JPanel();
        JLabel oldPass=new JLabel("Enter your old password: ");
        JLabel newPass=new JLabel("Enter your new password: ");//prompt user to enter the new password they want to change to and store in password
        oldPasswordText=new JTextField();
        newPasswordText=new JTextField();
        enterBtn=new JButton("Confirm");
        enterBtn.addActionListener(this);

        changePasswordPan.add(oldPass); //add objects to panel
        changePasswordPan.add(oldPasswordText);
        changePasswordPan.add(newPass);
        changePasswordPan.add(newPasswordText);
        changePasswordPan.add(enterBtn);
        //changePasswordPan.add(returnToManagerBtn);
        JButton rtn = new JButton("Return");
        changePasswordPan.add(rtn);
        rtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                changePasswordPan.setVisible(false);
                managerPan.setVisible(true);
            }
        });
        GridLayout grid2=new GridLayout(0,2);
        changePasswordPan.setLayout(grid2);
        add(changePasswordPan); //add panel to frame
        changePasswordPan.setVisible(true); //set change password panel to visible
        managerPan.setVisible(false);
    }

    public void createCustomerMenu(){ //create customer menu
        if (customerPan !=null){
            welcomePan.setVisible(false);
            customerPan.setVisible(true);
            return;
        }
        customerPan = new JPanel(new GridLayout(0,2,25,10));
        JPanel cmdPan = new JPanel(new GridLayout(0,1));

        customerInstructionLabel=new JLabel("Choose an option from the following: "); //prompt user to choose from a list of actions they can execute
        cmdPan.add(customerInstructionLabel); //add objects to panel

        viewCartBtn=new JButton("View Cart"); //create buttons
        viewCartBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                viewCart();
            }
        });


        checkoutBtn=new JButton("Checkout");
        pickForMeBtn=new JButton("Pick for Me");
        randomGiftBtn=new JButton("Random Gift");

        //viewCartBtn.addActionListener(this); //add action listeners to buttons

        checkoutBtn.addActionListener(this);
        pickForMeBtn.addActionListener(this);
        randomGiftBtn.addActionListener(this);

        //backToMainBtn.addActionListener(this);
        JButton backToMainBtn = new JButton("Return to Main Menu");
        backToMainBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                customerPan.setVisible(false);
                showWelcomePanel();
            }
        });

        cmdPan.add(viewCartBtn);
        cmdPan.add(checkoutBtn);
        cmdPan.add(pickForMeBtn);
        cmdPan.add(randomGiftBtn);
        cmdPan.add(backToMainBtn);
        //////////////////////////////////////////////////
        displayProductsPan = new JPanel();
        cart=new int[cart.length]; //reset cart
        String displayText="Products \n"; //create string to store all product names and prices
        for (int i=0; i<productNum; i++){ //for loop to add all product names and prices to display text string
            if(stock[i]<=0){
                displayText+="(out of stock)";
            }
            displayText+=(i+1)+". "+productName[i]+"  ";
            displayText+="$ "+price[i]+"  \n";
        }
        displayProducts=new JTextArea(displayText); //add text to text area
        displayProductsPan.add(displayProducts);

        ////////////////////////////////////////////////

        customerPan.add(cmdPan);
        customerPan.add(displayProductsPan);
        add(customerPan); //add manager panel to frame

        welcomePan.setVisible(false);
        customerPan.setVisible(true);
    }

    public void viewCart(){ //create panel to view items added to shopping cart
        viewCartPan = new JPanel(new GridLayout(0,1,0,10));
        String displayCart="";
        for (int i=0; i<cart.length; i++){ //for loop to add the name and price of products in the shopping cart to a string
            if (cart[i]!=0) {
                displayCart += productName[i]+"  ";
                displayCart+=cart[i]+"\n";
            }
        }
        viewCartTextArea=new JTextArea(displayCart); //set text of text area to string
        addToCartBtn=new JButton("Add Product to Cart"); //create buttons
        removeFromCartBtn=new JButton("Remove Product from Cart");
        addToCartBtn.addActionListener(this); //add action listeners to buttons
        removeFromCartBtn.addActionListener(this);
        //returnToCustomerBtn.addActionListener(this);
        JButton returnToCustomerBtn = new JButton("Return to Customer Menu");
        returnToCustomerBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                viewCartPan.setVisible(false);
                customerPan.setVisible(true);
            }
        });

        viewCartPan.add(viewCartTextArea); //add objects to panel
        viewCartPan.add(addToCartBtn);
        viewCartPan.add(removeFromCartBtn);
        viewCartPan.add(returnToCustomerBtn);

        add(viewCartPan);//add panel to frame
        viewCartPan.setVisible(true);
        customerPan.setVisible(false);

    }

    public void addToCart() { // create panel that adds the item the user wants to purchase to his/her cart
        //clearPanel(addToCartPan);
        addToCartPan = new JPanel();
        instructionLabel.setText("Enter the number of the product you would like to purchase: (ie. 1. Small hoop earrings, 2. X diamond ring, etc.)"); //prompt user to enter the number of the product they want to add to their cart
        instructionLabel2.setText("How many of that item would you like to purchase? "); //prompt user to enter the quantity of that product they want to add to their cart
        itemNumText=new JTextField(10);
        itemQuantityText=new JTextField(10);
        enterBtn=new JButton("Confirm Add to Cart");
        noticeLabel.setText("");
        addToCartPan.add(instructionLabel); //add objects to panel
        addToCartPan.add(itemNumText);
        addToCartPan.add(instructionLabel2);
        addToCartPan.add(itemQuantityText);
        addToCartPan.add(enterBtn);
        addToCartPan.add(noticeLabel);

        JButton rtn = new JButton("Return to Customer Menu");
        addToCartPan.add(rtn);
        rtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                addToCartPan.setVisible(false);
                customerPan.setVisible(true);
            }
        });
        add(addToCartPan); //add panel to frame
        addToCartPan.setVisible(true); //set add to cart panel to visible
        viewCartPan.setVisible(false);
    }

    public void removeFromCart() { // create panel that removes items from the customer's cart
        removeFromCartPan = new JPanel();
        instructionLabel.setText("Enter the number of the product you would like to remove from your cart: (ie. 1. Small hoop earrings, 2. X diamond ring, etc.)"); //prompt user to enter the number of the product they want to add to their cart
        instructionLabel2.setText("How many of that item would you like to remove from your cart? "); //prompt user to enter the quantity of that product they want to remove from their cart
        itemNumText=new JTextField(10);
        itemQuantityText=new JTextField(10);
        allBtn=new JButton("All");
        enterBtn=new JButton("Confirm Remove from Cart");
        noticeLabel.setText("");
        removeFromCartPan.add(instructionLabel); //add objects to panel
        removeFromCartPan.add(itemNumText);
        removeFromCartPan.add(instructionLabel2);
        removeFromCartPan.add(itemQuantityText);
        removeFromCartPan.add(allBtn);
        removeFromCartPan.add(enterBtn);
        removeFromCartPan.add(noticeLabel);
        //removeFromCartPan.add(returnToCustomerBtn);
        JButton rtn = new JButton("Return to Customer Menu");
        removeFromCartPan.add(rtn);
        rtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                removeFromCartPan.setVisible(false);
                customerPan.setVisible(true);
            }
        });
        add(removeFromCartPan); //add panel to frame
        removeFromCartPan.setVisible(true); //set add to cart panel to visible
        viewCartPan.setVisible(false);
    }

    public  void checkout(int[] cart, int[] stock, int[] sold, double[] price, double salesTax, String[] productName){ //create panel that calculates and prints the final cost of all items in the shopping cart
        checkoutPan = new JPanel(new GridLayout(0,1,0,10));
        digitalReceiptBtn=new JButton("View Digital Receipt");
        digitalReceiptBtn.addActionListener(this); //add action listener to button
        double total = 0; //initiate accumulator total
        for (int i = 0; i < cart.length; i++) { //for each item in cart
            stock[i] -= cart[i]; //subtract the quantity of the item in cart from the stock of that item
            sold[i] += cart[i]; //add the quantity of items for current product to sold
            total += cart[i] * price[i]; //add the quantity multiplied by the price of the current product to total
            }
        locationBox.setSelectedIndex(8); //set default index of combo box to ontario
        locationBox.addActionListener(this); //add action listener to combo box
        noticeLabel.setText("Select the province/territory of where you are making this purchase from:");
        noticeLabel2.setText("Sales tax rate: "+salesTax*100+"%");
        noticeLabel3.setText("The total is $" + total+Math.round(total * salesTax * 100.0)/100.0); //display final cost
        checkoutPan.add(noticeLabel); //add objects to panel
        checkoutPan.add(noticeLabel2);
        checkoutPan.add(locationBox);
        checkoutPan.add(noticeLabel3);
        checkoutPan.add(digitalReceiptBtn);
        //checkoutPan.add(returnToCustomerBtn);
        JButton rtn = new JButton("Return to Customer Menu");
        checkoutPan.add(rtn);
        rtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                checkoutPan.setVisible(false);
                customerPan.setVisible(true);
            }
        });

        add(checkoutPan); //add panel to frame
        checkoutPan.setVisible(true);
        customerPan.setVisible(false);
    }

    public void pickForMe(int productNum, String[] productName) { //create panel that randomizes an item when user can not decide what to pick
        pickForMePan = new JPanel(new GridLayout(0,1,0,10));
        int chosen = randGen.nextInt(productNum); //generate a random integer from 0 to the number of products
        noticeLabel.setText("You should choose " + (chosen + 1) + ". " + productName[chosen]); //print the number generated + 1 as java is zero-indexed
        pickForMePan.add(noticeLabel); //add objects to panel
        //pickForMePan.add(returnToCustomerBtn);
        JButton rtn = new JButton("Return to Customer Menu");
        pickForMePan.add(rtn);
        rtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                pickForMePan.setVisible(false);
                customerPan.setVisible(true);
            }
        });
        add(pickForMePan); //add panel to frame
        pickForMePan.setVisible(true);
        customerPan.setVisible(false);
    }

    public void randomGift(String[] gift) { //creates panel that randomizes a gift
        randomGiftPan = new JPanel(new GridLayout(0,1,0,10));
        int random = randGen.nextInt(5); //randomly generating a number from 0-4
        noticeLabel.setText("You got a " + gift[random]+".\n"); //output the corresponding element in gift
        randomGiftPan.add(noticeLabel); //add objects to panel
        //randomGiftPan.add(returnToCustomerBtn);
        JButton rtn = new JButton("Return to Customer Menu");
        randomGiftPan.add(rtn);
        rtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                randomGiftPan.setVisible(false);
                customerPan.setVisible(true);
            }
        });
        add(randomGiftPan); //add panel to frame
        randomGiftPan.setVisible(true);
        customerPan.setVisible(false);
    }

    public void exitStore() { //creates panel to exit the program
        exitPan = new JPanel();
        JLabel instructionLabel=new JLabel("Are you sure you want to exit the program?"); //prompt the user for confirmation
        exitPan.add(instructionLabel); //add buttons to panel

        JButton yesBtn=new JButton("Yes"); //create buttons
        yesBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                dispose(); //stop program
            }
        });
        JButton noBtn=new JButton("No");
        noBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                exitPan.setVisible(false);
                showWelcomePanel();
            }
        });

        exitPan.add(yesBtn);
        exitPan.add(noBtn);

        add(exitPan);//add panel to frame
        exitPan.setVisible(true);
        welcomePan.setVisible(false);
    }


    // ACTION LISTENER - This method runs when an event occurs Code in here only runs when a user interacts with a component that has an action listener attached to it
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand(); // find out the name of the component that was used
        if (command.equals("Manager")) { //when the manager button is clicked
            createPasswordGUI();
        }
        else if (command.equals("Customer")) { //when the customer button is clicked
            customerNum += 1; //increment accumulator for the number of customers by 1
            if (customerNum>10){ //if max customer number is reached
                noticeLabel.setText("Maximum number of customers reached.");
            }
            else { //if max customer number isn't reached
                createCustomerMenu(); //display the customer menu
            }
        }
        else if (command.equals("Enter password")){ //if enter password button is clicked
            if ((passwordText.getText()).equalsIgnoreCase("return")) { //return to main menu if user types "return"
                passwordPan.setVisible(false);
                showWelcomePanel();
            } else if (!(passwordText.getText()).equals(password)) { //if incorrect password is entered
                noticeLabel.setText("Incorrect password, please try again.");
            } else { //if the correct password is entered
                passwordPan.setVisible(false);
                createManagerMenu(); //display the manager menu
            }
        }

        else if (command.equals("Add a New Product")){ //if add a new product button is clicked
            if (productNum == 10) { //determine whether or not the maximum (10) number of products is reached
                noticeLabel.setText("Sorry, you have reached the maximum number of products.");
            } else {
                addProduct();
                productNum += 1;
            }
        }
        else if (command.equals("Add Product")){ //if add product button is clicked
            try {
                productNum+=1;
                productName[productNum] =newProductText.getText(); //add new product name to array
                price[productNum] = Integer.parseInt(newPriceText.getText()); //add new price to array, type casting it into int first
                int newStock = Integer.parseInt(newStockText.getText()); //store in the next available element in stock while checking for invalid inputs
                if (newStock < 0) {
                    newStock = 0;
                }
                stock[productNum] = newStock;
                noticeLabel.setText("New product: " + (productNum) + "." + productName[productNum] + "  Price: $" + price[productNum] + "  Stock: " + stock[productNum]);
            }
            catch(Exception e){ //if entered wrong data type
                noticeLabel.setText("Invalid input, please try again(Note: Price must be a Integer).");
            }
        }
        else if (command.equals("Update Inventory")){ //if update inventory button is clicked
            updateInventory();
        }
        else if(command.equals("Change Stock")){ //if change stock button is clicked
            try {
                if (Integer.parseInt(changeStockText.getText()) < 0) {
                    noticeLabel.setText("Please enter a value greater than 0 or the stock will be set to 0.");
                    stock[Integer.parseInt(stockItemText.getText()) - 1] = 0;
                } else {
                    stock[Integer.parseInt(stockItemText.getText()) - 1] = Integer.parseInt(changeStockText.getText()); //collect user input for new stock and store it
                }
                noticeLabel2.setText("The stock of " + productName[Integer.parseInt(stockItemText.getText()) - 1] + " is now " + stock[Integer.parseInt(stockItemText.getText()) - 1]);
            }
            catch (Exception e){
                noticeLabel.setText("Invalid input, please try again.");
            }
        }
        else if (command.equals("Display Inventory")) { //if display invenotry button is clicked
            displayInventory();
        }
        else if (command.equals("Sales Update")){
            salesUpdate(price, sold);
        }
        else if (command.equals("Change Password")){
            changePassword();
        }
        else if (command.equals("Confirm")){
            if ((oldPasswordText.getText()).equals(password)) { //if the old password entered is correct
                password = newPasswordText.getText();
                if (password.equalsIgnoreCase("return")) { //if the user enters "return" as their new password
                    noticeLabel.setText("Sorry, you can not change your password to 'return', please try again."); //tell the user that the password can not be changed to "return" and prompt the user to try again
                } else {
                    noticeLabel.setText("Your new password is " + password + ".");
                }
            } else {
                noticeLabel.setText("Incorrect password.");
            }
        }
        else if (command.equals("Return to Manager Menu")){ //if return to manager menu button is clicked

            createManagerMenu();//set manager menu to visible
        }
        else if (command.equals("Return to Main Menu")){ //if return to main menu button is clicked
            showWelcomePanel();
        }

        else if(command.equals("Add Product to Cart")){ //if add product to cart button is clicked
            addToCart();
        }
        else if(command.equals("Remove Products from Cart")){ //if remove product from cart button is clicked
            removeFromCart();
        }
        else if(command.equals("Confirm Add to Cart")){ //if confirm add to cart button is clicked
            int itemNum=Integer.parseInt(itemNumText.getText()) - 1;
            int itemQuantity=Integer.parseInt(itemQuantityText.getText());
            if(stock[itemNum]<itemQuantity) {
                noticeLabel.setText("Sorry, the number you entered exceeds our current stock, the maximum stock ("+stock[itemNum]+") will be added to your cart.");
                cart[itemNum] += stock[itemNum];
                stock[itemNum]=0;
            }else {
                cart[itemNum] += itemQuantity;
                noticeLabel.setText("Your item(s) have been added to your cart.");
            }
        }
        else if(command.equals("Confirm Remove from Cart")){ //if confirm remove from cart button is clicked
            cart[Integer.parseInt(itemNumText.getText()) - 1] -= Integer.parseInt(itemQuantityText.getText());
            if(cart[Integer.parseInt(itemNumText.getText()) - 1]<0){ //if removed number of the item is more than number of the item in cart, set number of item in cart to 0
                cart[Integer.parseInt(itemNumText.getText()) - 1]=0;
            }
            noticeLabel.setText("Your item(s) have been removed from your cart.");
        }
        else if (command.equals("Checkout")) { //if checkout button is clicked
            checkout(cart, stock, sold, price, salesTax, productName);
        }
        else if(event.getSource()==locationBox){
            JComboBox cb=(JComboBox)event.getSource();
            String location=(String)cb.getSelectedItem();
            switch(location){
                case "Alberta": salesTax=0.05;
                    break;
                case "British Columbia": salesTax=0.012;
                    break;
                case "Manitoba": salesTax=0.13;
                    break;
                case "New-Brunswick": salesTax=0.15;
                    break;
                case "Newfoundland and Labrador": salesTax=0.15;
                    break;
                case "Northwest Territories": salesTax=0.05;
                    break;
                case "Nova Scotia": salesTax=0.15;
                    break;
                case "Nunavut": salesTax=0.05;
                    break;
                case "Ontario": salesTax=0.13;
                    break;
                case "PEI": salesTax=0.15;
                    break;
                case "Québec": salesTax=0.14975;
                    break;
                case "Sasketchewan": salesTax=0.11;
                    break;
                case "Yukon": salesTax=0.05;
                    break;
                default: salesTax=0.13;
            }
        }
        else if(command.equals("View Digital Receipt")){ //creates a digital receipt
            File file=new File("digitalreceipt.txt"); //create file to write to and printWriter
            PrintWriter output = null;
            try {
                output = new PrintWriter(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            double total=0;
            for (int i=0; i<productNum; i++){
                if (cart[i]!=0){
                    if(cart[i]>=stock[i]) {
                        output.println(productName[i] + "   "+cart[i]+" x "+price[i]+" = "+cart[i]*price[i]); //write product name, number of items in cart*price of item, and total cost of items to file
                        total+=cart[i]*price[i];
                    }
                    else{ //if number of item is more than stock
                        output.println(productName[i] + "   "+stock[i]+" x "+price[i]+" = "+stock[i]*price[i]);
                        total+=stock[i]*price[i];
                    }
                }
            }
            output.println("Sales tax:" +salesTax*100+"%"); //print sales tax to file
            output.println("Total: "+total); //print total cost to file
            output.close(); //close printWriter to save all changes
        }
        else if (command.equals("Pick for Me")) { //randomizes an item when user can not decide what to pick
            pickForMe(productNum, productName);
        }
        else if (command.equals("Random Gift")) { //randomizes a small gift
            if (flag == 0) { //if the user has not obtained their gift yet -- each user can only receive one gift
                randomGift(gift);
                flag = 1; //set flag to one, indicating they have already obtained their gift
            } else { //if the user has already received a gift
                noticeLabel.setText("You have already obtained your gift \n");
            }
        }
        else if(command.equals("Return to Customer Menu")){
            createCustomerMenu();
        }
        else if (command.equals("Exit Store")) { //return to main menu
            exitStore();
        }
    }

    public static void main(String[] args) { //main method
        OnlineStoreISP frame = new OnlineStoreISP(); //create frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when x button is clicked, program will stop
        //frame.setVisible(true);// set frame visibility to true
    }
}
