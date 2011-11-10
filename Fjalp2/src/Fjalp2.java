
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jline.ANSIBuffer;
import jline.ConsoleReader;
import jline.Terminal;
import org.fusesource.jansi.AnsiConsole;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author itakenami
 */
public final class Fjalp2 {
    
    private static Fjalp2 instance;
    
    private String COR_FUNDO;
    private String COR_FONTE;
    private String RESET;
    
    private int PX = -1;
    private int PY = -1;
    
    private Fjalp2(){
        
        String SO = System.getProperty("os.name");
        
        if(SO.toUpperCase().indexOf("WIN")>=0){
            AnsiConsole.systemInstall();
        }
        
        RESET = ANSIBuffer.ANSICodes.attrib(0);
        resetCor();
    }
    
    public static Fjalp2 getTerminal(){
        if(instance==null){
            instance = new Fjalp2();
        }
        return instance;
    }
    
    public void resetCor(){
        COR_FUNDO = ANSIBuffer.ANSICodes.attrib(40);
        COR_FONTE = ANSIBuffer.ANSICodes.attrib(47);
    }
    
    public void setCor(int fonte, int fundo){
        COR_FONTE = ANSIBuffer.ANSICodes.attrib(fonte);
        COR_FUNDO = ANSIBuffer.ANSICodes.attrib(fundo);
    }
    
    public void setCorFonte(int fonte){
        COR_FONTE = ANSIBuffer.ANSICodes.attrib(fonte);
    }
    
    public void setCorFundo(int fundo){
        COR_FUNDO = ANSIBuffer.ANSICodes.attrib(fundo);
    }
    
    public void escreva(Object obj){
        System.out.print(COR_FONTE+COR_FUNDO+(obj)+RESET);
    }
    
    public void escrevaln(Object obj){
        System.out.println(COR_FONTE+COR_FUNDO+(obj)+RESET);
    }
    
    public void escrevaChar(int c){
        System.out.print(COR_FONTE+COR_FUNDO+(char)c+RESET);
    }
    
    public void escrevaChar(int c, int qtd){
        StringBuilder saida = new StringBuilder();
        for(int x=0;x<qtd;x++){
            saida.append((char)c);
        }
        System.out.print(COR_FONTE+COR_FUNDO+saida.toString()+RESET);
    }
    
    public String leiaString(){
        System.out.print(COR_FONTE+COR_FUNDO);
        String r = (new Scanner(System.in)).nextLine();
        System.out.print(RESET);
        return r;
    }
    
    public double leiaDouble(){
        System.out.print(COR_FONTE+COR_FUNDO);
        double r = (new Scanner(System.in)).nextDouble();
        System.out.print(RESET);
        return r;
    }
    
    public int leiaInt(){
        System.out.print(COR_FONTE+COR_FUNDO);
        int r = (new Scanner(System.in)).nextInt();
        System.out.print(RESET);
        return r;
    }
    
    public int leiaTecla(){
        
        try{
            int r = Terminal.getTerminal().readVirtualKey(System.in);
            Terminal.getTerminal().enableEcho();
            return r;
            
        }catch(Exception ex){
            throw new RuntimeException("Erro ao tenta capturar a tecla");
        }
        
    }
    
    public void limparTela(){
        System.out.print(COR_FONTE+COR_FUNDO);
        System.out.print(ANSIBuffer.ANSICodes.clrscr());
        limpar(0, 0, Terminal.getTerminal().getTerminalWidth(), Terminal.getTerminal().getTerminalHeight());
        System.out.print(RESET);
        if(PX>-1 && PY>-1){
            setPosicaoCursor(PX, PX, false);
        }
        
    }
    
    private void setPosicaoCursor(int x, int y, boolean setar){
        if(setar){
            this.PX = x;
            this.PY = y;
        }
        System.out.print(ANSIBuffer.ANSICodes.gotoxy(x, y));
    }
    
    public void setPosicaoCursor(int x, int y){
        setPosicaoCursor(x, y, true);
    }
    
    public void caixa(int x, int y, int largura, int altura){
        largura++;
        //Linhas horinzontais
        setPosicaoCursor(x, y);
        escrevaChar(205, largura);
        setPosicaoCursor(x+altura, y);
        escrevaChar(205, largura);
        
        //Linhas verticais
        
        for(int z=0;z<altura;z++){
            setPosicaoCursor(x+z, y);
            escrevaChar(186);
            setPosicaoCursor(x+z, y+largura);
            escrevaChar(186);
        }
        
        setPosicaoCursor(x, y);
        escrevaChar(201);
        setPosicaoCursor(x, y+largura);
        escrevaChar(187);
        setPosicaoCursor(x+altura, y);
        escrevaChar(200);
        setPosicaoCursor(x+altura, y+largura);
        escrevaChar(188);
        
    }
    
    public void limpar(int x, int y, int largura, int altura){
        largura++;
        //Linhas horinzontais
        setPosicaoCursor(x, y, false);
        for(int z=0;z<altura;z++){
            for(int w=0;w<largura;w++){
                setPosicaoCursor(x+z, y+w, false);
                escreva(" ");
            }
        }
        
    }
    
    public void aguardar(int segundos){
        try {
            Thread.sleep(segundos*1000);
        } catch (InterruptedException ex) {
            throw new RuntimeException("Erro ao tentar esperar");
        }
    }
    
    public Terminal getJlineTerminal(){
        return Terminal.getTerminal();
    }
    
}
