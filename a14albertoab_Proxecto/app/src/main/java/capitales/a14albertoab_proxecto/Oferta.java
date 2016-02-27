package capitales.a14albertoab_proxecto;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ZoomControls;

public class Oferta extends Activity {

    Spinner spinner;
    ImageView imaxe;
    ZoomControls zoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta);

        spinner=(Spinner) findViewById(R.id.idSpinner);
        imaxe=(ImageView) findViewById(R.id.idImaxe);
        zoom=(ZoomControls) findViewById(R.id.zoomControls1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cambiarImaxe(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imaxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imaxe.setAdjustViewBounds(true);
            }
        });

        zoom.setOnZoomInClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                float x = imaxe.getScaleX();
                float y = imaxe.getScaleY();

                imaxe.setScaleX((float) (x+1));
                imaxe.setScaleY((float) (y+1));
            }
        });

        zoom.setOnZoomOutClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                float x = imaxe.getScaleX();
                float y = imaxe.getScaleY();

                imaxe.setScaleX((float) (x-1));
                imaxe.setScaleY((float) (y-1));
            }
        });

    }

    public void cambiarImaxe(int numero){
        switch (numero){
            case 0:
                imaxe.setImageDrawable(getResources().getDrawable(R.mipmap.dam));
                break;
            case 1:
                imaxe.setImageDrawable(getResources().getDrawable(R.mipmap.smr));
                break;
            case 2:
                imaxe.setImageDrawable(getResources().getDrawable(R.mipmap.asir));
                break;
            case 3:
                imaxe.setImageDrawable(getResources().getDrawable(R.mipmap.daw));
                break;
            case 4:
                imaxe.setImageDrawable(getResources().getDrawable(R.mipmap.asirm));
                break;
            default:
                imaxe.setImageDrawable(getResources().getDrawable(R.mipmap.dam));
                break;
        }
    }
}
