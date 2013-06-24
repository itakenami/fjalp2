
import java.util.Scanner;
import jline.ANSIBuffer;
import jline.Terminal;
import org.fusesource.jansi.AnsiConsole;

/**
 * A classe <b>Fjalp2</b> contem variáveis e métodos necessários para
 * a emulação de um terminal modo texto (padrão ANSI), capaz de prover
 * uma interface de linha de comando, com input de teclado e output
 * de caracteres em uma tela de console.
 * É possível a interação do usuário quando está no terminal, através 
 * de leitura e reconhecimento de teclas, armazenamento de variáveis do
 * tipo {@code int} (inteiro), {@code float} (real), {@code char}
 * (caractere), {@code String} (palavras) e outros. Assim como
 * a exibição de caracteres específicos, números, textos e caixas
 * desenhadas com caracteres do padrão ANSI, controle de posição do
 * cursor, cores diferenciadas e visual interativo.
 *
 *
 * @author Igor Takenami <itakenami@gmail.com>
 * 
 * @see CorFonte
 * @see CorFundo
 * @see Teclas
 * 
 */
public final class Fjalp2 {
    
    /**
     * Variável estática que será utilizada para aplicação do 
     * padrão de projeto <a href="http://pt.wikipedia.org/wiki/Singleton">Singleton</a>.
     */	
    private static Fjalp2 instance;
    
    private String COR_FUNDO;
    private String COR_FONTE;
    private String RESET;
    
    private int PX = -1;
    private int PY = -1;
    
    /**
     * Construtor da classe Fjalp2. 
     * Método construtor privado. Inicializa informações sobre o 
     * Sistema Operacional o qual está executando. Provê a emulação
     * de terminal através do método "systemInstall()", presente na
     * classe AnsiConsole.
     */
    private Fjalp2(){
        
        String SO = System.getProperty("os.name");
        
        if(SO.toUpperCase().indexOf("WIN")>=0){
            AnsiConsole.systemInstall();
        }
        
        RESET = ANSIBuffer.ANSICodes.attrib(0);
        resetCor();
    }
    
    /**
     * Retorna apenas uma única instância da classe <b>Fjalp2</b>.
     * Método público e estático do tipo {@code Fjalp2}.
     * Utiliza o padrão de projeto <a href="http://pt.wikipedia.org/wiki/Singleton">Singleton</a>,
     * protegendo o construtor e retornando o único objeto instanciado.
     *
     * @return A instância/objeto da classe
     *
     */
    public static Fjalp2 getTerminal(){
        if(instance==null){
            instance = new Fjalp2();
        }
        return instance;
    }
    
    /**
     * Reinicia todas as cores que foram definidas para a exibição do terminal.
     * Para definir uma cor de fundo, ou cor de frente, é necessário invocar um
     * dos seguintes métodos: {@code setCor}, {@code setCorFonte} ou {@code SetCorFundo}.
     * A cor padrão será reatribuída.
     */
    public void resetCor(){
        COR_FUNDO = ANSIBuffer.ANSICodes.attrib(40);
        COR_FONTE = ANSIBuffer.ANSICodes.attrib(47);
    }
    
    /**
     * Define a cor da fonte e do fundo (da fonte) ao mesmo tempo.
     * Ao chamar esse método, é possível definir cores para a fonte e o fundo do texto
     * que será exibido no console. Aceita dois parâmetros do tipo {@code int} (inteiro).
     *
     * @param fonte
     * 		A cor da fonte a ser exibida no terminal
     * @param fundo
     * 		A cor do fundo a ser exibido no terminal
     *
     * @see CorFonte
     * @see CorFundo
     */
    public void setCor(int fonte, int fundo){
        COR_FONTE = ANSIBuffer.ANSICodes.attrib(fonte);
        COR_FUNDO = ANSIBuffer.ANSICodes.attrib(fundo);
    }
    
