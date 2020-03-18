package com.example.project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;



public class AttractionListViewAdapter extends ArrayAdapter<Attraction> {

    private ArrayList<Attraction> attractions;
    private Context context;
    private int resource;
    String nameforWishList = "";



    public AttractionListViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Attraction> objects) {
        super(context, resource, objects);
        attractions = objects;
        this.context=context;
        this.resource=resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        List<Attraction> getAttractionDetails;

        List<User> getUsernameValue;

//        String nameValue = "";
//        String imageValue = "";
//        String descriptionValue = "";
//        String addressValue = "";

        getUsernameValue = LoginActivity.connection.userDao().getAllUsers();
        for(int i = 0;i<getUsernameValue.size();i++){
            nameforWishList  = getUsernameValue.get(i).getUsername();
        }

       Attraction attraction = attractions.get(position);

       final String nameValue = attraction.getAttractionName();
       String imageValue1 = attraction.getPhoto();
       String descriptionValue = attraction.getAttractionDesc();
       String addressValue = attraction.getAttractionAddress();

        Log.d("name",nameValue);

        //Create an Layout inflater;
        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(this.resource,parent,false);

        //getAttractionDetails = LoginActivity.attractionConnection.attractionDao().getAllAttraction();

        //fecth text view

        TextView name = (TextView) convertView.findViewById(R.id.nametxt);
        ImageView image = (ImageView) convertView.findViewById(R.id.imageViewid);
        TextView description = (TextView) convertView.findViewById(R.id.decriptiontxt);
        TextView address = (TextView) convertView.findViewById(R.id.addresstxttxt);
        final Button addingTowishList = (Button) convertView.findViewById(R.id.wishistbtuuon);

        RequestOptions options = new RequestOptions()
           .placeholder(R.drawable.attractionimage)
                .error(R.drawable.ic_action_attraction);

        Glide.with(context).load(imageValue1).thumbnail(Glide.with(context).load(R.drawable.attra1)).apply(options).into(image);

        name.setText(nameValue);
        description.setText(descriptionValue);
        address.setText(addressValue);

        addingTowishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Wishlists w = new Wishlists(nameforWishList,nameValue);
                LoginActivity.wishListConnection.wishListDao().addwishlist(w);

            }
        });

        return convertView;
    }


}
