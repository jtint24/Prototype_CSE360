package com.example.prototype_cse360;

import javafx.scene.image.Image;

import java.io.Serializable;

public class SerializableImage implements Serializable {
    private final String location;

    SerializableImage(String _location) {
        location = _location;
        Utils.cacheImageByURL(location);
    }

    public Image getImage() {
        return Utils.getCachedImageByURL(location);
    }
}
