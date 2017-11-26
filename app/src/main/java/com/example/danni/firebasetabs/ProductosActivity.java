package com.example.danni.firebasetabs;

import android.content.Context;
import android.content.Intent;
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
    String tiendaId;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    //ArrayList<Integer> ProductosArray = new ArrayList<Integer>();
    int numPedidos = 0;
    int[] ProductosArray = new int[10]; ///numero de hijos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        listaProductos = (ListView) findViewById(R.id.listaProductos);


        Bundle extras = getIntent().getExtras();
        tiendaId = extras.getString("TiendaId");


        productos = new ArrayList<Productos>();
        final Adapter adapter = new Adapter(getApplicationContext(), productos);
        listaProductos.setAdapter(adapter);


        myRef = database.getReference("Productos").child(tiendaId);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
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
            super(context, R.layout.lista_productos, productos);
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView,
                            @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.lista_productos, null);

            final Productos productos = getItem(position);

            TextView tNombre = (TextView) item.findViewById(R.id.tNombre);
            tNombre.setText(productos.getNombre());
            TextView tNegocio = (TextView) item.findViewById(R.id.tCantidadPresentacion);
            tNegocio.setText(productos.getTamaño());
            TextView tTiempoEnvio = (TextView) item.findViewById(R.id.tMarca);
            tTiempoEnvio.setText(productos.getMarca());
            TextView tPedidoMin = (TextView) item.findViewById(R.id.tCostoProducto);
            tPedidoMin.setText(productos.getPrecio());

            Button add = (Button) item.findViewById(R.id.bAddProduct);
            Button prodOk = (Button) item.findViewById(R.id.bProductosOk);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getContext(),"CLicked :"+String.valueOf(position),Toast.LENGTH_SHORT).show();
                    //ProductosArray.add(numPedidos, position);
                    ProductosArray[position + 1] += 1;
                    numPedidos++;
                }
            });
            /*prodOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(),"end",Toast.LENGTH_SHORT).show();
                }
            });*/
            return item;
        }

    }

    public void ok(View view) {

        String a;
        int CantidadActual = 0, i = 0;


        Toast.makeText(getApplicationContext(), String.valueOf(ProductosArray[1]), Toast.LENGTH_SHORT).show();


        for (int factor = 1; factor <= ProductosArray.length; factor++) {

            if (ProductosArray[factor] > 0) {

                funcion(factor);
            }
        }
    }
    private void funcion(final int pos){
        myRef = database.getReference("Pedidos").child(tiendaId); //pedidos -- lienda
        myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            String valor = dataSnapshot.child(String.valueOf(pos)).getValue(String.class);

            //i++;


////////////////////IDEAS:
/// 1. CREAR CLASE CON POSICIONES Y UN VALOR
/// LEER MEDIATNE MUMUCHOS GET REREFNCE SIN FOR
/// CANCELAR

            //CantidadActual = Integer.valueOf(valor);

            //tiendas.add(tiendasSnapshot.getValue(Tiendas.class));
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
    }
}/*


                        a = String.valueOf(ProductosArray[factor]);
                myRef.child(String.valueOf(factor)).setValue("cantidad",a);
            }

        myRef.child("33").setValue("466");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String w = dataSnapshot.child("33").getValue(String.class);
                Toast.makeText(getApplicationContext(), w, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

        /*for (int factor = 1; factor <= ProductosArray.length; factor++) {

            if (ProductosArray[factor] > 0) {
                a = String.valueOf(ProductosArray[factor]);
                myRef.child(String.valueOf(factor)).setValue("cantidad",a);
            }
        }
    }

               /*
               myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //ya esta comparar lo que vale
                        if (dataSnapshot.child(String.valueOf(a)).exists()){


                        }else{
                            dataSnapshot.set
                        }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });


            }


    }
/*

     final int id = view.getId();

        final String uidd = eID.getText().toString();

        final String name,email,phone;
        name = eName.getText().toString();
        phone = ePhone.getText().toString();
        email = eEmail.getText().toString();
        switch (id){
            case R.id.bCreate:
                myRef = database.getReference("user").child("user"+uidd);
                user =  new User(name,email,phone,"user"+uidd);
                myRef.setValue(user);
                //uid++;
                clean();
                break;
    */
//finish();




