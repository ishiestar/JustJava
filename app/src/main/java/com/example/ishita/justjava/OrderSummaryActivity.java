package com.example.ishita.justjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OrderSummaryActivity extends Activity {

    Intent intent;
    Bundle extras;
    String name;
    String price;
    String quantity;
    String item;
    String chocolate;
    String whippedCream;
    String message;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordersummary);
        intent = getIntent();
        extras = intent.getExtras();
        name = extras.getString("EXTRA_NAME");
        price = extras.getString("EXTRA_PRICE");
        quantity = extras.getString("EXTRA_QUANTITY");
        item = extras.getString("EXTRA_ITEM");
        chocolate = extras.getString("EXTRA_CHOCOLATE");
        whippedCream = extras.getString("EXTRA_WHIPPEDCREAM");
        TextView nameTextView = (TextView) findViewById(R.id.message);
        message = "Name: " + name + "\n\nSelected Product: " + item + "\n\nQuantity: " + quantity + "\n\nToppings\n\n" + chocolate + "\n" + whippedCream + "\n\nTotal price: " + price;
        nameTextView.setText(message);
    }

    public void sendOrder(View view){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_SUBJECT, "Order Summary");
        i.putExtra(Intent.EXTRA_TEXT, message);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(OrderSummaryActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
