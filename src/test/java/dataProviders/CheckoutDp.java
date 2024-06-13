package dataProviders;

import org.testng.annotations.DataProvider;

public class CheckoutDp {

    @DataProvider(name = "checkoutInfo")
    public Object[][] checkoutInfo() {
        String[] firstNames = new String[]{"", "a"};
        String[] lastNames = new String[]{"", "a"};
        String[] postalCodes = new String[]{"", "123"};

        int totalCombinations = firstNames.length * lastNames.length * postalCodes.length;
        Object[][] data = new Object[totalCombinations][3];
        int index = 0;
        for (String firstName : firstNames) {
            for (String lastName : lastNames) {
                for (String postalCode : postalCodes) {
                    data[index][0] = firstName;
                    data[index][1] = lastName;
                    data[index][2] = postalCode;
                    index++;
                }
            }
        }

        return data;
    }
}
