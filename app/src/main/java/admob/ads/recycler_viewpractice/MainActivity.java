package admob.ads.recycler_viewpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //make object of Model Class my_Model_class
    my_Model_class[] model_classes;

    //make object of Adapter Class Adapter_Class
    Adapter_Class adapter_class;

    //make Recycler View Object recyclerView
    RecyclerView recyclerView;

    //Banner Ads object
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Before loading ads, have your app initialize the Mobile Ads SDK by calling MobileAds.initialize()
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });



        //-------------------Starting Banner Ads From Here---------------------
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest_banner = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest_banner);

        //-------------------Ending Banner Ads---------------------



        //initiate Model Class through model_classes object
        model_classes=new my_Model_class[]{

                // This  class creates the array of items for model_classes class and set the adapter class to RecyclerView.
                new my_Model_class(" General Information "),
                new my_Model_class(" Memory "),
                new my_Model_class(" Battery Information "),
                new my_Model_class(" Network Information "),
                new my_Model_class(" System Infromation "),
                new my_Model_class(" Application Information "),
                new my_Model_class(" Gravity Sensor Testing "),
                new my_Model_class(" Touch  Testing "),
                new my_Model_class(" Battery Information "),
                new my_Model_class(" Network Information "),
                new my_Model_class(" System Infromation "),
                new my_Model_class(" Application Information "),
                new my_Model_class(" Gravity Sensor Testing "),
                new my_Model_class(" Touch  Testing ")




        };

        //Now Casting the recycler view to identify the recyclerview id
        recyclerView=findViewById(R.id.my_recycler_view);

        //Initiate the AdapterClass object and put parameter of model_class
        adapter_class=new Adapter_Class(model_classes);

        //Set Linearity to recyvclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //pass adapter class object to recycler view as a parameter
        recyclerView.setAdapter(adapter_class);



    }
}