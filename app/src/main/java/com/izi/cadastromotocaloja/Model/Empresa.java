package com.izi.cadastromotocaloja.Model;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.FirebaseFirestore;
import java.io.Serializable;

public class Empresa implements Serializable {

    private String uid;
    private String nomeEmpresa;
    private String nomeProprietario;
    private String telefone;
    private String email;
    private String categoria;
    private String descricao;
    private String idUsuario;
    private String senha;
    private String tempo;
    private String taxa;
    private String cpf;
    private String cnpj;
    private Double precoEntrega;
    private Localizacao localizacao;
    private String urlImagem;


    public Empresa() {
    }

    public void salvar(){
        FirebaseAuth usuario = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        setUid(usuario.getUid());
        db.collection("Lojas").document(getUid()).set(this);


    }



    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }


    public  String getDescricao() {
        return  descricao;
    }

    public void setDescricao(){
        this.descricao =descricao;
    }

    public String getTaxa(){
        return taxa;
    }

    public void setTaxa(){
        this.taxa = taxa;
    }


    public String getTempo(){
        return tempo;
    }

    public void setTempo(String tempo){
        this.tempo = this.tempo;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }



    public String getUrlImagem() {
        return urlImagem;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Double getPrecoEntrega() {
        return precoEntrega;
    }

    public void setPrecoEntrega(Double precoEntrega) {
        this.precoEntrega = precoEntrega;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }


}
