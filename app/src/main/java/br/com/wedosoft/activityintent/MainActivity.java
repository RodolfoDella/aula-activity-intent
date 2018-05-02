package br.com.wedosoft.activityintent;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "Aula";
    private final int SIMULAR_CHAMADA = 0;
    private final int PASSAR_PARAMETRO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ouvinte();
        gerarLogs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_webview) {
            Intent it = new Intent(MainActivity.this, WebVieww.class);
            startActivity(it);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ouvinte() {
        parametro();
        alertaDialog();
        abrirBrowser();
        simularChamada();
    }

    private void parametro() {
        Button parametro = (Button) findViewById(R.id.bt_parametro);
        parametro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                entradaTexto(PASSAR_PARAMETRO);
            }
        });
    }

    private void alertaDialog() {
        Button alerta = (Button) findViewById(R.id.bt_entradaTexto);
        alerta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                entradaTexto(SIMULAR_CHAMADA);
            }
        });
    }

    private void simularChamada() {
        Button chamada = (Button) findViewById(R.id.bt_simularChamada);
        chamada.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            public void onClick(View v) {
                String url = "tel:99133-4640";
                Intent it = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                startActivity(it);
            }
        });
    }

    private void abrirBrowser() {
        Button browser = (Button) findViewById(R.id.bt_abrirBrowser);
        browser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.wedosoft.com.br");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });
    }
    protected void entradaTexto(final int acao) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT);

        alert.setMessage("Entrada de Texto..")
                .setView(input);

        alert.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String mensagemUsuario = input.getText().toString().trim();
                if (acao == SIMULAR_CHAMADA) alertaToast(mensagemUsuario);
                else if (acao == PASSAR_PARAMETRO) passarParametro(mensagemUsuario);
            }
        });

        alert.setNegativeButton("Cancelar",	new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        alert.show();
    }

    private void alertaToast(String mensagemUsuario) {
        Toast.makeText(MainActivity.this, mensagemUsuario, Toast.LENGTH_LONG).show();
    }

    private void passarParametro(String mensagemUsuario){
        Intent it = new Intent(MainActivity.this, Parametros.class);
        Bundle params = new Bundle();
        params.putString("msg", mensagemUsuario);
        it.putExtras(params);
        startActivity(it);
    }

    private void gerarLogs() {
        Log.v(TAG, "Log de Verbose");
        Log.d(TAG, "Log de Debug");
        Log.i(TAG, "Log de Info");
        Log.w(TAG, "Log de Warn");
        Log.e(TAG, "Log de Error");
    }

}
