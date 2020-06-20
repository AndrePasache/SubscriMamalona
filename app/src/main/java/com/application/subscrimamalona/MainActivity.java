package com.application.subscrimamalona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.application.subscrimamalona.Controlador.CasilleroContent;
import com.application.subscrimamalona.Controlador.Data;
import com.application.subscrimamalona.Controlador.PagerController;
import com.application.subscrimamalona.Controlador.Pagos;
import com.application.subscrimamalona.Controlador.Subscripciones;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem tab1, tab2;
    PagerController pagerAdapter;
    FloatingActionButton plus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        tab1 = findViewById(R.id.tabsubscripciones);
        tab2 = findViewById(R.id.tabpagos);

        pagerAdapter = new PagerController(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0){
                    pagerAdapter.notifyDataSetChanged();
                }
                if(tab.getPosition()==1){
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            //no lo usamos
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            //no lo usamos
            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        plus = (FloatingActionButton)findViewById(R.id.plusBoton);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Anadir(v);
            }
        });


        //Pagos Pagos = new Pagos();
        /*Bundle bundle = getIntent().getExtras();
        String nombre = getIntent().getStringExtra("name");
        String monto = getIntent().getStringExtra("amount");
        String tipo = getIntent().getStringExtra("type");*/
        /*if (bundle!=null) {
            Toast.makeText(this, tipo, Toast.LENGTH_SHORT).show();
        }*/

        /*if(bundle != null && bundle.get("type").toString().equals("Pago")){
            Bundle bundle1 = new Bundle();
            bundle1.putString("nombre", nombre);
            bundle1.putString("monto", monto);
            bundle1.putString("tipo",tipo);
            Pagos pagos = new Pagos();
            pagos.setArguments(bundle1);
            //getSupportFragmentManager().beginTransaction().replace(R.id.tabpagos,pagos).commit();
            //Toast.makeText(this, "Hay Data", Toast.LENGTH_SHORT).show();
        }*/
    }
    public void Anadir(View view){
        Intent anadir = new Intent(this, Add_Activity.class);
        startActivity(anadir);
    }
}
