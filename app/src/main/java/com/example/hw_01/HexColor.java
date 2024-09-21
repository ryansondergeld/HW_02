package com.example.hw_01;

public class HexColor
{
    int red;
    int green;
    int blue;
    String hex;

    public HexColor()
    {
        red     = 0;
        green   = 0;
        blue    = 0;
        setHex();
    }

    public HexColor(int r, int g, int b)
    {
        red     = r;
        green   = g;
        blue    = b;
        setHex();
    }

    // Getters and Setters
    public int getRed()     { return red;}
    public int getGreen()   { return green;}
    public int getBlue()    { return blue;}
    public String getHex()  { return hex;}

    public void setRed(int r)
    {
        red = r;
        setHex();
    }
    public void setGreen(int g)
    {
        green = g;
        setHex();
    }
    public void setBlue(int b)
    {
        blue = b;
        setHex();
    }
    public void setHex()
    {
        hex = String.format("#%02X%02X%02X", red, green, blue);
    }
}
