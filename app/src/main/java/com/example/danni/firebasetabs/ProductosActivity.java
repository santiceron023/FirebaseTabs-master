package com.example.danni.firebasetabs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductosActivity extends AppCompatActivity {
    //private Button bTiendas;
    private ListView listaProductos;
    private ArrayList<Productos> productos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        listaProductos = (ListView)findViewById(R.id.listaProductos);

        productos = new ArrayList<Productos>();
        final Adapter adapter = new Adapter(getApplicationContext(),productos);
        listaProductos.setAdapter(adapter);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Productos");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                    productos.add(datasnapshot.getValue(Productos.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });



    }



    class Adapter extends ArrayAdapter<Productos> {
        public Adapter(Context context, ArrayList<Productos> productos) {
            super(context, R.layout.lista_productos,productos);
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater= LayoutInflater.from(getContext());
            View item =inflater.inflate(R.layout.lista_productos,null);

            Productos productos = getItem(position);

            TextView tNombre=(TextView)item.findViewById(R.id.tNombre);
            tNombre.setText(productos.getNombre());
            TextView tNegocio=(TextView)item.findViewById(R.id.tCantidadPresentacion);
            tNegocio.setText(productos.getTamaño());
            TextView tTiempoEnvio=(TextView)item.findViewById(R.id.tMarca);
            tTiempoEnvio.setText(productos.getMarca());
            TextView tPedidoMin=(TextView)item.findViewById(R.id.tCostoProducto);
            tPedidoMin.setText(productos.getPrecio());

            Button add = (Button) item.findViewById(R.id.bAddProduct);
            Button prodOk = (Button) item.findViewById(R.id.bProductosOk);

            /*add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(),"hola"+String.valueOf(position),Toast.LENGTH_SHORT).show();
                }
            });
            prodOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            */



            return item;
        }

    }
}