     /**
     * Define a cor da fonte a ser exibida no terminal.
     * Ao chamar esse método, é possível definir uma cor para a fonte do texto
     * que será exibido no console. Aceita o parâmetro do tipo {@code int} (inteiro).
     *
     * @param fonte
     * 		A cor da fonte a ser exibida no terminal
     *
     * @see CorFonte
     */
    public void setCorFonte(int fonte){
        COR_FONTE = ANSIBuffer.ANSICodes.attrib(fonte);
    }
    
    /**
     * Define a cor do fundo a ser exibido no terminal.
     * Ao chamar esse método, é possível definir uma cor para o fundo do texto
     * que será exibido no console. Aceita o parâmetro do tipo {@code int} (inteiro).
     *
     * @param fundo
     * 		A cor do fundo a ser exibido no terminal
     *
     * @see CorFundo
     */ 
    public void setCorFundo(int fundo){
        COR_FUNDO = ANSIBuffer.ANSICodes.attrib(fundo);
    }
    
    /**
     * Escreve um valor na tela do terminal.
     * Exibe na tela do console informações contidas em um tipo {@code Object} (Objeto),
     * que pode ser um texto ou caractere. Utiliza informações de cores de fundo e fonte
     * já atribuídas pelo usuário.
     *
     * @param obj
     * 		Objeto que contém informações que serão exibidas na saída do terminal
     *
     */
    public void escreva(Object obj){
        System.out.print(COR_FONTE+COR_FUNDO+(obj)+RESET);
    }
    
    /**
     * Escreve um valor na tela do terminal, e em seguida aplica uma quebra de linha.
     * Exibe na tela do console informações contidas em um tipo {@code Object} (Objeto),
     * que pode ser um texto ou caractere. Utiliza informações de cores de fundo e fonte
     * já atribuídas pelo usuário.
     *
     * @param obj
     * 		Objeto que contém informações que serão exibidas na saída do terminal
     *
     */
    public void escrevaln(Object obj){
        System.out.println(COR_FONTE+COR_FUNDO+(obj)+RESET);
    }
    
    /**
     * Escreve um caractere na tela do terminal.
     * Exibe na tela do console um caractere específico do tipo {@code char} (caractere).
     * Utiliza informações de cores de fundo e fonte já atribuídas pelo usuário.
     *
     * @param c
     * 		Inteiro que representa caractere a ser exibido na saída do terminal
     *
     */
    public void escrevaChar(int c){
        System.out.print(COR_FONTE+COR_FUNDO+(char)c+RESET);
    }
    
    /**
     * Escreve um caractere repetidas vezes no terminal.
     * Exibe na tela do console um caractere específico do tipo {@code char} (caractere),
     * várias vezes de acordo com a quantidade informada por parâmetro.
     * Exemplo: Caso queira imprimir na tela 5 vezes o caractere 'a', deve-se usar:
     * <p><blockcode><pre>
     * 		Fjalp2.getTerminal().escreveChar((int) 97, 5);
     * 	</pre></blockcode>
     * Onde 97 é o valor do tipo {@code int} para o caractere 'a'.
     *
     * @param c
     * 		Inteiro que representa caractere a se exibido na saída do terminal
     *
     * @param qtd
     * 		Inteiro que define a quantidade de vezes que será exibido o caractere
     *
     */	
    public void escrevaChar(int c, int qtd){
        StringBuilder saida = new StringBuilder();
        for(int x=0;x<qtd;x++){
            saida.append((char)c);
        }
        System.out.print(COR_FONTE+COR_FUNDO+saida.toString()+RESET);
    }
    
    /**
     * Aguarda a entrada do teclado e faz a leitura de texto no terminal.
     * Armazena o texto ou caractere digitado até a presença da tecla ENTER e retorna 
     * uma variável do tipo {@code String} (texto).
     *
     * @return O texto que foi digitado pelo usuário
     * 
     */
    public String leiaString(){
        System.out.print(COR_FONTE+COR_FUNDO);
        String r = (new Scanner(System.in)).nextLine();
        System.out.print(RESET);
        return r;
    }
    
