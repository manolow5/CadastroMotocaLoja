package com.izi.cadastromotocaloja.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.izi.cadastromotocaloja.Model.Empresa;
import com.izi.cadastromotocaloja.Model.Localizacao;
import com.izi.cadastromotocaloja.R;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class CadastroPt2Activity extends AppCompatActivity {

    private Intent iCadastro;
    private Empresa empresa = new Empresa();
    private PlacesClient placesClient;
    private Localizacao localizacao = new Localizacao();
    private Button btnFinalizarCadastro;

    private Spinner spinnerCategoria;
    private TextInputEditText inputNomeEmpresa;
    private FirebaseAuth mAuth;
    private TextInputEditText inputSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pt2);

        inicializarComponentes();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Categorias, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter);
        autoComplete();

        btnFinalizarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                salvarComerciantePt2();

            }
        });
    }

    private void salvarComerciantePt2() { //save store object

        if (localizacao != null) {
          //  String categoria = spinnerCategoria.getSelectedItem().toString();//geting a category
          //  if(categoria == null){
                empresa.setNomeEmpresa(inputNomeEmpresa.getText().toString());
                empresa.setLocalizacao(localizacao);
               // empresa.setCategoria(categoria);
                empresa.setSenha(inputSenha.getText().toString());


                criaUsuarioFirebase(empresa.getEmail(), inputSenha.getText().toString());// create firebase user
         //   }




        }
    }


    private void autoComplete() {

        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("PlaceCerto", "Place: " + place.getName() + ", " + place.getId());
                Geocoder geocoder = new Geocoder(CadastroPt2Activity.this, Locale.getDefault());
                try {
                    List<Address> listaEnderecos = geocoder.getFromLocationName(place.getName(), 1);
                    if(listaEnderecos != null && listaEnderecos.size() >0){
                        Address address = listaEnderecos.get(0);
                        localizacao.setLatitude(place.getLatLng().latitude); // save latitude
                        localizacao.setLongitude(place.getLatLng().longitude); // save longitude
                        salvaLocal(address);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("PlaceErrado", "An error occurred: " + status);
            }
        });
    }

    private void salvaLocal(Address address){// save place

        localizacao.setEstado(address.getAdminArea());
        localizacao.setCidade(address.getCountryName());
        localizacao.setCep(address.getPostalCode());
        localizacao.setBairro(address.getSubLocality());
        localizacao.setRua(address.getThoroughfare());
        localizacao.setNumero(address.getFeatureName());
    }


    private void criaUsuarioFirebase (String email, String senha){

        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d("TagCerta", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            empresa.salvar(); //writing values
                            Intent inicio = new Intent(CadastroPt2Activity.this, FinalizadoActivity.class);
                            String categoria = spinnerCategoria.getSelectedItem().toString();
                            inicio.putExtra("categoria", categoria);
                            startActivity(inicio);
                            finish();


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TagErrada", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CadastroPt2Activity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    private void inicializarComponentes(){



        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "AIzaSyCVA5Z6ZpycRP-NPtyKjvBbUSKXJ6aiD70");
        }
        placesClient = Places.createClient(this);


        spinnerCategoria = findViewById(R.id.spinnerCategoria);

        iCadastro =  getIntent();
        empresa = (Empresa) iCadastro.getSerializableExtra("comerciante");
       // Log.d("NomeLoja",empresa.get());

        btnFinalizarCadastro = findViewById(R.id.btnFinalizarCadastro);

        inputSenha = findViewById(R.id.inputSenha);
        inputNomeEmpresa = findViewById(R.id.inputNomeEmpresa);

        mAuth = FirebaseAuth.getInstance();
    }
}
