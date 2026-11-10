package hotel.negocio;

import hotel.modelo.*;
import java.util.ArrayList;

public class Hotel {
    public static final int NUM_ANDARES = 20;
    public static final int APTOS_POR_ANDAR = 14;

    private Apartamento[][] matriz;
    private ArrayList<Servico> servicos;
    private ArrayList<Consumo> consumos;

    public Hotel() {
        this.matriz = new Apartamento[NUM_ANDARES][APTOS_POR_ANDAR];
        this.servicos = new ArrayList<>();
        this.consumos = new ArrayList<>();
        inicializar();
    }

    private void inicializar() {
        for (int a = 0; a < NUM_ANDARES; a++) {
            for (int n = 0; n < APTOS_POR_ANDAR; n++) { 
                if(n<6)
                    matriz[a][n] = new ApartamentoPremium();
                else
                    matriz[a][n] = new ApartamentoSimples();
            }
        }
    }

    private boolean aptoValido(int andar, int numero) {
        return andar >= 0 && andar < NUM_ANDARES && numero >= 0 && numero < APTOS_POR_ANDAR;
    }

    /**
     * Reserva um apartamento, mudando seu status de LIVRE para RESERVADO.
     *
     * @param andar(0 - 19)
     * @param numero do apartamento(0 - 13)
     * @param hospede (dados do hospede que fez a reserva)
     * @return true se a reserva for bem sucedida
     * @throws IllegalArgumentException se andar ou número forem inválidos
     * @pre O hotel deve estar inicializado
     * @post o apartamento tem o status mudado para Reservado e os dados do hospede sao salvos na reserva
    */
    public boolean reservarApartamento(int andar, int numero, Hospede hospede) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }

        matriz[andar][numero].reservar(hospede);
        return true;
        
    }


    /**
     * Realiza o check-in de um apartamento, mudando seu status de RESERVADO para OCUPADO
     *
     * @param andar(0 - 19)
     * @param numero do apartamento(0 - 13)
     * @param hospede (dados do hospede que fez a reserva)
     * @return true se o check-in for bem sucedido
     * @throws IllegalArgumentException se andar ou numero forem inválidos
     * @pre O apartamento deve estar reservado antes
     * @post o apartamento tem o status mudado para OCUPADO e os dados do hospede sao sobrescritos
    */
    public boolean realizarCheckin(int andar, int numero, Hospede hospede) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }
        
        matriz[andar][numero].checkin(hospede);
        return true;
        
    }

    /**
     * Realiza o check-out de um apartamento, mudando seu status de OCUPADO para LIVRE
     *
     * @param andar(0 - 19)
     * @param numero do apartamento(0 - 13)
     * @return true se o check-out for bem sucedido
     * @throws IllegalArgumentException se andar ou número forem invalidos
     * @pre O apartamento deve estar ocupado
     * @post o apartamento tem o status mudado para LIVRE e os dados do hospede sao apagados
    */

    public boolean realizarCheckout(int andar, int numero) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }

        matriz[andar][numero].checkout();
        return true;
    
    }

    /**
     * Cancela uma reserva, mudando seu status de RESERVADO para LIVRE
     *
     * @param andar(0 - 19)
     * @param numero do apartamento(0 - 13)
     * @return true se o cancelamento for bem sucedido
     * @throws IllegalArgumentException se andar ou número forem inválidos
     * @pre O apartamento deve estar reservado
     * @post o apartamento tem o status mudado para LIVRE e os dados do hospede sao apagados
    */

    public boolean cancelarReserva(int andar, int numero) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }

        matriz[andar][numero].cancelarReserva();
        return true;
    }   

    /**
    * Exibe no o mapa visual de ocupação do hotel (20 andares x 14 quartos)
    * @pre O hotel deve estar inicializado
    */

    public void mostrarMapa() {
        int i, j;
        System.out.println("\t\t\tMapa de ocupação\n");
        System.out.printf("quarto->\t");
        for(i=0; i<14; i++)
            System.out.printf("%2d ",i);
        System.out.println();
        for(i=19; i>=0; i--){
            for(j=0; j<14; j++){
                if(j==0)
                    System.out.printf("Andar %2d\t",i);
                System.out.printf(" %c ", matriz[i][j].getSymbol());
            }
            System.out.println();
        }
        System.out.println("\n\t\t\t Legenda");
        System.out.println("Quarto livre: '.'   Quarto reservado:'R'   Quarto ocupado:'O'");
    }

    /**
    * Consulta o status de um apartamento especifico e exibe os dados do hospede, caso não esteja LIVRE
    *
    * @param andar (deve estar entre 0 e 19)
    * @param numero do apartamento (deve estar entre 0 e 13)
    * @throws IllegalArgumentException Se as coordenadas do andar ou numero forem invalidas
    * @pre os dados do hospede devem estar cadastrados caso nao esteja LIVRE
    * @post Exibe o status atual do apartamento e os dados do hospede se houver
    */

    public void consultarApartamento(int andar, int numero) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }

        if(!matriz[andar][numero].estaLivre()) {
            Hospede h = matriz[andar][numero].getHospede();
            System.out.println();
            if(matriz[andar][numero].estaOcupado())
                System.out.println("Status: Ocupado");
            else
                System.out.println("Status: Reservado");

            if(h != null)
                System.out.println(h.toString());    
        }else
            System.out.println("Status: Livre");
        
    }

    /**
    * Calcula a taxa de ocupação do hotel
    *
    * @return taxa de ocupação como um valor decimal entre 0.0(nenhum quarto ocupado) e 1.0(todos os quartos reservados)
    */
    public float calcularTaxaOcupacao() {
        int i,j;
        float taxa,c = 0;
        for(i=0; i<=19; i++)
            for(j=0; j<14; j++)
                if(matriz[i][j].estaOcupado())
                    c = c + 1;
        taxa = c/280;
        return taxa;
    }

    /**
    * Calcula a taxa de reservas do hotel
    *
    * @return A taxa de reservas como um valor decimal entre 0.0(nenhum quarto reservado) e 1.0(todos os quartos reservados)
    */
    public float calcularTaxaReservas() {
        int i,j;
        float taxa,c=0;
        for(i=0; i<=19; i++)
            for(j=0; j<14; j++)
                if(matriz[i][j].estaReservado())
                    c = c + 1;
        taxa = c/280;
        return taxa;
    }

    /**
     * Cadastra um novo serviço no sistema.
     * 
     * @param nome o nome do serviço a ser cadastrado (não pode ser uma string vazia)
     * @param preco o preço do serviço (deve ser estritamente maior que zero)
     * @throws IllegalArgumentException se o preço for menor ou igual a zero 
     *                                  ou se o nome for uma string vazia ("")
     */
    public void cadastrarServico(String nome, float preco) {
        if(preco <= 0){
            throw new IllegalArgumentException("Preço inválido");
        }else if(nome == ""){
            throw new IllegalArgumentException("Nome inválido");
        }else{
            Servico servico = new Servico(nome, preco);
            servicos.add(servico);
        }
    }
    
    /**
     * Registra o consumo de um serviço por um hóspede em um determinado apartamento.
     * 
     * @param andar o andar onde se localiza o apartamento
     * @param numero o número do apartamento
     * @param indiceServico o índice do serviço correspondente na lista de serviços
     * @param quantidade a quantidade consumida do serviço
     * @throws IllegalArgumentException quando o apartamento for invalido
     * @return {@code true} se o consumo foi registrado com sucesso; 
     *         {@code false} se o apartamento for inválido ou não estiver ocupado por nenhum hóspede
     */
    public boolean registrarConsumo(int andar, int numero, int indiceServico, int quantidade) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }else if(!(getApartamento(andar, numero).estaOcupado())){
            System.out.println("O apartamento não está ocupado por nenhum Hóspede");
            return false;
        }else{
            Consumo consumo = new Consumo(getApartamento(andar, numero), servicos.get(indiceServico), quantidade);
            consumos.add(consumo);
            return true;
        }
    }

    public ArrayList<Consumo> getConsumosDoApartamento(int andar, int numero) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }
        throw new UnsupportedOperationException("Implementar getConsumosDoApartamento");
    }

    public Fatura emitirFatura(int andar, int numero, int dias) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }
        throw new UnsupportedOperationException("Implementar emitirFatura");
    }

    public Apartamento getApartamento(int andar, int numero) {
        return matriz[andar][numero];
    }

    public ArrayList<Servico> getServicos() { return servicos; }
    public ArrayList<Consumo> getConsumos() { return consumos; }
}
