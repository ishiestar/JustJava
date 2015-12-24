package com.example.ishita.justjava;

import android.os.Bundle;
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
    public void submitOrder(View view) {
        //display(2);
        displayPrice(5);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    /*private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }*/

    /**
     * This is the method that increases the quantity when the "+" button is pressed.
     */
    public void increment(View view) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        int value=Integer.parseInt((String)quantityTextView.getText());
        value++;
        quantityTextView.setText("" + value);
    }

    public void decrement(View view) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        int value=Integer.parseInt((String)quantityTextView.getText());
        if(value>0) value--;
        quantityTextView.setText("" + value);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        number=number*(Integer.parseInt((String)quantityTextView.getText()));
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}