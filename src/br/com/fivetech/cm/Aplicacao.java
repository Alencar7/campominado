package br.com.fivetech.cm;

import br.com.fivetech.cm.modelo.Tabuleiro;
import br.com.fivetech.cm.visao.TabuleiroConsole;

public class Aplicacao {

    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro(6,6,3);
        new TabuleiroConsole(tabuleiro);
    }
}
