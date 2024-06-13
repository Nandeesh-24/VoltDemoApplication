package dataProviders;

import org.testng.annotations.DataProvider;

import java.util.Map;

public class loginDp {

    @DataProvider(name = "validUser")
    public Object[][] validUser(){
        Map<String,Boolean> usernames = Map.of(
                "",false,
                "standard_user",true,
                "random_user",false
        );
        Object[][] data = new Object[usernames.size()][3];
        int count = 0;
        for (String username : usernames.keySet()){
            data[count][0] = username;
            data[count][1] = usernames.get(username);
            data[count][2] = "secret_sauce";
            count++;
        }
        return data;
    }


    @DataProvider(name = "validPassword")
    public Object[][] validPassword(){
        Map<String,Boolean> passwords = Map.of(
                "",false,
                "secret_sauce",true,
                "random_user",false
        );
        Object[][] data = new Object[passwords.size()][3];
        int count = 0;
        for (String password : passwords.keySet()){
            data[count][0] = "standard_user";
            data[count][1] = passwords.get(password);
            data[count][2] = password;
            count++;
        }
        return data;
    }
}
