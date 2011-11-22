/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author itakenami
 */
public class Exemplo1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Fjalp2.getTerminal().limparTela();
        
        Fjalp2.getTerminal().setCor(CorFonte.VERDE, CorFundo.PRETO);
        Fjalp2.getTerminal().caixa(8, 28, 21, 4);
        
        Fjalp2.getTerminal().setPosicaoCursor(10, 30);
        Fjalp2.getTerminal().escreva("BEM VINDO AO FJALP2");
        
        Fjalp2.getTerminal().leiaTecla();
        
    }
     
    
}
