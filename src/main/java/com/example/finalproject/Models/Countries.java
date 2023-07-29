package com.example.finalproject.Models;

import java.util.Locale;

public class Countries {
    public static String[] printCountries() {
        String[] countryCodeCodesArray = Locale.getISOCountries();

        int ctr = 1;
        //Iterate over country codes array
        for (String countryCode : countryCodeCodesArray) {
            //Now, we will get Locale from countryCode
            Locale countryLocale = new Locale("", countryCode);

            System.out.println("S.No = "+ (ctr++) +" > Country Code is = "
                    + countryLocale.getCountry()
                    + ",  Country Name is = " + countryLocale.getDisplayCountry());
        }

        return countryCodeCodesArray;
    }
}
