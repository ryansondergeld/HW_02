//  _  _                              _    ___
// | || |___ _ __  _____ __ _____ _ _| |__|_  )
// | __ / _ \ '  \/ -_) V  V / _ \ '_| / / / /
// |_||_\___/_|_|_\___|\_/\_/\___/_| |_\_\/___|
//-----------------------------------------------------------------------------
// Homework 02
// CIS 183
// R.Sondergeld
// 2024-09-21
//-----------------------------------------------------------------------------
// This code is extremely disorganized and messy.
// Not a fan of Android Studio so far.
//

package com.example.hw_01;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{

    // Declare Variables Here
    TextView    jMainRedLabelTextView;
    TextView    jMainRedValueTextView;
    TextView    jMainGreenLabelTextView;
    TextView    jMainGreenValueTextView;
    TextView    jMainBlueLabelTextView;
    TextView    jMainBlueValueTextView;
    TextView    jMainHexLabelTextView;
    TextView    jMainHexValueTextView;


    SeekBar     jMainRedSeekBar;
    SeekBar     jMainGreenSeekBar;
    SeekBar     jMainBlueSeekBar;
    View        jMainBackgroundView;
    View        jMainRedView;
    View        jMainGreenView;
    View        jMainBlueView;
    ListView    jMainColorListView;

    HexColor    hexColor;

    // Array List
    ArrayList<HexColor> hexColors;
    ArrayList<TextView> textViews;

    HexColorListAdapter hexColorListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Map All Controls Here
        jMainRedSeekBar         = findViewById(R.id.vMainRedSeekBar);
        jMainGreenSeekBar       = findViewById(R.id.vMainGreenSeekBar);
        jMainBlueSeekBar        = findViewById(R.id.vMainBlueSeekBar);

        jMainRedLabelTextView   = findViewById(R.id.vMainRedLabelTextView);
        jMainRedValueTextView   = findViewById(R.id.vMainRedValueTextView);
        jMainGreenLabelTextView = findViewById(R.id.vMainGreenLabelTextView);
        jMainGreenValueTextView = findViewById(R.id.vMainGreenValueTextView);
        jMainBlueLabelTextView  = findViewById(R.id.vMainBlueLabelTextView);
        jMainBlueValueTextView  = findViewById(R.id.vMainBlueValueTextView);
        jMainHexLabelTextView   = findViewById(R.id.vMainHexLabelTextView);
        jMainHexValueTextView   = findViewById(R.id.vMainHexValueTextView);

        jMainBackgroundView     = findViewById(R.id.vMainBackgroundView);
        jMainRedView            = findViewById(R.id.vMainRedView);
        jMainGreenView          = findViewById(R.id.vMainGreenView);
        jMainBlueView           = findViewById(R.id.vMainBlueView);

        jMainColorListView      = findViewById(R.id.vMainColorListView);

        init();


        // ------------[ RED SEEKBAR LISTENER ]---------------------------
        jMainRedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                // Set Red value
                hexColor.setRed(i);

                // Update the screen
                update();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // ------------[ GREEN SEEKBAR LISTENER ]---------------------------
        jMainGreenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
           public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                // Set Green value
                hexColor.setGreen(i);

                // Update the screen
                update();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // ------------[ BLUE SEEKBAR LISTENER ]---------------------------
        jMainBlueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                // Set Blue Value
                hexColor.setBlue(i);

                // Update the screen
                update();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // ------------[ LIST CLICK EVENT ]---------------------------
        jMainColorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                // Get the HexColor item in the list
                HexColor t = (HexColor) adapterView.getItemAtPosition(i);

                // Extract the values
                int r = t.getRed();
                int g = t.getGreen();
                int b = t.getBlue();

                // Create a new copy
                hexColor = new HexColor(r, g, b);

                // Set the background and sliders
                update();
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    // ------------[ Button Click LISTENER ]---------------------------
    public void addButtonClicked(View v)
    {
        // Get the RGB values
        int r = hexColor.getRed();
        int g = hexColor.getGreen();
        int b = hexColor.getBlue();
        String h = hexColor.getHex();

        // Create a new hexColor object
        HexColor n = new HexColor(r, g, b);

        // Add it to the list
        hexColors.add(n);

        // Reset the sliders and background color
        reset();

        // Notify of a data change
        hexColorListAdapter.notifyDataSetChanged();

    }

    public void setBackgroundToRGB()
    {
        // Get the Hex Color
        String hex = hexColor.getHex();

        // Set the Hex Value to the hex color
        jMainHexValueTextView.setText(hex);

        // Set the background color to the stored Hex color
        jMainBackgroundView.setBackgroundColor(Color.argb(255,hexColor.getRed(), hexColor.getGreen(), hexColor.getBlue()));
    }

    public void fillHexColorList()
    {
        hexColorListAdapter = new HexColorListAdapter(this, hexColors);
        jMainColorListView.setAdapter(hexColorListAdapter);
    }

    public boolean useBlackText(HexColor c)
    {
        // Per Web Content Accessability guild, we need to determine the luminecense
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();

        int check = r + g + b;

        if( (check > 450) || (g > 180)) { return true;}
        else { return false;}
    }

    public void setAllTextBlack()
    {
        // Get RGB values for white
        int a = 255;
        int r = 255;
        int g = 255;
        int b = 255;

        // if we need to use black, set to black
        if (useBlackText(hexColor))
        {
            r = 0;
            g = 0;
            b = 0;
        }

        // Loop through all TextViews and set the color
        for (TextView t : textViews)
        {
            t.setTextColor(Color.argb(255, r, g, b));
        }

    }

    private void update()
    {
        // Set the text labels
        jMainRedValueTextView.setText(String.valueOf(hexColor.getRed()));
        jMainGreenValueTextView.setText(String.valueOf(hexColor.getGreen()));
        jMainBlueValueTextView.setText(String.valueOf(hexColor.getBlue()));
        jMainHexValueTextView.setText(hexColor.getHex());

        // Se the progress bars
        jMainRedSeekBar.setProgress(hexColor.getRed());
        jMainGreenSeekBar.setProgress(hexColor.getGreen());
        jMainBlueSeekBar.setProgress(hexColor.getBlue());

        // Update background color
        setBackgroundToRGB();

        // Update text color
        setAllTextBlack();
    }

    private void reset()
    {
        jMainRedSeekBar.setProgress(255);
        jMainBlueSeekBar.setProgress(255);
        jMainGreenSeekBar.setProgress(255);
    }


    private void init()
    {
        // Set Background Colors
        hexColor = new HexColor(255, 255, 255);

        // Add all of the text views
        textViews = new ArrayList<TextView>();

        textViews.add(jMainRedLabelTextView);
        textViews.add(jMainRedValueTextView);
        textViews.add(jMainGreenLabelTextView);
        textViews.add(jMainGreenValueTextView);
        textViews.add(jMainBlueLabelTextView);
        textViews.add(jMainBlueValueTextView);
        textViews.add(jMainHexLabelTextView);
        textViews.add(jMainHexValueTextView);

        // This should be initializeation
        jMainRedSeekBar.setProgress(hexColor.getRed());
        jMainBlueSeekBar.setProgress(hexColor.getBlue());
        jMainGreenSeekBar.setProgress(hexColor.getGreen());

        // This should go in its own routine
        jMainRedValueTextView.setText(String.valueOf(hexColor.getRed()));
        jMainGreenValueTextView.setText(String.valueOf(hexColor.getGreen()));
        jMainBlueValueTextView.setText(String.valueOf(hexColor.getBlue()));

        // Test Here
        setBackgroundToRGB();

        // This should go into an init function
        hexColors = new ArrayList<HexColor>();
        fillHexColorList();
    }

}