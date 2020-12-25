package admob.ads.recycler_viewpractice;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import org.w3c.dom.Text;

import java.util.List;

public class Adapter_Class extends RecyclerView.Adapter<Adapter_Class.rvviewholder> {

    //make object of Model Class my_Model_class
    private my_Model_class[] mylists;


    //Create Constructor Class of the Model class
    public Adapter_Class(my_Model_class[] mylists) {
        this.mylists = mylists;
    }

    // Create a String Obj
    String item_data;

    //Interstilal Ads
    InterstitialAd mInterstitialAd;

    @NonNull
    @Override
    public rvviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent,false);

        return new rvviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull rvviewholder holder, int position) {

         holder.myTitle.setText(mylists[position].getMy_title());

         item_data=holder.myTitle.getText().toString();


    }

    @Override
    public int getItemCount() {
        return mylists.length;
    }


    //This is View Holder Class with name of rvviewholder
    public class rvviewholder extends RecyclerView.ViewHolder {

        //TextView Obj
        private TextView myTitle;

        //CardView Obj
        private CardView main_card_view;

        public rvviewholder(@NonNull View itemView) {
            super(itemView);

            //TextView Casting
            myTitle=itemView.findViewById(R.id.item_id);

            //CardView Casting
            main_card_view=itemView.findViewById(R.id.main_card_view);




            //-------------------Starting  InterstitialAd Ads From Here---------------------

            //Intersilal Ads Requesting
            mInterstitialAd = new InterstitialAd(itemView.getContext());
            // set the ad unit ID
            mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");


            AdRequest adRequest = new AdRequest.Builder().build();

            // Load ads into Interstitial Ads
            mInterstitialAd.loadAd(adRequest);



            //OnClickListener in main_card_view
            main_card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent_=new Intent(itemView.getContext(),Detail_Activity.class);
                    intent_.putExtra("item_data",item_data);
                    itemView.getContext().startActivity(intent_);

                    //Intertiatilal Ads show if Successfully Loaded
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();

                        mInterstitialAd.setAdListener(new AdListener() {

                            @Override
                            public void onAdClosed() {
                                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                            }
                        });
                    }

                    else{
                        Toast.makeText(itemView.getContext(), "Ad Are Not Loaded", Toast.LENGTH_SHORT).show();
                    }

                }
            });

            //-------------------Ending  InterstitialAd ---------------------

        }
    }
}
