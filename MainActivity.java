package com.polytech.legue.tondeusebluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private final String DEVICE_ADDRESS = "00:0E:EA:CF:16:C5"; //MAC Address of Bluetooth Module
    private final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    private BluetoothDevice device;
    private BluetoothSocket socket;
    private OutputStream outputStream;

    Button forward_btn, forward_left_btn, forward_right_btn, reverse_btn, reverse_left_btn, reverse_right_btn, mode_pilote, mode_autonome, bluetooth_connect_btn, start_coupe_btn, stop_coupe_btn;

    String command; //string variable that will store value to be transmitted to the bluetooth module

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declaration of button variables
        forward_btn = (Button) findViewById(R.id.forward_btn);
        forward_left_btn = (Button) findViewById(R.id.forward_left_btn);
        forward_right_btn = (Button) findViewById(R.id.forward_right_btn);
        reverse_btn = (Button) findViewById(R.id.reverse_btn);
        mode_autonome = (Button) findViewById(R.id.mode_autonome);            ////////////////////////////CODER BOUTON/////////////////////////////////////
        mode_pilote = (Button) findViewById(R.id.mode_pilote);
        bluetooth_connect_btn = (Button) findViewById(R.id.bluetooth_connect_btn);
        start_coupe_btn = (Button) findViewById(R.id.start_coupe_btn);
        stop_coupe_btn = (Button) findViewById(R.id.stop_coupe_btn);

        //OnTouchListener code for the forward left button (button long press)
        forward_left_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    command = "l";

                    try {
                        outputStream.write(command.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    command = "s";
                    try {
                        outputStream.write(command.getBytes());
                    } catch (IOException e) {
                    }
                }
                return false;
            }
        });
        //OnTouchListener code for the forward button (button long press)
        forward_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) //MotionEvent.ACTION_DOWN is when you hold a button down
                {
                    command = "f";

                    try {
                        outputStream.write(command.getBytes()); //transmits the value of command to the bluetooth module
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) //MotionEvent.ACTION_UP is when you release a button
                {
                    command = "s";
                    try {
                        outputStream.write(command.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                return false;
            }

        });

        //OnTouchListener code for the reverse button (button long press)
        reverse_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    command = "b";

                    try {
                        outputStream.write(command.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    command = "s";
                    try {
                        outputStream.write(command.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });


        //OnTouchListener code for the forward right button (button long press)
        forward_right_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    command = "r";

                    try {
                        outputStream.write(command.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    command = "s";
                    try {
                        outputStream.write(command.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                return false;
            }
        });

        //Button that connects the device to the bluetooth module when pressed
        bluetooth_connect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (BTinit()) {
                    BTconnect();
                }

            }
        });

        //Button that changes the mode of the mower
        mode_pilote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String command1 = "p";

                try {
                    outputStream.write(command1.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        mode_autonome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String command1 = "a";
                String command2 = "d";
                try {
                    outputStream.write(command1.getBytes());
                    outputStream.write(command2.getBytes());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        mode_pilote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String command1 = "p";

                try {
                    outputStream.write(command1.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        start_coupe_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String command1 = "t";

                try {
                    outputStream.write(command1.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        stop_coupe_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String command1 = "c";

                try {
                    outputStream.write(command1.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    //Initializes bluetooth module
    public boolean BTinit() {
        boolean found = false;

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null) //Checks if the device supports bluetooth
        {
            Toast.makeText(getApplicationContext(), "Device doesn't support bluetooth", Toast.LENGTH_SHORT).show();
        }

        if (!bluetoothAdapter.isEnabled()) //Checks if bluetooth is enabled. If not, the program will ask permission from the user to enable it
        {
            Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableAdapter, 0);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();

        if (bondedDevices.isEmpty()) //Checks for paired bluetooth devices
        {
            Toast.makeText(getApplicationContext(), "Please pair the device first", Toast.LENGTH_SHORT).show();
        } else {
            for (BluetoothDevice iterator : bondedDevices) {
                if (iterator.getAddress().equals(DEVICE_ADDRESS)) {
                    device = iterator;
                    found = true;
                    break;
                }
            }
        }

        return found;
    }

    public boolean BTconnect() {
        boolean connected = true;

        try {
            socket = device.createRfcommSocketToServiceRecord(PORT_UUID); //Creates a socket to handle the outgoing connection
            socket.connect();

            Toast.makeText(getApplicationContext(),
                    "Connexion avec la tondeuse effectuée !", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            connected = false;
        }

        if (connected) {
            try {
                outputStream = socket.getOutputStream(); //gets the output stream of the socket
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return connected;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
