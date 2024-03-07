package org.me.gcu.pizzaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RadioButton stuffedCrust, thinAndCrispy;
    private CheckBox mushrooms,sweetcorn, onions, peppers;
    private ToggleButton extraCheese;
    private Button submitButton;
    private EditText emailEntry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stuffedCrust = (RadioButton) findViewById(R.id.stuffedRadio);
        thinAndCrispy = (RadioButton) findViewById(R.id.thinRadio);
        mushrooms = (CheckBox) findViewById(R.id.MushroomCB);
        sweetcorn = (CheckBox) findViewById(R.id.SweetcornCB);
        onions = (CheckBox) findViewById(R.id.OnionCB);
        peppers = (CheckBox) findViewById(R.id.PeppersCB);
        extraCheese = (ToggleButton) findViewById(R.id.toggleCheese);
        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
        emailEntry = (EditText) findViewById(R.id.editTextTextEmailAddress);

    }

    @Override
    public void onClick(View v) {
        System.out.println("in on click");
        String deliveryEmail = emailEntry.getText().toString();
        if (deliveryEmail.equals(""))
        {
            Toast.makeText(this, "Please enter your delivery email address",
                    Toast.LENGTH_LONG).show();
            return;
        }
        else
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            //get current date time with Date()
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            String deliveryInfo = "Pizza Delivery for "+ deliveryEmail +"\nat " +dateFormat.format(date) +"\n";
            if (stuffedCrust.isChecked())
                deliveryInfo = deliveryInfo+"Stuffed Crust Pizza base\n";
            else
                deliveryInfo = deliveryInfo+"Thin and Crispy Pizza base\n";
            if (mushrooms.isChecked())
                deliveryInfo = deliveryInfo + "with Mushrooms\n";
            if (sweetcorn.isChecked())
                deliveryInfo = deliveryInfo + "with Sweetcorn\n";
            if (onions.isChecked())
                deliveryInfo = deliveryInfo + "with Onions\n";
            if (peppers.isChecked())
                deliveryInfo = deliveryInfo + "with Peppers\n";
            if (extraCheese.isChecked())
                deliveryInfo = deliveryInfo + "with Extra Cheese\n";
            System.out.println(deliveryInfo);

            //Toast.makeText(this,deliveryInfo,Toast.LENGTH_LONG).show();
            showConfirmDeliveryDialog(deliveryInfo);


    }
}

    private void showConfirmDeliveryDialog(String deliveryInfo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Pizza Order details");
        builder.setMessage(deliveryInfo);
        builder.setCancelable(false);
        builder.setPositiveButton("Confirm Order", confirmButtonListener);
        builder.setNegativeButton("Cancel", cancelButtonListener);
        AlertDialog alert = builder.create();
        alert.show();
    }

    private DialogInterface.OnClickListener confirmButtonListener = new DialogInterface.OnClickListener (){

        public void onClick(DialogInterface dialog, int id)
        {
            System.out.println("Button with id "+ id + " pressed");
            System.out.println("Order was confirmed");
            dialog.cancel();
            stuffedCrust.setChecked(true);
            thinAndCrispy.setChecked(false);
            mushrooms.setChecked(false);
            sweetcorn.setChecked(false);
            onions.setChecked(false);
            peppers.setChecked(false);
            extraCheese.setChecked(false);
            emailEntry.setText("");


        }

    };

    private DialogInterface.OnClickListener cancelButtonListener = new DialogInterface.OnClickListener ()
    {

        public void onClick(DialogInterface dialog, int id)
        {
            System.out.println("Button with id "+ id + " pressed");
            System.out.println("Order was cancelled and so clear all entries in the main screen");
            stuffedCrust.setChecked(true);
            thinAndCrispy.setChecked(false);
            mushrooms.setChecked(false);
            sweetcorn.setChecked(false);
            onions.setChecked(false);
            peppers.setChecked(false);
            extraCheese.setChecked(false);
            emailEntry.setText("");
            dialog.cancel();

        }
    };
    }