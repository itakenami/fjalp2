/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author itakenami
 */
public class Exemplo {
    public static void main(String[] args) {
        
        Fjalp2.getTerminal().limparTela();
        Fjalp2.getTerminal().setPosicaoCursor(10, 10);
        Fjalp2.getTerminal().setCor(32, 40);
        Fjalp2.getTerminal().escreva("Bem Vindo ao Fjalp2");
        Fjalp2.getTerminal().leiaTecla();
       
    }
}
