package com.example.farzanaeventtrackingapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class EventGridActivity extends AppCompatActivity {

    private static final int SMS_PERMISSION_CODE = 100;

    private EditText editEventName;
    private EditText editEventDate;
    private EditText editEventTime;
    private EditText editDeleteId;
    private EditText editUpdateId;

    private Button buttonAddEvent;
    private Button buttonDeleteEvent;
    private Button buttonUpdateEvent;

    private TextView textEvents;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_grid);

        databaseHelper = new DatabaseHelper(this);

        editEventName = findViewById(R.id.editEventName);
        editEventDate = findViewById(R.id.editEventDate);
        editEventTime = findViewById(R.id.editEventTime);
        editDeleteId = findViewById(R.id.editDeleteId);
        editUpdateId = findViewById(R.id.editUpdateId);

        buttonAddEvent = findViewById(R.id.buttonAddEvent);
        buttonDeleteEvent = findViewById(R.id.buttonDeleteEvent);
        buttonUpdateEvent = findViewById(R.id.buttonUpdateEvent);

        textEvents = findViewById(R.id.textEvents);

        requestSmsPermission();
        displayEvents();

        buttonAddEvent.setOnClickListener(v -> {
            String eventName = editEventName.getText().toString().trim();
            String eventDate = editEventDate.getText().toString().trim();
            String eventTime = editEventTime.getText().toString().trim();

            if (eventName.isEmpty() || eventDate.isEmpty() || eventTime.isEmpty()) {
                Toast.makeText(this, "Enter all event details", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean added = databaseHelper.addEvent(eventName, eventDate, eventTime);

            if (added) {
                Toast.makeText(this, "Event Added", Toast.LENGTH_SHORT).show();

                editEventName.setText("");
                editEventDate.setText("");
                editEventTime.setText("");

                displayEvents();
                sendSmsNotification(eventName, eventDate, eventTime);
            } else {
                Toast.makeText(this, "Failed to Add Event", Toast.LENGTH_SHORT).show();
            }
        });

        buttonDeleteEvent.setOnClickListener(v -> {
            String idText = editDeleteId.getText().toString().trim();

            if (idText.isEmpty()) {
                Toast.makeText(this, "Enter Event ID to delete", Toast.LENGTH_SHORT).show();
                return;
            }

            int id = Integer.parseInt(idText);
            boolean deleted = databaseHelper.deleteEvent(id);

            if (deleted) {
                Toast.makeText(this, "Event Deleted", Toast.LENGTH_SHORT).show();

                editDeleteId.setText("");
                displayEvents();
            } else {
                Toast.makeText(this, "Event Not Found", Toast.LENGTH_SHORT).show();
            }
        });

        buttonUpdateEvent.setOnClickListener(v -> {
            String idText = editUpdateId.getText().toString().trim();
            String eventName = editEventName.getText().toString().trim();
            String eventDate = editEventDate.getText().toString().trim();
            String eventTime = editEventTime.getText().toString().trim();

            if (idText.isEmpty() || eventName.isEmpty() || eventDate.isEmpty() || eventTime.isEmpty()) {
                Toast.makeText(this, "Enter ID and event details", Toast.LENGTH_SHORT).show();
                return;
            }

            int id = Integer.parseInt(idText);

            boolean updated = databaseHelper.updateEvent(id, eventName, eventDate, eventTime);

            if (updated) {
                Toast.makeText(this, "Event Updated", Toast.LENGTH_SHORT).show();

                editUpdateId.setText("");
                editEventName.setText("");
                editEventDate.setText("");
                editEventTime.setText("");

                displayEvents();
            } else {
                Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestSmsPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.SEND_SMS},
                    SMS_PERMISSION_CODE
            );
        }
    }

    private void sendSmsNotification(String eventName, String eventDate, String eventTime) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED) {

            SmsManager smsManager = SmsManager.getDefault();

            smsManager.sendTextMessage(
                    "5551234567",
                    null,
                    "Reminder: " + eventName + " on " + eventDate + " at " + eventTime,
                    null,
                    null
            );

            Toast.makeText(this, "SMS notification sent", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "SMS permission not granted", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayEvents() {
        Cursor cursor = databaseHelper.getAllEvents();

        if (cursor.getCount() == 0) {
            textEvents.setText("No events saved yet.");
            cursor.close();
            return;
        }

        StringBuilder builder = new StringBuilder();

        while (cursor.moveToNext()) {
            builder.append("ID: ").append(cursor.getInt(0)).append("\n");
            builder.append("Event: ").append(cursor.getString(1)).append("\n");
            builder.append("Date: ").append(cursor.getString(2)).append("\n");
            builder.append("Time: ").append(cursor.getString(3)).append("\n\n");
        }

        textEvents.setText(builder.toString());
        cursor.close();
    }
}