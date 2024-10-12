package br.com.fivetech.cm.modelo;

import br.com.fivetech.cm.excecao.ExplosaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CampoTest {

    private Campo campo = new Campo(3,3);

    @BeforeEach
    void iniciarCampo(){
        campo = new Campo(3,3);
    }

    @Test // import api
    void testeVizinhoHorizontal(){
        // <- ->
        Campo vizinhoEsquerdo = new Campo(3,2);
        boolean resultadoEsquerdo = campo.adcionarVizinho(vizinhoEsquerdo);

        Campo vizinhoDireito = new Campo(3,4);
        boolean resultadoDireito = campo.adcionarVizinho(vizinhoDireito);

        assertTrue(resultadoEsquerdo);
        assertTrue(resultadoDireito);
    }

    @Test // import api
    void testeVizinhoVertical(){
        // cima // baixo
        Campo vizinhoEmCima = new Campo(2,3);
        boolean resultadoEmCima = campo.adcionarVizinho(vizinhoEmCima);

        Campo vizinhoEmBaixo = new Campo(4,3);
        boolean resultadoEmBaixo = campo.adcionarVizinho(vizinhoEmBaixo);

        assertTrue(resultadoEmCima);
        assertTrue(resultadoEmBaixo);
    }

    @Test
    void testeVizinhoDistancia2(){
        Campo vizinho = new Campo(2,2);
        boolean resultadoD2 = campo.adcionarVizinho(vizinho);
        assertTrue(resultadoD2);
    }

    @Test
    void testeNaoVizinho(){
        Campo vizinho = new Campo(1,1);
        boolean resultado = campo.adcionarVizinho(vizinho);
        assertFalse(resultado);
    }

    @Test
    void testeValorPadraoAtributoMarcado() {
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacao() {
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacaoDuasChamadas() {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirNaoMinadoMarcado() {
        assertTrue(campo.abrir());
    }

    @Test
    void testeAbrirMinadoMarcado() {
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoNaoMarcado() {
        campo.minar();

        assertThrows(ExplosaoException.class, () ->{
            campo.abrir();
        });
    }

    @Test
    void testeAbrirComVizinhos1() {

        Campo campo11 = new Campo(1,1);

        Campo campo22 = new Campo(2,2);
        campo22.adcionarVizinho(campo11);

        campo.adcionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    void testeAbrirComVizinhos2() {

        Campo campo11 = new Campo(1,1);
        Campo campo12 = new Campo(1,1);
        campo12.minar();

        Campo campo22 = new Campo(2,2);
        campo22.adcionarVizinho(campo11);
        campo22.adcionarVizinho(campo12);

        campo.adcionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isFechado());
    }




























}