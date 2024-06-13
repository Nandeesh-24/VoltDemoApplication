package dataProviders;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class productsDp {

    @DataProvider(name = "addProduct")
    public Object[][] addProduct() {
        int[] products = new int[]{1, 2};
        String[] items = new String[]{"backpack", "bike-light"};

        Object[][] data = new Object[products.length][1];
        for (int i = 0; i < products.length; i++) {
            data[i][0] = getItemsForProduct(products[i], items);
        }

        return data;
    }

    private List<String> getItemsForProduct(int productCount, String[] items) {
        List<String> selectedItems = new ArrayList<>();
        for (int i = 0; i < productCount; i++) {
            selectedItems.add(items[i]);
        }
        return selectedItems;
    }
}
