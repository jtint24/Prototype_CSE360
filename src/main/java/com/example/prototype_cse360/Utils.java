package com.example.prototype_cse360;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Utils {

    private static final HashMap<String, Image> imageCache = new HashMap<>();

    /**
     * spacer
     *
     * @return a blank region to space out the geometry
     * */

    public static Region spacer() {
        Region retRegion = new Region();
        //retRegion.setPadding(new Insets(30));
        retRegion.setPrefWidth(30);
        return retRegion;
    }

    /**
     * labelledTextBox
     *
     * @return A text box with a horizontally aligned label
     * */

    public static HBox labelledTextBox(String name, TextField tf) {
        Label nameLabel = new Label(name);
        nameLabel.setTextFill(Color.WHITE);
        return  new HBox(nameLabel, tf);
    }

    /**
     * makeCleanButton
     * Makes a button formatted to fit the style of the UI
     * @param n The node to put inside the button
     * */
    public static Button makeCleanButton(Node n) {
        Button retButton = new Button();
        retButton.setGraphic(n);
        retButton.setStyle("-fx-border-style: none; -fx-border-width: 0; -fx-background-color: #FFE657; -fx-background-radius: 10");
        return retButton;
    }

    /**
     * makeCleanButton
     *
     * Makes a button formatted tto fit the style of the UI with plain text
     *
     * @param s The string to label the button with
     * */
    public static Button makeCleanButton(String s) {
        Button retButton = new Button(s);
        retButton.setStyle("-fx-border-style: none; -fx-border-width: 0; -fx-background-color: #FFE657; -fx-background-radius: 10");
        return retButton;
    }


    /**
     * getFoodItems
     *
     * @return the list of available food items
     * */
    public static FoodItem[] getFoodItems() {
        return new FoodItem[]{
                new FoodItem("Pepperoni Pizza", 5.99, FoodItem.Category.PIZZA, new SerializableImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSsfaA0ZWjkuzaSgO1BiLemMVp58QxGbBxzew&usqp=CAU"),
                        new OptionalMod("Extra Sauce", 1.00),
                        new OptionalMod("Extra Cheese", 1.00),
                        new RadioMods(new String[]{"Small", "Medium", "Large"}, new double[] {0, 1.00, 2.00}, 1)
                ),
                new FoodItem("Cheese Pizza", 4.99, FoodItem.Category.PIZZA, new SerializableImage("https://images.contentstack.io/v3/assets/bltbb619fd5c667ba2d/blt2d4e43bcebe1548e/60ca60fa1e0505677a881227/Cheese_Pizza.jpg"),
                    new OptionalMod("Extra Sauce", 1.00),
                    new OptionalMod("Extra Cheese", 1.00),
                    new RadioMods(new String[]{"Small", "Medium", "Large"}, new double[] {0, 1.00, 2.00}, 1)
                ),
                new FoodItem("Hawaiian Pizza", 5.49, FoodItem.Category.PIZZA, new SerializableImage("https://www.kayscleaneats.com/wp-content/uploads/2020/07/unadjustednonraw_thumb_a8b0.jpg"),
                        new OptionalMod("Extra Sauce", 1.00),
                        new OptionalMod("Extra Cheese", 1.00),
                        new RadioMods(new String[]{"Small", "Medium", "Large"}, new double[] {0, 1.00, 2.00}, 1)
                ),
                new FoodItem("Caesar Salad", 3.49, FoodItem.Category.SALAD, new SerializableImage("https://thumbs.dreamstime.com/b/plate-chicken-caesar-salad-white-background-plate-chicken-caesar-salad-white-background-130777588.jpg"),
                        new OptionalMod("Extra Dressing", .50),
                        new RadioMods(new String[]{"Small", "Medium", "Large"}, new double[] {0, 1.00, 2.00}, 1)
                ),
                new FoodItem("Garden Salad", 2.99, FoodItem.Category.SALAD, new SerializableImage("https://media.istockphoto.com/photos/vegetable-salad-on-white-background-picture-id1009286132?k=20&m=1009286132&s=170667a&w=0&h=5U71BfSfRGkAiX6ZKRjeXdDQ3SuAYcISvb0UJzJw_B0="),
                        new OptionalMod("Extra Dressing", .50),
                        new RadioMods(new String[]{"Small", "Medium", "Large"}, new double[] {0, 1.00, 2.00}, 1)
                ),
                new FoodItem("Buffalo Wings", 5.99, FoodItem.Category.WINGS, new SerializableImage("https://assets.bonappetit.com/photos/57ad22671b334044149753ed/4:3/w_1775,h_1331,c_limit/BAS-BEST-HOT-WINGS.jpg"),
                        new RadioMods(new String[]{"6 pc", "8 pc", "10 pc"}, new double[] {0, 1.00, 2.00}, 1)
                ),
                new FoodItem("Cookie", 1.99, FoodItem.Category.DESSERT, new SerializableImage("https://envato-shoebox-0.imgix.net/5f6b/069d-c501-41cc-a5e8-09067132b96d/IMG_0440-Edit_20161019.jpg?auto=compress%2Cformat&fit=max&mark=https%3A%2F%2Felements-assets.envato.com%2Fstatic%2Fwatermark2.png&markalign=center%2Cmiddle&markalpha=18&w=700&s=662a7a310abacd265c4218db27d9ebab")),
                new FoodItem("Coca-Cola", .99, FoodItem.Category.DRINKS, new SerializableImage("https://res.cloudinary.com/fleetnation/image/private/c_fit,w_1120/g_south,l_text:style_gothic2:%C2%A9%20urbanbuzz,o_20,y_10/g_center,l_watermark4,o_25,y_50/v1434359372/iwqhwkmtyo2cquilmjbm.jpg"),
                        new RadioMods(new String[]{"Small", "Medium", "Large"}, new double[] {0, .50, .75}, 1)
                ),
                new FoodItem("Sprite", .99, FoodItem.Category.DRINKS, new SerializableImage("https://media.istockphoto.com/photos/sprite-can-on-an-isolated-white-background-picture-id458556265?k=20&m=458556265&s=170667a&w=0&h=Eabx51DYMfH7QA5sV_YmQnPGbA721l7b0Udugm_kGQw="),
                        new RadioMods(new String[]{"Small", "Medium", "Large"}, new double[] {0, .50, .75}, 1)
                ),
                new FoodItem("Dr. Pepper", .99, FoodItem.Category.DRINKS, new SerializableImage("https://thumbs.dreamstime.com/b/dr-pepper-novyy-urengoy-russia-march-aluminium-can-isolated-over-white-background-177812013.jpg"),
                        new RadioMods(new String[]{"Small", "Medium", "Large"}, new double[] {0, .50, .75}, 1)
                ),
        };
    }

    public static void cacheImageByURL(String url) {
        imageCache.put(url, new Image(url));
    }

    public static Image getCachedImageByURL(String url) {
        if (imageCache.containsKey(url)) {
            return imageCache.get(url);
        } else {
            return new Image(url);
        }
    }

    public static ArrayList<ShoppingCart> readOrdersFromFiles(String directory) {
        ArrayList<ShoppingCart> orders = new ArrayList<>();
        File dir = new File(directory);
        File[] directoryListing = dir.listFiles();
        try {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    if (child.isFile() && child.getName().endsWith(".order")) {
                        FileInputStream file = new FileInputStream(child);
                        ObjectInputStream in = new ObjectInputStream(file);

                        ShoppingCart newCart = (ShoppingCart) in.readObject();
                        orders.add(newCart);

                        in.close();
                        file.close();

                        System.out.println(child.getName() + " has been deserialized");
                        System.out.println(newCart);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return orders;
    }

    /**
     * isNumericChar
     *
     * @return whether the char is a digit 0-9
     * */
    private static boolean isNumericChar(char c) {
        return "1234567890".contains(c+"");
    }

    /**
     * isNumeric
     *
     * @return if a string only contains digits
     * */
    private static boolean isNumeric(String s) {
        if (s.length() == 0) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (!isNumericChar(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * isAsuEmail
     *
     * @return if a string is a valid ASU email
     * */

    static boolean isAsuEmail(String s) {
        return s.matches(".+@asu\\.edu");
    }

    /**
     * isNumber
     *
     * @return whether a string is an integer of length n
     * */

    static boolean isNumber(String s, int n) {
        if (s.length() == n) {
            return isNumeric(s);
        } else {
            return false;
        }
    }
    static boolean isValidDate(String s) {
        return s.matches("^(0[1-9]|1[0-2])\\/?([0-9]{4}|[0-9]{2})$");
    }
}
