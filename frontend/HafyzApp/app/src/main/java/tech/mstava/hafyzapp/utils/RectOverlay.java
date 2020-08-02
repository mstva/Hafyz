package tech.mstava.hafyzapp.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class RectOverlay extends GraphicOverlay.Graphic {

    private static final float STROKE_WIDTH = 4.0f;

    private final Paint boxPaint;

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

        // to draw a box around face
        boxPaint = new Paint();
        boxPaint.setColor(Color.RED);
        boxPaint.setStyle(Paint.Style.STROKE);
        boxPaint.setStrokeWidth(STROKE_WIDTH);
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
