package mc322.jogo;

import java.awt.Color;

public enum ColorEnum {
	GRASS(122,232,90),
	CITY(139,69,19),
	FOREST(0,100,0),
	WATER(30,144,255),
	MOUNTAIN(150,150,150),
	LUMBERMILL(	235, 205, 128),
	FARM(187,255,185);

    private int red;
    private int green;
    private int blue;

    private ColorEnum(int r, int g, int b)
    {
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    public Color getColor()
    {
        return new Color(red, green, blue);
    }
}
