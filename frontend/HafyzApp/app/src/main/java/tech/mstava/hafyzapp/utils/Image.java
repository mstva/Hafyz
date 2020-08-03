package tech.mstava.hafyzapp.utils;

public class Image {

    private int imageWidth;
    private int imageHeight;

    public Image() {
        // empty constructor needed
    }

    public Image(int imageWidth, int imageHeight) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public int getWidth() {
        return imageWidth;
    }

    public int getHeight() {
        return imageHeight;
    }
}
