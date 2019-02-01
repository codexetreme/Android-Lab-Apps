package gears.com.lab_app_6;

/**
 * Created by cse on 2/20/2018.
 */

public class CryptoItem {

    private String Name;
    private String Symbol;
    private float Price;

    public CryptoItem(String name, String symbol, float price) {
        Name = name;
        Symbol = symbol;
        this.Price = price;
    }

    public String getName() {
        return Name;
    }

    public String getSymbol() {
        return Symbol;
    }

    public float getPrice() {
        return Price;
    }

    @Override
    public String toString() {
        return "\'"+ Name + "\', \'" + Symbol + "\', " + String.valueOf(Price);
    }
}