     /**
     * Aguarda a entrada do teclado e faz a leitura de número (decimal ou real) no terminal.
     * Armazena o número digitado até a presença da tecla ENTER e retorna
     * uma variável do tipo {@code double} (real).
     *
     * @return O número real ou inteiro que foi digitado pelo usuário
     *
     */ 
    public double leiaDouble(){
        System.out.print(COR_FONTE+COR_FUNDO);
        double r = (new Scanner(System.in)).nextDouble();
        System.out.print(RESET);
        return r;
    }
    
     /**
     * Aguarda a entrada do teclado e faz a leitura de um inteiro no terminal.
     * Armazena o número digitado até a presença da tecla ENTER e retorna
     * uma variável do tipo {@code int} (inteiro).
     *
     * @return O número inteiro que foi digitado pelo usuário
     *
     */
    public int leiaInt(){
        System.out.print(COR_FONTE+COR_FUNDO);
        int r = (new Scanner(System.in)).nextInt();
        System.out.print(RESET);
        return r;
    }
    
     /**
     * Aguarda a entrada do teclado no terminal.
     * Aguarda uma tecla qualquer a ser pressionada enquanto exibe uma mensagem
     * ou opção de menu no console. O retorno é utilizado para identificar uma 
     * tecla específica que foi pessionada pelo usuário.
     *
     * @return O código do caractere (tipo {@code int} (inteiro) que foi digitado
     *
     */
    public int leiaTecla(){
        
        try{
            int r = Terminal.getTerminal().readVirtualKey(System.in);
            Terminal.getTerminal().enableEcho();
            return r;
            
        }catch(Exception ex){
            throw new RuntimeException("Erro ao tenta capturar a tecla");
        }
        
    }
    
    /**
     * Limpa por completo a tela do terminal.
     * Apaga todos os caracteres do console, exibindo a tela padrão da aplicação.
     *
     */
    public void limparTela(){
        System.out.print(COR_FONTE+COR_FUNDO);
        System.out.print(ANSIBuffer.ANSICodes.clrscr());
        System.out.print(RESET);
        
    }
    
    /**
     * Determina a posição do cursor na tela do terminal.
     * Possibilita posicionar o cursor em determinada área no console, exibindo
     * ou não o cursor na posição indicada.
     *
     * @param x
     * 		Inteiro que representa a posição da linha
     *
     * @param y
     * 		Inteiro que representa a posição da coluna
     *
     * @param setar
     * 		Booleano que possibilita exibir o cursor do teclado no terminal
     *
     */
    private void setPosicaoCursor(int x, int y, boolean setar){
        if(setar){
            this.PX = x;
            this.PY = y;
        }
        System.out.print(ANSIBuffer.ANSICodes.gotoxy(x, y));
    }
    
    /**
     * Determina a posição do cursor na tela do terminal.
     * Exibe o cursor de teclado no console de acordo com a coordenada passada como parâmetro.
     *
     * @param x
     * 		Inteiro que representa a posição da linha
     *
     * @param y
     * 		Inteiro que representa a posição da coluna
     *
     */
    public void setPosicaoCursor(int x, int y){
        setPosicaoCursor(x, y, true);
    }
    
