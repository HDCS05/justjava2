/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava2
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava2;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        if (gQuantity < 100) {
            gQuantity++;
        }
        else {
            /* "getApplicationContext()" or "this"  will work */
            Toast.makeText(getApplicationContext(),
                    "You can't order more than 100 coffees!",
                    Toast.LENGTH_SHORT).show();
            }
        updateScreen();
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (gQuantity > 1) {
            gQuantity--;
        }
        else {
            /* "getApplicationContext()" or "this"  will work */
            Toast.makeText(this,
                    "You can't order less than 1 coffee!",
                    Toast.LENGTH_SHORT).show();
        }
        updateScreen();
    }

    /**
     * This method updates the display.
     */
    private void updateScreen() {
        displayQuantity(gQuantity);
        displayPrice(gQuantity * gPrice);
//        displayMessage("---");
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

//        /* check if the whipped cream button is selected */
//        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
//        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
//
//        /* check if the chocolate button is selected */
//        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
//        boolean hasChocolate = chocolateCheckBox.isChecked();
//
//        /* calculate the prize */
//        int price = calculatePrice(hasWhippedCream, hasChocolate);
//
//        /* get the string from the name field */
//        EditText nameField = (EditText) findViewById(R.id.name_field);
//        String vOrderName = nameField.getText().toString();
//
//        /* create the string to display as message */
//        String priceMessage = createOrderSumary(
//                price,
//                hasWhippedCream,
//                hasChocolate,
//                vOrderName);
//
//        /* send the string to the method that handles the display */
//        displayMessage(priceMessage);

        /* check if the whipped cream button is selected */
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        /* check if the chocolate button is selected */
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        /* calculate the prize */
        int price = calculatePrice(hasWhippedCream, hasChocolate);

        /* get the string from the name field */
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String vOrderName = nameField.getText().toString();

        /* create the string to display as message */
        String priceMessage = createOrderSumary(
                price,
                hasWhippedCream,
                hasChocolate,
                vOrderName);

//        String vSubject = "Just Java order for: " + vOrderName;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(
                R.string.order_summary_email_subject, vOrderName));
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * Calculates the price of the order.
     * globalPrice is the price per cup ordered
     * quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(boolean addWhippedCream,
                               boolean addChocolate) {
        int basePrice = gPrice;
        if (addWhippedCream) {
            basePrice += 1;
        }
        if (addChocolate) {
            basePrice += 2;
        }
        return gQuantity * basePrice;
    }

    /**
     * Creates a Sumary of the order.
     */
    private String createOrderSumary(int finalPrice,
                                     boolean addWhippedCream,
                                     boolean addChocolate,
                                     String OrderName) {
        String orderMessage = getString(R.string.order_summary_name, OrderName);
        orderMessage += "\n" + getString(R.string.order_summary_whipped_cream, addWhippedCream);
        orderMessage += "\n" + getString(R.string.order_summary_chocolate, addChocolate);
        orderMessage += "\n" + getString(R.string.order_summary_quantity, gQuantity);
        orderMessage += "\n" + getString(R.string.order_summary_price,
                NumberFormat.getCurrencyInstance().format(finalPrice));
        orderMessage += "\n" + getString(R.string.thank_you) + "\n";
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

//    /**
//     * This method displays the given text on the screen.
//     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);

//        The next two lines I only used them to test some code left here for reference
//        View textView = findViewById(R.id.order_summary_text_view);
//        textView.setVisibility(View.GONE);

//    }

}
