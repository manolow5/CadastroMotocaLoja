package com.izi.cadastromotocaloja.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.izi.cadastromotocaloja.Model.Empresa;
import com.izi.cadastromotocaloja.R;

import java.io.Serializable;
import java.util.ArrayList;

public class CadastroLojaActivity extends AppCompatActivity {

    private TextInputEditText inputNomeProprietario;
    private TextInputEditText inputEmail;
    private TextInputEditText inputTelefone;
    private TextInputEditText inputCpf;
    private TextInputEditText inputCnpj;
    private Button btnContinuar;
    private Switch switchEscolha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_loja);
        inicializarComponentes();

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarTexto();
            }
        });

        switchEscolha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    inputCpf.setEnabled(false);
                    inputCnpj.setEnabled(true);
                }else {

                    inputCnpj.setEnabled(false);
                    inputCpf.setEnabled(true);

                }
            }
        });
    }


    private void recuperarTexto(){
        Empresa comerciante = new Empresa();


        String nomeProprietario = inputNomeProprietario.getText().toString();
        String telefone = inputTelefone.getText().toString();
        String email = inputEmail.getText().toString();
        String cnpj = inputCnpj.getText().toString();
        String cpf = inputCpf.getText().toString();
        //String senha = inputSenha.getText().toString();



        if(!nomeProprietario.isEmpty()){
            if(!telefone.isEmpty()){
                if(!email.isEmpty()){

                        if(switchEscolha.isChecked()){
                            if(!cnpj.isEmpty()){

                                comerciante.setNomeProprietario(nomeProprietario);
                                comerciante.setTelefone(telefone);
                                comerciante.setEmail(email);
                                comerciante.setCpf(cpf);
                                comerciante.setCnpj(cnpj);
                                Intent cadastro2 = new Intent(CadastroLojaActivity.this, CadastroPt2Activity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("comerciante",  comerciante);
                                cadastro2.putExtras(bundle);/*
                                cadastro2.putExtra("nomeProprietario",nomeProprietario);
                                cadastro2.putExtra("telefone",telefone);
                                cadastro2.putExtra("email",email);
                                cadastro2.putExtra("cpf",cpf);
                                cadastro2.putExtra("cnpj",cnpj);*/

                                startActivity(cadastro2);
                                finish();

                            }else {
                                Toast.makeText(this, "Preencha o campo CNPJ", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            if(!cpf.isEmpty()){

                                comerciante.setNomeProprietario(nomeProprietario);
                                comerciante.setTelefone(telefone);
                                comerciante.setEmail(email);
                                comerciante.setCpf(cpf);
                                comerciante.setCnpj(cnpj);


                                Intent cadastro2 = new Intent(CadastroLojaActivity.this, CadastroPt2Activity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("comerciante",  comerciante);
                                cadastro2.putExtras(bundle);/*
                                cadastro2.putExtra("nomeProprietario",nomeProprietario);
                                cadastro2.putExtra("telefone",telefone);
                                cadastro2.putExtra("email",email);
                                cadastro2.putExtra("cpf",cpf);
                                cadastro2.putExtra("cnpj",cnpj);
                                cadastro2.putExtra("senha",senha);
*/

                                startActivity(cadastro2);
                                finish();

                            }else {
                                Toast.makeText(this, "Preencha o campo CPF", Toast.LENGTH_SHORT).show();
                            }
                        }

                }else {
                    Toast.makeText(this, "Preencha o campo Email", Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(this, "Preencha o campo Telefone", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "Preencha o campo Nome do Proprietario", Toast.LENGTH_SHORT).show();
        }
    }


    private void inicializarComponentes(){

        inputNomeProprietario = findViewById(R.id.inputNomeProprietario);
        inputEmail = findViewById(R.id.inputEmail);
        inputTelefone = findViewById(R.id.inputTelefone);
        inputCpf = findViewById(R.id.inputCPF);
        inputCnpj = findViewById(R.id.inputCNPJ);
        btnContinuar = findViewById(R.id.btnContinuar);
        switchEscolha = findViewById(R.id.switchEscolha);
    }
}
