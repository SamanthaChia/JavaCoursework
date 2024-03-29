// Name: Samantha Chia Eileen 
// Student Number: 190428594

//Data entry used to test
// 1          - King cross
// Samantha Chia   - name
// 123         - price
// 4         - units
// n          - no more item or service
//            - Should print least 118.0 and no more than 590.4 for the min and max amount to enter
// 40         - deposit, should reject
// 800        - deposit, should reject 
// 300        - deposit, should accept
// testing method        - payment method, should reject
//  credit card      - payment method

// Display
// SHOP LOCATIONS:
// 1) King's Cross
// 2) Victoria
// 3) Waterloo
// 4) Euston
// 5) Liverpool Street
// 6) London Bridge
// 7) Paddington
// 8) Marylebone
// Enter The number that corresponds to your shop: 1
// Enter your first name: Samantha Chia
// Enter Item or service user is being charged for: This item is for a and an the of with this
// Enter the unit price (without VAT) of This Item Is For a and an the of with This: 123
// Enter the number of This Item Is For a and an the of with This: 4
// Enter another item or service? Yes/No: n
// Enter amount paid as a deposit by the customer (must be at least 118 and no more than 590.4: 40
// Deposit is too small
// Enter amount paid as a deposit by the customer (must be at least 118 and no more than 590.4: 800
// Deposit is too large
// Enter amount paid as a deposit by the customer (must be at least 118 and no more than 590.4: 300
// Enter deposit payment method: testing method
// Payment type not recognised
// Please enter one of the following ,cash,credit card,debit card,bitcoin
// Enter deposit payment method:    cash

// ****************************************
// * FixAllPhones - repairs you can trust *
// ****************************************

// Receipt - 13/JAN/2020::0756+0000(GMT)

// FixAll - King's Cross

// VAT No: 123456789

// Served by: Samantha chia

// ---------------------------------------------
// Your order:

// 4 x This Item Is For a and an the of with This @ £123.00 each           £492.00


// Subtotal                                £492.00
// Tax (VAT @ 20%)                         £98.40

// TOTAL : 4 items                         £590.40

// PAID (cash) :                   £300.00

// TO PAY :                                £290.40

// ---------------------------------------------


// Exchange and returns policy - http://www.FixAllPhones.co.uk/returns

// All repairs guaranteed for 18 months
// Repair T&Cs - http://www.FixAllPhones.co.uk/terms


// Rate our Service
// https//uk.trustpilot.com/review/www.FixAllPhones.co.uk


// Get 20% off your order when you refer a friend - see our web page for details
// http://www.FixAllPhones.co.uk/phonefriend

// Follow us on FaceBook and Twitter

import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TimeZone;

public class ReceiptGenerator{
    private static final String RETURNS_POLICY_URL = "http://www.FixAllPhones.co.uk/returns";
    private static final String TERMS_AND_CONDITIONS_URL = "http://www.FixAllPhones.co.uk/terms";
    private static final String SERVICE_REVIEW_URL = "https//uk.trustpilot.com/review/www.FixAllPhones.co.uk";
    private static final String REFERRAL_URL = "http://www.FixAllPhones.co.uk/phonefriend";
    private static final String SOCIAL_MEDIA = "FaceBook and Twitter";
    private static final String COMPANY_SLOGAN = "FixAllPhones - repairs you can trust";
    private static final int VAT_NUMBER = 123456789;
    //static final variables are constants, they cannot be changed by methods once assigned. 
    //By convention they are written in all caps,
    //with words separated by the underscore for readability.

    private static final String[] LOCATIONS = {
            "King's Cross",
            "Victoria",
            "Waterloo",
            "Euston",
            "Liverpool Street",
            "London Bridge",
            "Paddington",
            "Marylebone"
    };
    
	private static final String[] PAYMENT_TYPES = {
			"cash",
			"credit card",
			"debit card",
			"bitcoin"
	};
    