    /**
     * Desenha uma caixa na tela do terminal.
     * Exibe uma caixa desenhada no console de acordo com a coordenada/posição
     * e tamanho passado como parâmetro.
     * Ao definir posição e tamanho, uma caixa com seu limite é desenhada na tela.
     * 
     * @param x
     * 		Inteiro que determina a posição inicial da caixa na linha especificada
     *
     * @param y
     * 		Inteiro que determina a posição inicial da caixa na coluna especificada
     *
     * @param largura
     * 		Inteiro que define o limite da largura da caixa
     *
     * @param altura
     * 		Inteiro que define o limite da altura da caixa
     *
     */
    public void caixa(int x, int y, int largura, int altura){
        //largura++;
        
        //Ajusta a largura e altura máxima
        if(x<0){
            x=1;
        }

        if(y<0){
            y=1;
        }
        int MAX_ALTURA = Fjalp2.getTerminal().getJlineTerminal().getTerminalHeight()-2;
        int MAX_LARGURA = Fjalp2.getTerminal().getJlineTerminal().getTerminalWidth()-1;

        if((largura+x)>MAX_LARGURA){
            largura = MAX_LARGURA-x;
        }

        if((altura+y)>MAX_ALTURA){
            altura = MAX_ALTURA-y;
        }
        
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
    
    
    public void limparFundo(){
        System.out.print(COR_FONTE+COR_FUNDO);
        limpar(0, 0, Terminal.getTerminal().getTerminalWidth(), Terminal.getTerminal().getTerminalHeight());
        System.out.print(RESET);
        if(PX>-1 && PY>-1){
            setPosicaoCursor(PX, PX, false);
        }
        Terminal.getTerminal().enableEcho();
    }
    
     /**
     * Limpa um local especificado de acordo com a posição e tamanho.
     * Limpa os caracteres dentro de um espaço destinado a caixa a
     * partir da posição e tamanho (altura e largura) especificados
     * como parâmetro.
     *
     * @param x
     * 		Inteiro que determina a posição inicial da linha da caixa a limpar
     *
     * @param y
     *		Inteiro que determina a posição inicial da coluna da caixa a limpar
     *
     * @param largura
     * 		Inteiro que determina o limite da largura da caixa a limpar
     *
     * @param altura
     * 		Inteiro que determina o limite da altura da caixa a limpar
     *  
     */
    public void limpar(int x, int y, int largura, int altura){
        //largura++;
        
        //Ajusta a largura e altura máxima
        if(x<0){
            x=1;
        }

        if(y<0){
            y=1;
        }
        int MAX_ALTURA = Fjalp2.getTerminal().getJlineTerminal().getTerminalHeight()-2;
        int MAX_LARGURA = Fjalp2.getTerminal().getJlineTerminal().getTerminalWidth()-1;

        if((largura+x)>MAX_LARGURA){
            largura = MAX_LARGURA-x;
        }

        if((altura+y)>MAX_ALTURA){
            altura = MAX_ALTURA-y;
        }
        
        
        //Linhas horinzontais
        setPosicaoCursor(x, y, false);
        for(int z=0;z<altura;z++){
            for(int w=0;w<largura;w++){
                setPosicaoCursor(x+z, y+w, false);
                escreva(" ");
            }
        }
        
    }
    
    /**
     * Aguarda um tempo específico, parando a atividade da aplicação por um momento.
     * Ao definir uma quantidade de tempo do tipo {@code int} (inteiro), a aplicação
     * aguarda esse tempo em estado de espera, e só é liberada após consumir o tempo
     * parametrizado na função. Produz um erro caso não seja possível aguardar, ou
     * for interrompido abruptamente.
     *
     * @param segundos
     * 		Quantidade de tempo do tipo {@code int} (inteiro) que define o tempo
     * 		a permanecer em estado de espera
     *
     * @throws RuntimeException
     * 		Se a Thread for interrompida a exceção é gerada
     *
     */
    public void aguardar(int segundos){
        try {
            Thread.sleep(segundos*1000);
        } catch (InterruptedException ex) {
            throw new RuntimeException("Erro ao tentar esperar");
        }
    }
    
     /**
     * Retorna um terminal quando chamado.
     * Chama o emulador de terminal capaz de exibir mensagens e 
     * aceitar entradas do usuário.
     *
     * @return Terminal do tipo {@code Terminal}
     *
     */
    public Terminal getJlineTerminal(){
        return Terminal.getTerminal();
    }
    
}
