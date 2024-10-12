package br.com.fivetech.cm.modelo;

import br.com.fivetech.cm.excecao.ExplosaoException;

import java.util.ArrayList;
import java.util.List;

public class Campo {
    //codigo encapsulado
    private final int linha;
    private final int coluna;

    private boolean aberto = false;
    private boolean minado = false;
    private boolean marcado = false;

    private List<Campo> vizinhos = new ArrayList<>();

    Campo(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }

    boolean adcionarVizinho(Campo vizinho){
        //A  LOGICA DO VIZINHO VERTICAL/HORIZONTAL EH TER DIFERENCA DE 1
        //A LOGICA DO VIZINHO NA DIAGONAL EH TER DIFERENCA DE 2
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        //delta = distancia
        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaColuna + deltaLinha;

        //adicionar vizinho
        if (deltaGeral == 1 && !diagonal){
            vizinhos.add(vizinho);
            return true;
        } else if(deltaGeral == 2 && diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else {
            return false;
        }
    }

    //alternar marcacao
    void alternarMarcacao(){
        if (!aberto){
            marcado = !marcado;
        }
    }

    boolean abrir(){

        if (!aberto && !marcado){
            aberto = true;

            if (minado) {
                throw new ExplosaoException();
            }
            if (vizinhancaSegura()){
                vizinhos.forEach(v -> v.abrir());
            }
            return true;
        } else {
            return false;
        }
    }

    boolean vizinhancaSegura() {
        return vizinhos.stream().noneMatch(v -> v.minado); // true? = nenhunm viz esta minado
    }

    void minar(){
        minado = true;
    }

    public boolean isMinado(){
        return minado;
    }

    public boolean isMarcado() { //funciona como um get()
        return marcado;
    }

    //implementacao de explosao
     void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public boolean isAberto() {
        return aberto;
    }

    public boolean isFechado() {
        return !isAberto();
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    boolean objetivoAlcancado() {
        boolean desvendado = !minado && aberto;
        boolean peotegido = minado && marcado;
        return desvendado || peotegido;
    }

    //quantidade de minas na vinzinhanca
    long minasNaVizinhanca() {
        return vizinhos.stream().filter(v -> v.minado).count();
    }

    //reiniciar o jogo - resetar tudo
    void reiniciar(){
        aberto = false;
        minado = false;
        marcado = false;
    }

    public String toString(){
        if(marcado){
            return "x";
        } else if (aberto && minado) {
            return "*";
        } else if(aberto && minasNaVizinhanca() > 0){
            return Long.toString(minasNaVizinhanca());
        } else if (aberto) {
            return " ";
        } else {
            return "?";
        }
    }




}
