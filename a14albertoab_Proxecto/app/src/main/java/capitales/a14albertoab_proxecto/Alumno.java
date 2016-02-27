package capitales.a14albertoab_proxecto;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Alumno extends AppCompatActivity {

    AlertDialog.Builder venta;
    private TextView etNome, etContrasinal;
    private SharedPreferences preferencias;
    private static String nome, contrasinal;
    private ImageView imaxe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);
        preferencias=getSharedPreferences("nome",MODE_PRIVATE);
        imaxe=(ImageView) findViewById(R.id.idHorario);
        imaxe.setImageDrawable(getResources().getDrawable(R.drawable.horario_clase));
    }

    private void chamarLogin(int num){
        showDialog(num);
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 2:
                String infService1 = Context.LAYOUT_INFLATER_SERVICE;
                LayoutInflater li1 = (LayoutInflater) getApplicationContext().getSystemService(infService1);
                // Inflamos o compoñente composto definido no XML
                View inflador1 = li1.inflate(R.layout.login, null);
                // Buscamos os compoñentes dentro do Diálogo

                TextView txt11=(TextView) inflador1.findViewById(R.id.txtNome);
                TextView txt21=(TextView) inflador1.findViewById(R.id.txtContra);

                etNome = (TextView) inflador1.findViewById(R.id.idUsuario);
                etContrasinal = (TextView) inflador1.findViewById(R.id.idContrasinal);

                venta = new AlertDialog.Builder(this);
                venta.setTitle("Indica o novo usuario e contrasinal");
                // Asignamos o contido dentro do diálogo (o que inflamos antes)
                venta.setView(inflador1);
                // Non se pode incluír unha mensaxe dentro deste tipo de diálogo!!!
                venta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int boton) {
                        cambiarContrasinal();
                    }
                });
                venta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int boton) {
                        Toast.makeText(getApplicationContext(),"Non se cambiaron os datos",Toast.LENGTH_SHORT).show();
                    }
                });
                return venta.create();
        }
        return null;
    }

    private void cambiarContrasinal(){
        SharedPreferences.Editor editor= preferencias.edit();
        editor.putString("usuario", etNome.getText().toString());
        editor.putString("contrasinal", etContrasinal.getText().toString());
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_horario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.idCambiarContrasinal) {
            chamarLogin(2);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
