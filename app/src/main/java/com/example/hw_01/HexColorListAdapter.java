package com.example.hw_01;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.ArrayList;

public class HexColorListAdapter extends BaseAdapter
{
    // Variables
    private Context context;
    private ArrayList<HexColor> hexColors;

    // Constructor
    public HexColorListAdapter(Context c, ArrayList<HexColor> l)
    {
        context = c;
        hexColors = l;
    }

    @Override
    public int getCount() {return hexColors.size();}

    @Override
    public Object getItem(int i) {return hexColors.get(i);}

    @Override
    public long getItemId(int i) {return i;}

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        // This will run the very first time
        if(view == null)
        {
            // We need to grab the view to be updated
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.color_list_cell, null);
        }

        // Find GUI elements in my custom cell
        TextView    jColorListCellRedValueTextView      = view.findViewById(R.id.vColorListCellRedValueTextView);
        TextView    jColorListCellGreenValueTextView    = view.findViewById(R.id.vColorListCellGreenValueTextView);
        TextView    jColorListCellBlueValueTextView     = view.findViewById(R.id.vColorListCellBlueValueTextView);
        TextView    jColorListCellHexValueTextView      = view.findViewById(R.id.vColorListCellHexValueTextView);
        TextView    jColorListCellRedLabelTextView      = view.findViewById(R.id.vColorListCellRedLabelTextView);
        TextView    jColorListCellGreenLabelTextView    = view.findViewById(R.id.vColorListCellGreenLabelTextView);
        TextView    jColorListCellBlueLabelTextView     = view.findViewById(R.id.vColorListCellBlueLabelTextView);
        TextView    jColorListCellHexLabelTextView      = view.findViewById(R.id.vColorListCellHexLabelTextView);
        View        jColorListCellView                  = view.findViewById(R.id.vColorListCellView);

        HexColor c = hexColors.get(i);
        // Set the text fields here
        jColorListCellRedValueTextView.setText(String.valueOf(c.getRed()));
        jColorListCellGreenValueTextView.setText(String.valueOf(c.getGreen()));
        jColorListCellBlueValueTextView.setText(String.valueOf(c.getBlue()));
        jColorListCellHexValueTextView.setText(c.getHex());
        jColorListCellView.setBackgroundColor(Color.argb(255,c.getRed(), c.getGreen(), c.getBlue()));

        // Get RGB values for white
        int a = 255;
        int r = 255;
        int g = 255;
        int b = 255;

        // if we need to use black, set to black
        if (useBlackText(c))
        {
            r = 0;
            g = 0;
            b = 0;
        }

        // Set the text black or white
        jColorListCellRedValueTextView.setTextColor(Color.argb(a, r, g, b));
        jColorListCellGreenValueTextView.setTextColor(Color.argb(a, r, g, b));
        jColorListCellBlueValueTextView.setTextColor(Color.argb(a, r, g, b));
        jColorListCellHexValueTextView.setTextColor(Color.argb(a, r, g, b));
        jColorListCellRedLabelTextView.setTextColor(Color.argb(a, r, g, b));
        jColorListCellGreenLabelTextView.setTextColor(Color.argb(a, r, g, b));
        jColorListCellBlueLabelTextView.setTextColor(Color.argb(a, r, g, b));
        jColorListCellHexLabelTextView.setTextColor(Color.argb(a, r, g, b));

        return view;
    }

    // Determine if we need black or white text
    public boolean useBlackText(HexColor c)
    {
        // Determine if we use black text or not
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();

        int check = r + g + b;

        if( (check > 450) || (g > 180)) { return true;}
        else { return false;}

    }


}
