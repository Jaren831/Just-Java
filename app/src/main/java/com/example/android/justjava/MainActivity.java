
package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    /**
     * Global variables
     */
    int quantity = 2;
    int price = 5;
    String name = "Kaptain Kunal";

    /**
     * Increment triggered on plus button. Decrement on minus button. Increase/decrease quantity.
     */
    public void increment(View view) {
        quantity += 1;
        displayQuantity(quantity);
        displayPrice(calculateTotal(quantity, price));

    }
    public void decrement(View view) {
        quantity -= 1;
        displayQuantity(quantity);
        displayPrice(calculateTotal(quantity, price));
    }

    private int calculateTotal(int quantity, int price) {

        return quantity * price;
    }

    /**
     * Triggers thank you message.
     */

    public void submitOrder(View view) {
        /**
         * Checks if user wants whipped cream.
         */
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped);
        boolean checkWhipped = whippedCreamCheckBox.isChecked();

        /**
         * Checks if user wants chocolate.
         */
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate);
        boolean checkChocolate = chocolateCheckBox.isChecked();

        /**
         * Checks for user's name.
         */
        EditText name = ((EditText) findViewById(R.id.nameBox));
        String nameText = name.getText().toString();





        TextView messageTextView = (TextView) findViewById(R.id.price_text_view);
        messageTextView.setText(createMessage(nameText, checkWhipped, checkChocolate, quantity, calculateTotal(price, quantity), getString(R.string.message_checkout)));
    }
    private String createMessage(String name, boolean checkWhipped, boolean checkChocolate, int quantity, int total, String message) {
        return "Name: " + name + "\n" + "Add whipped Cream? " + checkWhipped + "\n" + "Add Chocolate? " + checkChocolate +
        "\n" + "Quantity: " + quantity + "\n" + "Total: $" + total + "\n" + message;
    }

    /**
     * This method displays the desired quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(number));
    }
    /**
     * This method displays the desired price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

}