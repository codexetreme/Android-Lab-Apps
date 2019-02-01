package gears.com.lab_app_2.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class CryptoMenuItem {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<CryptoItem> ITEMS = new ArrayList<>();
    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, CryptoItem> ITEM_MAP = new HashMap<String, CryptoItem>();

    private static final int COUNT = 5;
    static String[] names = new String[]{"Bitcoin","Ripple","ethereum","stellar lumens","monero"};
    static String[] details = new String[]{
            "Bitcoin has first mover advantage and is the most famous crypto coin out there",
            "Ripple is a new, premined, and centralized crypto, japanese banks use it currently",
            "Ethereum is used for ICO's and is going for $1000 per coin",
            "Stellar Lumens is very new coin and has no transaction fees",
            "Monero is a anonymous coin that uses ring signatures to hide payment details"
    };
    static {

        // Add some sample items.
        for (int i = 0; i < COUNT; i++) {
            addItem(createDummyItem(i,names[i],details[i]));
        }
    }

    private static void addItem(CryptoItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static CryptoItem createDummyItem(int position,String name,String details) {
        CryptoItem t = new CryptoItem(String.valueOf(position), "Crypto : " + name);
        t.setDetails(details);
        return t;
    }



    /**
     * A dummy item representing a piece of content.
     */
    public static class CryptoItem {
        public final String id;
        public final String content;
        public String details;



        public CryptoItem(String id, String content) {
            this.id = id;
            this.content = content;
//            this.details = details;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
