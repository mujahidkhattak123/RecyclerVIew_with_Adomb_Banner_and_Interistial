package admob.ads.recycler_viewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Detail_Activity extends AppCompatActivity {

    //create String Object
    String item_data;

    //create a Textview Object
    private TextView myTitle;

    //Banner Ads object
    private AdView mAdView;

    //Interstitial ads are requested and shown by InterstitialAd objects.
    InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);

        //Before loading ads, have your app initialize the Mobile Ads SDK by calling MobileAds.initialize()
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        //-------------------Starting Banner Ads From Here---------------------


        mAdView = findViewById(R.id.adView);
        //the next step is to load an ad. That's done with the loadAd() method in the AdView class.
        AdRequest adRequest_banner = new AdRequest.Builder().build();
        // It takes an AdRequest parameter
        mAdView.loadAd(adRequest_banner);

        //-------------------Ending Banner Ads---------------------





        //-------------------Starting  InterstitialAd Ads From Here---------------------

        //A single InterstitialAd object can be used to request and display multiple interstitial ads over the course of an activity's lifespan,
        // so you only need to construct it once.
        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");


        AdRequest adRequest = new AdRequest.Builder().build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {

                //To load an interstitial ad, call the InterstitialAd object's loadAd() method.
                // This method accepts an AdRequest object as its single parameter:
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

        //-------------------Ending  InterstitialAd Ads ---------------------


        //Cast the TextView with XML
        myTitle=findViewById(R.id.item_id);

        //Create The Intent Obj
        Intent intent = getIntent();

        //Get Data through intent.getStringExtra function
        item_data=intent.getStringExtra("item_data");

        myTitle.setText(item_data);

    }

    @Override
    public void onBackPressed() {

        //Now Show the InterstitialAd when user Click On Back Button
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        finish();
        super.onBackPressed();
    }

}