    private static final String[] TEMPLATE = {
            "%n****************************************%n* %s *%n****************************************%n%n", // + COMPANY_SLOGAN
            "Receipt - %s%n%n", // + receipt reference no
            "FixAll - %s%n%n", // + shop location
            "VAT No: %d%n%n", // + vat no
            "Served by: %s%n%n", // + staff member's first name (TODO and initial of surname?)
            "---------------------------------------------%nYour order:%n%n",
            "%s%n%n", // + costs breakdown
            "Subtotal\t\t\t\t£%.2f%n", // + subtotal (TODO: make it a task to make the receipts capable of being in euros? [ie make the £/€ in the receipt configurable] necessary because the Kings Cross branch is seeing lots of European business because of being next to St Pancras International)
            "Tax (VAT @ 20%%)\t\t\t\t£%.2f%n%n", // + tax (TODO: should tax rate also be configurable? VAT may change post Brexit)
            "TOTAL : %d item%s\t\t\t\t£%.2f%n%n", // + number of items, s or no s (single or plural), total amount payable
            "PAID (%s) :\t\t\t£%.2f%n%n", // + payment type (credit card/debit card/cash/bitcoin/other crypto), amount paid
            "TO PAY :\t\t\t\t£%.2f%n%n", // + amount left to pay
            "---------------------------------------------%n%n%n", //no additional info
            "Exchange and returns policy - %s%n%n", // + returns policy url
            "All repairs guaranteed for 18 months%nRepair T&Cs - %s%n%n%n", // + terms & conditions url
            "Rate our Service%n%s%n%n%n", // + 3rd party review site url
            "Get 20%% off your order when you refer a friend - see our web page for details%n%s%n%n", // + referral url
            "Follow us on %s%n" // + our social media channels
    };

    private static String dateAndTime;
    private static String shopLocation;
    private static String staffName;
    private static String itemisedCosts;
    private static int numberOfItemsPurchased;
    private static double subtotal;
    private static double vat;
    private static double total; 
    private static double outstandingBalance;
    private static double paid;
    private static String paymentMethod;
    private static Scanner in;
    private static String itemDescription;
    private static double unitPrice;
    private static int numberOfUnits;
    private static boolean keepGoing;

    public static void create() {
        in = new Scanner(System.in);
        
        dateAndTime = getDateAndTime();
        shopLocation = getShopLocationFromUser();
        staffName = getFromUser("your first name");
        staffName = capitalise(staffName);

        itemisedCosts = "";
        numberOfItemsPurchased = 0;
        subtotal = 0;
        
        keepGoing = true;
        //loop so that the user can enter as many items as they need to
        while (keepGoing) {

            retrieveItemNamePriceQuantity(); // Method to retrieve Item/Service Name, Price and Unit Number.
            calculateTotal(); // Method to calculate the total to be printed to the receipt.
            //TODO make above three statements a method

            continueAddingService();
            //TODO make asking the user if they wish to add more items into a method
            
        }//end of while loop
        
		vat = getVat(subtotal);
        //calculate total with vat
        total = addVat(subtotal);
        
        paid = validateDepositInput("amount paid as a deposit by the customer"); 
										 //TODO write a new method to get the deposit from the user. 
        								 //enforce that the method will (1) give the user the minimum and 
        								 //the maximum amount of the deposit (minimum is 20% of the total, 
        								 //and maximum is the total); (2) The method will enforce that deposit 
        								 //amounts that are too low or too high will be rejected, 
        								 //and the user asked to enter another amount. The minimum deposit, 
        								 //once calculated, should be rounded to the nearest int 
        								 //(normal rounding applies), before being shown to the user.
        
        paymentMethod = getPaymentMethod();
        // paymentMethod = getFromUser("deposit payment method"); //TODO validate this input. See PAYMENT_TYPES

        //clean up
        in.close();
        
        calculateOutstandingBalance(); 
        
        //print the formatted receipt
        printReceipt();
    }

