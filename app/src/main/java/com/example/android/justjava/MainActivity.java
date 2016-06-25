
package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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


    /**
     * Increment triggered on plus button. Decrement on minus button. Increase/decrease quantity.
     */
    public void increment(View view) {
        quantity += 1;
        displayQuantity(quantity);
        displayPrice(price * quantity);

    }
    public void decrement(View view) {
        quantity -= 1;
        displayQuantity(quantity);
        displayPrice(price * quantity);
    }
    /**
     * Triggers thank you message.
     */

    public void submitOrder(View view) {
        displayMessage(getString(R.string.message_checkout));
    }

    /**
     * This method displays the desired quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(number));
    }
    /**
     * This method displays the message on the screen.
     */
    private void displayMessage(String message) {
        TextView messageTextView = (TextView) findViewById(R.id.message_text_view);
        messageTextView.setText(message);

    }
    /**
     * This method displays the desired price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

}