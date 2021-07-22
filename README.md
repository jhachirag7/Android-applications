# Android-applications

This is a Bank Detail Generator app through ifsc code :

******* make sure to give internet permission in android manifest file *******

              <uses-permission android:name="android.permission.INTERNET"/>
              

    i) In the activity_main.xml -> EditText field : to take input from user i.e bank ifsc code.
                                Button : to start action.
                                textView : to print bank details.
                                
    ii) In the activity_main.java -> I took the ifsc code given by the user and use a requestQueue to make request and volley library.
                                     And crated a jsonObjectRequest to make request to the url( https://bank-apis.justinclicks.com/API/V1/IFSC/ + ifsc) and on response just fecth 
                                     the data. If you use different api then there can be multiple jsons than that time you can use jsonArray to fetch data.
                                     
                                     
I have used this api -> https://bank-apis.justinclicks.com/API/V1/IFSC/ for bank details generator.
                                 
                                 
