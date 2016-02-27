package capitales.a14albertoab_proxecto;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnContacto, btnAlumno, btnOferta, btnCentro;
    AlertDialog.Builder venta;
    private TextView etNome, etContrasinal;
    private SharedPreferences preferencias;
    private static String nome, contrasinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meterPreferencias();
        btnCentro=(Button) findViewById(R.id.idCentro);
        btnOferta=(Button) findViewById(R.id.idOferta);
        btnContacto=(Button) findViewById(R.id.idContacto);
        btnAlumno=(Button) findViewById(R.id.idAlumno);

        btnCentro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Centro.class));
            }
        });

        btnOferta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Oferta.class));
            }
        });

        btnContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            }
        });

        btnAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarLogin(1);
            }
        });

    }

    private void chamarLogin(int num){
        showDialog(num);
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {

            case 1:
                String infService = Context.LAYOUT_INFLATER_SERVICE;
                LayoutInflater li = (LayoutInflater) getApplicationContext().getSystemService(infService);
                // Inflamos o compoñente composto definido no XML
                View inflador = li.inflate(R.layout.login, null);
                // Buscamos os compoñentes dentro do Diálogo

                TextView txt1=(TextView) inflador.findViewById(R.id.txtNome);
                TextView txt2=(TextView) inflador.findViewById(R.id.txtContra);

                etNome = (TextView) inflador.findViewById(R.id.idUsuario);
                etContrasinal = (TextView) inflador.findViewById(R.id.idContrasinal);

                venta = new AlertDialog.Builder(this);
                venta.setTitle("Indica usuario e contrasinal");
                // Asignamos o contido dentro do diálogo (o que inflamos antes)
                venta.setView(inflador);
                // Non se pode incluír unha mensaxe dentro deste tipo de diálogo!!!
                venta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int boton) {
                        comprobarLogin();
                    }
                });
                venta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int boton) {
                        Toast.makeText(getApplicationContext(), "Abortouse o inicio de sesión", 1).show();
                    }
                });
                return venta.create();
        }
        return null;
    }

    private void comprobarLogin(){
        if(!etNome.getText().toString().equals(nome) || !etContrasinal.getText().toString().equals(contrasinal)){
            Toast.makeText(getApplicationContext(),"Datos incorrectos",Toast.LENGTH_LONG).show();
        }else startActivity(new Intent(getApplicationContext(), Alumno.class));
    }

    private void meterPreferencias(){
        preferencias=getSharedPreferences("nome",MODE_PRIVATE);
        nome=preferencias.getString("usuario","alberto_alvarez");
        contrasinal=preferencias.getString("contrasinal","abc123.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        meterPreferencias();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.idYoutube) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/sanclementetv")));
            return true;
        }

        if (id == R.id.idFacebook) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://gl-es.facebook.com/IES-San-Clemente-372154394748/")));
            return true;
        }

        if (id == R.id.idSair) {
            System.exit(1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
