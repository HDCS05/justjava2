/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava2
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava2;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int gQuantity = 1;
    int gPrice = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateScreen();
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        gQuantity++;
        updateScreen();
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        gQuantity--;
        updateScreen();
    }

    /**
     * This method updates the display.
     */
    private void updateScreen() {
        displayQuantity(gQuantity);
        displayPrice(calculatePrice());
        displayMessage("---");
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        /* calculate the prize */
        int price = calculatePrice();

        /* check if the whipped cream button is selected */
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        /* check if the chocolate button is selected */
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        /* get the string from the name field */
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String vOrderName = nameField.getText().toString();

        /* create the string to display as message */
        String priceMessage = createOrderSumary(
                price,
                hasWhippedCream,
                hasChocolate,
                vOrderName);

        /* send the string to the method that handles the display */
        displayMessage(priceMessage);
    }

    /**
     * Calculates the price of the order.
     * globalPrice is the price per cup ordered
     * quantity is the number of cups of coffee ordered
     */
    private int calculatePrice() {
        return gQuantity * gPrice;
    }

    /**
     * Creates a Sumary of the order.
     */
    private String createOrderSumary(int finalPrice,
                                     boolean addWhippedCream,
                                     boolean addChocolate,
                                     String OrderName) {
        String orderMessage = "Name: " + OrderName;
        orderMessage += "\nAdd whipped cream? " + addWhippedCream;
        orderMessage += "\nAdd chocolate? " + addChocolate;
        orderMessage += "\nQuantity: " + gQuantity;
        orderMessage += "\nTotal: ";
        orderMessage += NumberFormat.getCurrencyInstance().format(finalPrice);
        orderMessage += "\nThank you!\n";
        return orderMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);

//        The next two lines I only used them to test some code left here for reference
//        View textView = findViewById(R.id.order_summary_text_view);
//        textView.setVisibility(View.GONE);

    }

}
