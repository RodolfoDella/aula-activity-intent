package br.com.wedosoft.activityintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Parametros extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parametros);

		Intent it = getIntent(); 
		if(it != null){ 
			Bundle params = it.getExtras(); 
			if(params != null){ 
				String mensagemUsuario = params.getString("msg");

				TextView text = (TextView) findViewById(R.id.tx_parametro);
				text.setText(mensagemUsuario);
			} 
		} 
	}

}
