package com.example.ishita.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // This attribute stores the rates for the different coffee items in the order declared in the spinner.
    int[] rates = {25, 15, 10, 30};
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.ChooseProduct);
        spinner.setOnItemSelectedListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * This method is called when the order button is clicked.
     */
    public int submitOrder() {
        Spinner spinner = (Spinner) findViewById(R.id.ChooseProduct);
        return rates[spinner.getSelectedItemPosition()];
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This is the method that increases the quantity when the "+" button is pressed.
     */
    public void increment(View view) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        int value = Integer.parseInt((String) quantityTextView.getText());

        // This condition checks if the user has already selected the maximum number of coffees that can be ordered.

        if (value < 100) value++;
        else
            Toast.makeText(getApplicationContext(), "Ordering more than 100 coffees is not allowed.", Toast.LENGTH_SHORT).show();

        display(value);
    }

    /**
     * This is the method that decreases the quantity when the "-" button is pressed.
     */
    public void decrement(View view) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        int value = Integer.parseInt((String) quantityTextView.getText());

        // This condition checks if the user has selected the minimum number of coffees that can be ordered.
        if (value > 0) value--;
        else
            Toast.makeText(getApplicationContext(), "Order value cannot be less than zero.", Toast.LENGTH_SHORT).show();
        display(value);
    }

    /**
     * This method displays the rate of the item selected in a toast message.
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub
        // On selecting a spinner item
        Spinner spinner = (Spinner) findViewById(R.id.ChooseProduct);
        String product = spinner.getSelectedItem().toString();
        int pos = spinner.getSelectedItemPosition();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Price per " + product.toLowerCase() + ": " + rates[pos], Toast.LENGTH_SHORT).show();
    }

    /**
     * This method is executed when the user taps the "order" button.
     * This method then calculates the total price and sends the user to the "order summary" screen.
     *
     * @param view Starts this method when the "order" button is clicked.
     */
    public void placeOrder(View view) {
        Intent goIntent = new Intent(MainActivity.this, OrderSummaryActivity.class);
        Bundle extras = new Bundle();

        TextView qtyTextView = (TextView) findViewById(R.id.quantity_text_view);
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        EditText name = (EditText) findViewById(R.id.Name);
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whippedCream);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        Spinner spinner = (Spinner) findViewById(R.id.ChooseProduct);

        int price = submitOrder(); //gets the total price of the order
        int qty = Integer.parseInt((String) qtyTextView.getText()); //reads the number of coffees the user wants
        String product = spinner.getSelectedItem().toString(); //reads the type of coffee the user wants
        String nameValue = name.getText().toString(); //reads the name of the customer from the XML
        String Chocolate, WhippedCream; //to print whether the user selected any toppings

        //To get the state of the checkboxes
        boolean hasWhippedCream = whippedCream.isChecked();
        boolean hasChocolate = chocolate.isChecked();

        // These conditions check if the user has selected any toppings with their coffee.
        if (hasWhippedCream) {
            price++;
            WhippedCream = "\nWhipped Cream added: Yes";
        } else
            WhippedCream = "\nWhipped Cream added: No";

        if (hasChocolate) {
            price += 2;
            Chocolate = "\nChocolate added: Yes";
        } else
            Chocolate = "\nChocolate added: No";

        price = price * (Integer.parseInt((String) qtyTextView.getText()));
        priceTextView.setText("Total: $" + price);

        // To pass the parameters to the OrderSummaryActivity
        extras.putString("EXTRA_NAME", nameValue);
        extras.putString("EXTRA_QUANTITY", "" + qty);
        extras.putString("EXTRA_ITEM", product);
        extras.putString("EXTRA_WHIPPEDCREAM", WhippedCream);
        extras.putString("EXTRA_CHOCOLATE", Chocolate);
        extras.putString("EXTRA_PRICE", "" + price);
        goIntent.putExtras(extras);
        startActivity(goIntent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
        Toast.makeText(getApplicationContext(), "Please select something.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.ishita.justjava/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.ishita.justjava/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}