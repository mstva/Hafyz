package tech.mstava.hafyzapp.utils;

import android.graphics.Canvas;

public class RectOverlay extends GraphicOverlay.Graphic {

    private GraphicOverlay graphicOverlay;
    private String name;
    private int top;
    private int bottom;
    private int right;
    private int left;

    public RectOverlay(GraphicOverlay graphicOverlay, String name, int top, int bottom, int right, int left) {
        super(graphicOverlay);

        this.name = name;
        this.top = top;
        this.bottom = bottom;
        this.right = right;
        this.left = left;
        this.graphicOverlay = graphicOverlay;
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
