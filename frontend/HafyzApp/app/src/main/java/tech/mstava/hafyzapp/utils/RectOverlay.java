package tech.mstava.hafyzapp.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class RectOverlay extends GraphicOverlay.Graphic {

    private static final float STROKE_WIDTH = 4.0f;
    private static final float TEXT_SIZE = 40.0f;

    private final Paint boxPaint;
    private final Paint textPaint;

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

        // for create person name under box around face on image
        textPaint = new Paint();
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(TEXT_SIZE);
    }

    @Override
    public void draw(Canvas canvas) {
        // to create new rect that will be drawn around face
        RectF rect = new RectF(left, top, right, bottom);
        rect.left = translateX(rect.left);
        rect.top = translateY(rect.top);
        rect.right = translateX(rect.right);
        rect.bottom = translateY(rect.bottom);

        // to set the location of box and text
        canvas.drawRect(rect, boxPaint);
        canvas.drawText(name , rect.left-50, rect.bottom+50, textPaint);

    }
}
