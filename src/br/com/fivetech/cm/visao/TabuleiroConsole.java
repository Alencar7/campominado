package br.com.fivetech.cm.visao;

import br.com.fivetech.cm.excecao.ExplosaoException;
import br.com.fivetech.cm.excecao.SairException;
import br.com.fivetech.cm.modelo.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {

    private Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);


    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;

        executarJogo();
    }

    private void executarJogo(){
        try {
            boolean continuar = true;
            while (continuar){
                cicloDoJogo();

                System.out.println("Outra partida? (S/N)");
                String resposta = entrada.nextLine();

                if ("N".equalsIgnoreCase(resposta)){
                    continuar = false;
                } else {
                    tabuleiro.reinicializar();
                }
            }
        } catch (SairException e){
            System.out.println("Tchau!!!");
        } finally {
            entrada.close();
        }
    }

    private void cicloDoJogo() {
        try {

            while (!tabuleiro.objetivoAlcancado()){
                System.out.println(tabuleiro);

                String digitado = capturarValorDigitado("Digite (x.linha, y.coluna): ");

                //transformando elementos do tipo string(array) em integer
                //digitar um valor, quebrar com virgula e pegar os valores num
                Iterator<Integer> xy = Arrays.stream(digitado.split(","))
                        .map(e -> Integer.parseInt(e.trim()))
                        .iterator();

                digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar:");

                if("1".equals(digitado)){
                    tabuleiro.abrir(xy.next(), xy.next());
                } else if ("2".equals(digitado)){
                    tabuleiro.alterarMarcacao(xy.next(), xy.next());
                }
            }
            System.out.println("Parabens!!!");
            System.out.println("Voce ganhou!");
            System.out.println(tabuleiro);
            System.out.println("\n");

        } catch (ExplosaoException e ) {            //A EXPLOSAO TA AQUI
            System.out.println("Ih... DEU RUIM!");
            System.out.println(tabuleiro);
            System.out.println("Voce perdeu!");
        }
    }

    private String capturarValorDigitado(String texto) {
        System.out.print(texto);
        String digitado =  entrada.nextLine();

        if ("sair".equalsIgnoreCase(digitado)){
            throw new SairException();
        }
        return digitado;
    }
}
