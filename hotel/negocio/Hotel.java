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
                matriz[a][n] = new Apartamento();
            }
        }
    }

    private boolean aptoValido(int andar, int numero) {
        return andar >= 0 && andar < NUM_ANDARES && numero >= 0 && numero < APTOS_POR_ANDAR;
    }

    /*
     * Reserva um apartamento, mudando seu status de LIVRE para RESERVADO.
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
        else if(matriz[andar][numero].estaLivre()){
                matriz[andar][numero].reservar(hospede);
                return true;
        }
        else 
            throw new IllegalArgumentException("Apartamento inválido");
    }
    /*
     * Realiza o check-in de um apartamento, mudando seu status de RESERVADO para OCUPADO.
     * @param andar(0 - 19)
     * @param numero do apartamento(0 - 13)
     * @param hospede (dados do hospede que fez a reserva)
     * @return true se o check-in for bem sucedido
     * @throws IllegalArgumentException se andar ou número forem inválidos ou se nao tiver reserva
     * @pre O apartamento deve estar reservado antes
     * @post o apartamento tem o status mudado para OCUPADO e os dados do hospede sao sobrescritos
    */
    public boolean realizarCheckin(int andar, int numero, Hospede hospede) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }
        else if(matriz[andar][numero].estaReservado()){
            matriz[andar][numero].checkin(hospede);
            return true;
        }
        else
            throw new IllegalArgumentException("Apartamento não reservado");
    }

    /*
     * Realiza o check-out de um apartamento, mudando seu status de OCUPADO para LIVRE.
     * @param andar(0 - 19)
     * @param numero do apartamento(0 - 13)
     * @return true se o check-out for bem sucedido
     * @throws IllegalArgumentException se andar ou número forem inválidos ou o apartamento nao estiver ocupado
     * @pre O apartamento deve estar ocupado
     * @post o apartamento tem o status mudado para LIVRE e os dados do hospede sao apagados
    */

    public boolean realizarCheckout(int andar, int numero) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }
        else if(matriz[andar][numero].estaOcupado()){
            matriz[andar][numero].checkout();
            return true;
        }
        else
        throw new UnsupportedOperationException("Nao existe hospede nesse apartamento");
    }

    /*
     * Cancela uma reserva, mudando seu status de RESERVADO para LIVRE.
     * @param andar(0 - 19)
     * @param numero do apartamento(0 - 13)
     * @return true se o cancelamento for bem sucedido
     * @throws IllegalArgumentException se andar ou número forem inválidos ou nao existir reserva
     * @pre O apartamento deve estar reservado
     * @post o apartamento tem o status mudado para LIVRE e os dados do hospede sao apagados
    */
    public boolean cancelarReserva(int andar, int numero) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }
        else if(matriz[andar][numero].estaReservado()){
            matriz[andar][numero].cancelarReserva();
            return true;
        }
        else 
            throw new UnsupportedOperationException("Nao existe reserva para esse apartamento");
    }   

    public void mostrarMapa() {
        throw new UnsupportedOperationException("Implementar mostrarMapa");
    }

    public void consultarApartamento(int andar, int numero) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }
        throw new UnsupportedOperationException("Implementar consultarApartamento");
    }

    public float calcularTaxaOcupacao() {
        throw new UnsupportedOperationException("Implementar calcularTaxaOcupacao");
    }

    public float calcularTaxaReservas() {
        throw new UnsupportedOperationException("Implementar calcularTaxaReservas");
    }

    public void cadastrarServico(String nome, float preco) {
        throw new UnsupportedOperationException("Implementar cadastrarServico");
    }

    public boolean registrarConsumo(int andar, int numero, int indiceServico, int quantidade) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }
        throw new UnsupportedOperationException("Implementar registrarConsumo");
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
