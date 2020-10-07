package com.raulcidlimon.conversordedivisas;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    CurrencyAdapter currencyAdapter;

    ImageButton ivInfo;
    Spinner spinnerBase;
    Spinner spinnerACambiar;
    ImageButton ibCambiarValores;
    private TextView tvFecha;
    private TextView tvOneAToXB;
    private EditText etDivisaBase;
    private EditText etDivisaACambiar;

    private String divisaBase;
    private String divisaCambiada;
    private ArrayList<CurrencyItems> currencyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerModificar();
        actualizadorTexto();

        currencyAdapter = new CurrencyAdapter(this, currencyList);
        spinnerBase = findViewById(R.id.spinnerBase);
        spinnerBase.setAdapter(currencyAdapter);
        spinnerACambiar = findViewById(R.id.spinnerACambiar);
        spinnerACambiar.setAdapter(currencyAdapter);

        spinnerBase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CurrencyItems itemSelected = (CurrencyItems) parent.getItemAtPosition(position);
                divisaBase = itemSelected.getCurrencyName();
                getResultado();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerACambiar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CurrencyItems itemSelected = (CurrencyItems) parent.getItemAtPosition(position);
                divisaCambiada = itemSelected.getCurrencyName();
                getResultado();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ivInfo = findViewById(R.id.ivInfo);
        ivInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });

        ibCambiarValores = findViewById(R.id.ibCambiarValores);
        ibCambiarValores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarValores();
            }
        });
    }

    private void cambiarValores() {
        if (!etDivisaACambiar.getText().toString().equalsIgnoreCase("")) {
            int spinnerDivisaAitem = spinnerBase.getSelectedItemPosition();
            int spinnerDivisaBitem = spinnerACambiar.getSelectedItemPosition();
            etDivisaBase.setText(etDivisaACambiar.getText().toString().
                    replaceAll(",", ""));
            spinnerBase.setSelection(spinnerDivisaBitem);
            spinnerACambiar.setSelection(spinnerDivisaAitem);
        } else {
            etDivisaBase.setText("");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void spinnerModificar() {
        currencyList = new ArrayList<>();
        currencyList.add(new CurrencyItems("EUR", R.drawable.uno_eur_flag));
        currencyList.add(new CurrencyItems("GBP", R.drawable.dos_gbp_flag));
        currencyList.add(new CurrencyItems("HKD", R.drawable.tres_hkd_flag));
        currencyList.add(new CurrencyItems("IDR", R.drawable.cuatro_idr_flag));
        currencyList.add(new CurrencyItems("ILS", R.drawable.cinco_ils_flag));
        currencyList.add(new CurrencyItems("DKK", R.drawable.seis_dkk_flag));
        currencyList.add(new CurrencyItems("INR", R.drawable.siete_inr_flag));
        currencyList.add(new CurrencyItems("CHF", R.drawable.ocho_chf_flag));
        currencyList.add(new CurrencyItems("MXN", R.drawable.nueve_mxn_flag));
        currencyList.add(new CurrencyItems("CZK", R.drawable.diez_czk_flag));
        currencyList.add(new CurrencyItems("SGD", R.drawable.once_sgd_flag));
        currencyList.add(new CurrencyItems("THB", R.drawable.doce_thb_flag));
        currencyList.add(new CurrencyItems("HRK", R.drawable.trece_hrk_flag));
        currencyList.add(new CurrencyItems("MYR", R.drawable.catorce_myr_flag));
        currencyList.add(new CurrencyItems("NOK", R.drawable.quince_nok_flag));
        currencyList.add(new CurrencyItems("CNY", R.drawable.dieciseis_cny_flag));
        currencyList.add(new CurrencyItems("BGN", R.drawable.diecisiete_bgn_flag));
        currencyList.add(new CurrencyItems("PHP", R.drawable.dieciocho_php_flag));
        currencyList.add(new CurrencyItems("SEK", R.drawable.diecinueve_sek_flag));
        currencyList.add(new CurrencyItems("PLN", R.drawable.veinte_pln_flag));
        currencyList.add(new CurrencyItems("ZAR", R.drawable.veintiuno_zar_flag));
        currencyList.add(new CurrencyItems("CAD", R.drawable.veintidos_cad_flag));
        currencyList.add(new CurrencyItems("ISK", R.drawable.veintitres_isk_flag));
        currencyList.add(new CurrencyItems("BRL", R.drawable.veinticuatro_brl_flag));
        currencyList.add(new CurrencyItems("RON", R.drawable.veinticinco_ron_flag));
        currencyList.add(new CurrencyItems("NZD", R.drawable.veintiseis_nzd_flag));
        currencyList.add(new CurrencyItems("TRY", R.drawable.veintisiete_try_flag));
        currencyList.add(new CurrencyItems("RUB", R.drawable.veintiocho_rub_flag));
        currencyList.add(new CurrencyItems("KRW", R.drawable.veintinueve_krw_flag));
        currencyList.add(new CurrencyItems("USD", R.drawable.treinta_usd_flag));
        currencyList.add(new CurrencyItems("HUF", R.drawable.treintiuno_huf_flag));
        currencyList.add(new CurrencyItems("AUD", R.drawable.treintidos_aud_flag));
    }

    private void actualizadorTexto() {
        etDivisaBase = findViewById(R.id.etDivisaBase);
        etDivisaACambiar = findViewById(R.id.etDivisaACambiar);
        etDivisaACambiar.setEnabled(false);

        etDivisaBase.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("Main", "e");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    getResultado();
                } catch (Exception e) {
                    Log.d("Main", "On text changed");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    if (s.toString().length() == 1 && s.toString().startsWith("0")) {
                        s.clear();
                    }
                } catch (Exception e) {
                    Log.d("Main", "e");
                }
            }
        });
    }

    private void getResultado() {
        tvFecha = findViewById(R.id.tvFecha);
        tvOneAToXB = findViewById(R.id.tvOneAToXB);
        etDivisaBase = findViewById(R.id.etDivisaBase);
        etDivisaACambiar = findViewById(R.id.etDivisaACambiar);

        if (isNetwork()) {
            if (comprobarEditText(etDivisaBase)) {
                String apiUrl = "https://api.ratesapi.io/api/latest?base="
                        + divisaBase + "&symbols=" + divisaCambiada;

                if (divisaBase.equalsIgnoreCase(divisaCambiada)) {
                    etDivisaACambiar.setText(String.valueOf(
                            Double.parseDouble(etDivisaBase.getText().toString())));
                    tvOneAToXB.setText("");
                    tvOneAToXB.setText("");

                } else {
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                            Request.Method.GET, apiUrl, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        if (comprobarEditText(etDivisaBase)) {
                                            JSONObject jsonObject = response.
                                                    getJSONObject("rates");
                                            double valorETBase = Double.parseDouble(
                                                    etDivisaBase.getText().toString());
                                            double valorFinal = valorETBase *
                                                    jsonObject.getDouble(divisaCambiada);

                                            String unoBaseToXACambiar = "1 " + divisaBase
                                                    + " = " + jsonObject.getDouble(divisaCambiada)
                                                    + " " + divisaCambiada;
                                            tvOneAToXB.setText(unoBaseToXACambiar);

                                            etDivisaACambiar.setText(String.valueOf(
                                                    valorFinal));

                                            String textoFromJson = response.getString("date");
                                            SimpleDateFormat dateFormatYMD = new SimpleDateFormat(
                                                    "yyyy-MM-dd", Locale.getDefault());

                                            Date fechaInicial = dateFormatYMD.parse(textoFromJson);
                                            SimpleDateFormat dateFormatDMY = new SimpleDateFormat(
                                                    "dd-MM-yyyy", Locale.getDefault());

                                            String fechaFinal = dateFormatDMY.format(fechaInicial);
                                            tvFecha.setText(fechaFinal);
                                        }
                                    } catch (JSONException | ParseException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    RequestQueue queue = Volley.newRequestQueue(this);
                    queue.add(jsonObjectRequest);
                }
            } else {
                etDivisaACambiar.setText("");
            }
        } else {
            calculoSinInternet();
        }
    }


    private boolean isNetwork() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    // TODO
    private void calculoSinInternet() {
        if (!divisaBase.equalsIgnoreCase(divisaCambiada)) {
            etDivisaACambiar.setText(R.string.NoInternet);

        } else {
            etDivisaACambiar.setText(etDivisaBase.getText().toString());
        }
    }

    private boolean comprobarEditText(EditText editText) {

        return editText != null
                && !editText.getText().toString().isEmpty()
                && !editText.getText().toString().equalsIgnoreCase("")
                && !editText.getText().toString().equalsIgnoreCase(".");

    }

}