    private static String getDateAndTime() {
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("Europe/London"));
        return String.format("%1$te/%<Tb/%<tY::%<tH%<tM%<tz(%<tZ)", now);
    }

    private static String getShopLocationFromUser() {
        System.out.println("SHOP LOCATIONS:");
        for (int i = 0; i < LOCATIONS.length; i++) {
            System.out.println((i+1) + ") " + LOCATIONS[i]);
        }
        int choice = getIntFromUser("The number that corresponds to your shop");
        return LOCATIONS[choice-1];
    }
    
    private static String capitalise(String s) {
		if (s == null) return("");
		s = s.trim();
		if (s.length() < 2) return s.toUpperCase();
		return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    }

    
    private static void retrieveItemNamePriceQuantity(){
        itemDescription = getFromUser("Item or service user is being charged for");
        itemDescription = capitalizeFirstLetter(itemDescription);
        itemDescription = removeCapsFromArticles(itemDescription);
        unitPrice = getDoubleFromUser("the unit price (without VAT) of " + itemDescription);
        numberOfUnits = getIntFromUser("the number of " + itemDescription);
    }

    private static void calculateTotal(){
        itemisedCosts += addItemisedCostToReceipt(itemDescription, unitPrice, numberOfUnits);
        subtotal += (unitPrice * numberOfUnits);
        numberOfItemsPurchased += numberOfUnits;
    }

    private static void continueAddingService(){
        String more = getFromUser("another item or service? Yes/No");
            if (more.trim().toLowerCase().startsWith("n")) {
                keepGoing = false;
            }
    }

    private static double validateDepositInput(String prompt){
        boolean keepGoing = true;
        double minimumVal = 0;
        double i = 0;
        int previewMinimumVal = 0;
        while(keepGoing){
            minimumVal = total * 0.20;
            previewMinimumVal = (int) Math.round(minimumVal);
            i = getDoubleFromUser("amount paid as a deposit by the customer (must be at least " + previewMinimumVal + " and no more than "+ total);
            if (i < minimumVal){
                System.out.println("Deposit is too small");
            }
            else if (i > total){
                System.out.println("Deposit is too large");
            }
            else {
                keepGoing = false;
            }
        }
        return i;
    }

    private static String getPaymentMethod(){
        boolean validPaymentMethod;
        boolean continueWithLoop = true;
        while(continueWithLoop){
            validPaymentMethod = validatePaymentMethod();
            if(!validPaymentMethod){
                System.out.println("Payment type not recognised");
                System.out.print("Please enter one of the following ");
                for(String paymentType: PAYMENT_TYPES){
                    System.out.print("," +paymentType);
                }
                System.out.println("");
            } 
            else{
                continueWithLoop = false;
            }
        }
        return paymentMethod;
    }

    private static boolean validatePaymentMethod(){ 
        boolean validPaymentMethod = false;
        paymentMethod = getFromUser("deposit payment method").trim().replaceAll(" +"," ");
        paymentMethod = paymentMethod.toLowerCase();
        for(String s : PAYMENT_TYPES){
            if(s.equals(paymentMethod)){
                validPaymentMethod = true;
            } 
        }
        return validPaymentMethod;
    }
    
    private static String capitalizeFirstLetter(String itemDescription){ //capitalize first letter of every word.
         if (itemDescription == null || itemDescription.isEmpty()) {
            return itemDescription;
        }
        itemDescription = itemDescription.trim().replaceAll(" +"," "); // remove in between extra space
        StringBuilder sb = new StringBuilder(); //String Builders are to create mutable string objects.
        boolean convertCharacter = true;
        for (char letter : itemDescription.toCharArray()) // loop through the itemDescription
        {
            if (Character.isSpaceChar(letter)) //  check if it's a space character
            {
                convertCharacter = true;
            }
            else if (convertCharacter) // check if convertCharacter should be converted to a Title Case
            {
                letter = Character.toTitleCase(letter);
                convertCharacter = false;
            } 
            else 
            {
                letter = Character.toLowerCase(letter); // change other characters to a lower case
            }
            sb.append(letter);
        }
        String upperCaseDescription = sb.toString();
        return upperCaseDescription;
    }

    private static String removeCapsFromArticles(String itemDescription){ // remove Uppercase from a ,an, the, and, of, with
        String[] arrOfDescription = itemDescription.split(" ");
        for( int i = 0;i < arrOfDescription.length;i++){
            if(arrOfDescription[i].equals("A")){
                arrOfDescription[i] = arrOfDescription[i].toLowerCase();
            }
            else if(arrOfDescription[i].equals("An")){
                arrOfDescription[i] = arrOfDescription[i].toLowerCase();
            }
            else if(arrOfDescription[i].equals("And")){
                arrOfDescription[i] = arrOfDescription[i].toLowerCase();
            }
            else if(arrOfDescription[i].equals("The")){
                arrOfDescription[i] = arrOfDescription[i].toLowerCase();
            }
            else if(arrOfDescription[i].equals("Of") ){
                arrOfDescription[i] = arrOfDescription[i].toLowerCase();
            }
            else if(arrOfDescription[i].equals("With")){
                arrOfDescription[i] = arrOfDescription[i].toLowerCase();
            }
        }
        String endDescriptionArrays = String.join(" ", arrOfDescription);
        return endDescriptionArrays;
    }


    
    private static String addItemisedCostToReceipt(String itemDescription, double unitPrice, int numberOfUnits) {
        //format: numberOfUnits x itemDescription @ £unitPrice each: £(unitPrice*NumberOfUnits)
        return String.format("%d x %s @ £%.2f each\t\t£%.2f%n", numberOfUnits, itemDescription, unitPrice, unitPrice * numberOfUnits);
    }

    private static void calculateOutstandingBalance() {
        outstandingBalance = total - paid;
    }

    private static String getFromUser(String prompt) {
        System.out.print("Enter " + prompt + ": ");
        return in.nextLine();
    }

    private static int getIntFromUser(String prompt) {
        boolean keepGoing = true;
        int i = 0;
        while (keepGoing) {
            System.out.print("Enter " + prompt + ": ");
            i = in.nextInt();

            in.nextLine(); //read the rest of the line that had the int.
            //without this, running getIntFromUser() followed by getFromUser()
            //would result in getFromUser() returning the rest of the line that the int was part of
            //(ie probably just a newline character)

            if (i < 1) { //make sure the number is more than 0
                System.out.println("Invalid number. Number must be greater than zero.");
            } else {
                keepGoing = false;
            }
        }
        return i;
    }

    private static double getDoubleFromUser(String prompt) {
        boolean keepGoing = true;
        double i = 0;

        while (keepGoing) {
            System.out.print(("Enter " + prompt + ": "));
            i = in.nextDouble();

            in.nextLine(); //read the rest of the line (probably just a newline character)

            if (i <= 0) {
                System.out.println("Invalid number. Number must be greater than zero.");
            } else {
                keepGoing = false;
            }
        }

        return i;
    }

    private static void printReceipt() {
        String s = "";

        for (int i = 0; i < TEMPLATE.length; i++) {
            s += String.format(TEMPLATE[i], getTemplateData(i));
        }

        /*
        TODO need to fix the tab spacing in the itemised costs
        and the other bits between the dashes (the costs breakdown etc)
         */
        System.out.println(s);
    }

    private static Object[] getTemplateData(int line) {
        if (line == 0) return new Object[]{ COMPANY_SLOGAN };
        if (line == 1) return new Object[]{ dateAndTime };
        if (line == 2) return new Object[]{ shopLocation };
        if (line == 3) return new Object[]{ VAT_NUMBER };
        if (line == 4) return new Object[]{ staffName };
        if (line == 6) return new Object[]{ itemisedCosts };
        if (line == 7) return new Object[]{ subtotal };
        if (line == 8) return new Object[]{ vat };
        if (line == 9) return new Object[]{ numberOfItemsPurchased, numberOfItemsPurchased > 1 ? "s" : "", total };
        if (line == 10) return new Object[]{ paymentMethod, paid };
        if (line == 11) return new Object[]{ outstandingBalance };
        if (line == 13) return new Object[]{ RETURNS_POLICY_URL };
        if (line == 14) return new Object[]{ TERMS_AND_CONDITIONS_URL };
        if (line == 15) return new Object[]{ SERVICE_REVIEW_URL };
        if (line == 16) return new Object[]{ REFERRAL_URL };
        if (line == 17) return new Object[]{ SOCIAL_MEDIA };
        else return new Object[]{ "" }; //any lines that don't need extra data
    }

    private static double addVat(double amount) {
        double vat = getVat(amount);
        return amount + vat;
    }
    
    private static double getVat(double amount) {
        return (amount*20.0)/100.0; //vat = 20%
    }

    public static void main(String[] args) {
        ReceiptGenerator.create();
    }
}

