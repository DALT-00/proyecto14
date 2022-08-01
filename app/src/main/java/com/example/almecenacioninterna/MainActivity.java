package com.example.almecenacioninterna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText fecha, datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fecha = (EditText) findViewById(R.id.edit_fecha);
        datos = (EditText) findViewById(R.id.edit_resultado);
    }

    //Metodo para el boton para guadar
    public void Guardar(View v){
        String nomarchivo = fecha.getText().toString();
        nomarchivo=nomarchivo.replace('/','-');
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(nomarchivo,MODE_PRIVATE));
            archivo.write(datos.getText().toString());
            archivo.flush();
            archivo.close();

        }catch (IOException e){
        }
        Toast.makeText(this,"Los datos fueron grabados",Toast.LENGTH_LONG).show();
        fecha.setText("");
        datos.setText("");
    }

    public void Recuperar(View v){
        String nomarchivo = fecha.getText().toString();
        nomarchivo=nomarchivo.replace('/','-');
        boolean enco=false;
        String archivos [] = fileList();
        for (int i=0; i<archivos.length;i++){
            if (nomarchivo.equals(archivos[i]));
            enco=true;
        }
        if (enco==true){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput(nomarchivo));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                while(linea!=null){
                    todo=todo+linea+"\n";
                    linea=br.readLine();
                }
                br.close();
                archivo.close();
                datos.setText(todo);

            }catch (IOException e){

            }
        }else {
            Toast.makeText(this,"No hay datos grabados para dicha fecha",Toast.LENGTH_LONG).show();
            datos.setText("");
        }
    }
}