
package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
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
        displayPrice(calculateTotal(quantity, price));
        displayQuantity(quantity);
    }

    /**
     * Global variables. Prices. Defaults quantity on app start.
     */
    int quantity = 2;
    int price = 5;
    int whippedCream = 1;
    int chocolate = 2;

    /**
     * Increment called by plus button. Increase quantity.
     */
    public void increment(View view) {
        if (quantity <= 100) {
            quantity += 1;
            displayQuantity(quantity);
            displayPrice(calculateTotal(quantity, price));
        }
    }

    /**
     * Decrement called by minus button. Decreases quantity.
     */
    public void decrement(View view) {
        if (quantity >= 1) {
            quantity -= 1;
            displayQuantity(quantity);
            displayPrice(calculateTotal(quantity, price));
        }
    }

    /**
     * Recalculates and displays new price when checkboxes are checked/unchecked.
     */
    public void checkUpdate(View view) {
        displayPrice(calculateTotal(quantity, price));
    }

    /**
     * Determines total price from quantity, price, and whether checkboxes are checked.
     */

    private int calculateTotal(int quantity, int price) {
        CheckBox chocolateCheck = (CheckBox) findViewById(R.id.chocolate);
        CheckBox whippedCheck = (CheckBox) findViewById(R.id.whipped);
        if (chocolateCheck.isChecked()) {
            price += chocolate;
        }
        if (whippedCheck.isChecked()) {
            price += whippedCream;
        }
        return quantity * price;
    }

    /**
     * Called by submit order button.
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

        /**
         * Calls createMessage. Gives parameters for thank you message.
         */

        String message = createMessage(nameText, checkWhipped, checkChocolate, quantity,
                calculateTotal(quantity, price), getString(R.string.message_checkout));
//        TextView messageTextView = (TextView) findViewById(R.id.price_text_view);
//        messageTextView.setText(message);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps.
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + nameText);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * Called when submit order button pushed. Builds and displays thank you message.
     */

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