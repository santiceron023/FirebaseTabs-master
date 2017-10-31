package com.example.danni.firebasetabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarritoFragment extends Fragment  implements View.OnClickListener{
    private Button bCarrito;
    private TextView tCarrito;
    public CarritoFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_carrito, container, false);

        bCarrito=(Button)view.findViewById(R.id.bCarrito);
        bCarrito.setOnClickListener((View.OnClickListener) this);

        tCarrito=(TextView)view.findViewById(R.id.tCarrito);


        return view;
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bCarrito:
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");
                DatabaseReference myRef2 = database.getReference("message2");
                myRef2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        tCarrito.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
                myRef.setValue("Carrito");
                break;


        }
    }

}
