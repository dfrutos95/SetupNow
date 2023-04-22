package com.dfrutos95.setupnow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView miLista;

    private String [] categorias= new String[]{"Casas inteligentes","Servicios Hardware","Servicios Software",
            "Licencias Software","Cursos","Asistencia"}; //Datos
    private String [] descripciones= new String[]{"Diseño y configuracion de casas inteligentes","Reparaciones y mantenimiento de equipos","Servicios de software",
            "Venta de licencias","Cursos de formacion","Contacta con nosotros"}; //Datos
    NavigationBarView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home) {
                    // Handle Home navigation item click
                    return true;
                } else if (itemId == R.id.navigation_dashboard) {
                    mostrarCesta();
                    return true;
                } else if (itemId == R.id.navigation_notifications) {
                    // Handle Notifications navigation item click
                    return true;
                }
                return true;
            }
        });
        crearLista();

    }

    //Lo del menu no funciona
    public boolean onCreateOptionsMenu(Menu menu) {
        //si quieres un menú, "ínflalo"
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.Opcion1) {
            // Handle Home navigation item click
            System.exit(0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void mostrarCesta(){
        Intent abrirCesta = new Intent(this, Cesta.class);
        startActivity(abrirCesta);
    }
    public void crearLista(){
        ArrayList<Categoria> listaCategorias=new ArrayList<Categoria>();
        for(int i=0;i< categorias.length;i++){
            Categoria c=new Categoria(categorias[i],descripciones[i]);
            listaCategorias.add(c);
        }

        miLista=findViewById(R.id.lista); //IU -> se va a "inflar" una fila por cada ciudad a través de mi_fila_personalizada.xml

        MiAdaptadorPersonalizado adapter=
                new MiAdaptadorPersonalizado(this,R.layout.fila_personalizada,listaCategorias);

        //enfuchar adaptador a la vista
        miLista.setAdapter(adapter);
    }

    public void crearLista(MenuItem item) {
        ArrayList<Categoria> listaCategorias=new ArrayList<Categoria>();
        for(int i=0;i< categorias.length;i++){
            Categoria c=new Categoria(categorias[i],descripciones[i]);
            listaCategorias.add(c);
        }

        miLista=findViewById(R.id.lista); //IU -> se va a "inflar" una fila por cada ciudad a través de mi_fila_personalizada.xml

        MiAdaptadorPersonalizado adapter=
                new MiAdaptadorPersonalizado(this,R.layout.fila_personalizada,listaCategorias);

        //enfuchar adaptador a la vista
        miLista.setAdapter(adapter);
    }

    public class MiAdaptadorPersonalizado extends ArrayAdapter<Categoria> {
        private int mResource;
        private ArrayList<Categoria> listaCategorias;

        public MiAdaptadorPersonalizado(@NonNull Context context, int resource, @NonNull List<Categoria> objects) {
            super(context, resource, objects);
            mResource=resource;
            listaCategorias=(ArrayList<Categoria>) objects;
        }


        //Este método sólo es necesario reescribirlo si el adaptador se enchufa a un spinner
        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return crearFila(position,convertView,parent);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return crearFila(position,convertView,parent);
        }

        private View crearFila(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            //Este método es invocado tantas veces como "filas" se pinten en la actividad

            LayoutInflater miInflador= getLayoutInflater();
            View miFila=miInflador.inflate(mResource,parent,false);

            TextView txtDescripcion=miFila.findViewById(R.id.txtDescripcion);
            TextView txtCategoria=miFila.findViewById(R.id.txtCategoria);
            ImageView imgCategoria=miFila.findViewById(R.id.imagenCategoria);

            txtCategoria.setText(listaCategorias.get(position).nombre);
            txtDescripcion.setText(listaCategorias.get(position).descripcion);
            imgCategoria.setImageResource(listaCategorias.get(position).imagen);
            Log.d("RDT","creada la fila"+position);
            return miFila;
        }

    }


    public class Categoria{
        String nombre;
        String descripcion;
        int imagen;

        public Categoria(String nombre, String descripcion) {
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.imagen = R.drawable.logo;
        }
    }


}