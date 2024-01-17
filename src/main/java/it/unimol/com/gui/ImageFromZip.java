package it.unimol.com.gui;

import java.io.InputStream;

public class ImageFromZip {
    private String name;
    private InputStream inputStream;

    public ImageFromZip(String name, InputStream inputStream) {
        this.name = name;
        this.inputStream = inputStream;
    }

    public String getName() {
        return name;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    @Override
    public String toString() {
        return name;
    }
}
