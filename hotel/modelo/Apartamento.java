package hotel.modelo;

public class Apartamento {
    private Status status;
    private Hospede hospede;

    public Apartamento() {
        this.status = Status.LIVRE;
        this.hospede = null;
    }

    public Status getStatus() { return status; }
    public Hospede getHospede() { return hospede; }

    /*
     * Reserva o apartamento para um determinado hospede
     *
     * @param hospede (Dados do hóspede que realizara a reserva)
     * @throws IllegalArgumentException se o hospede for nulo
     * @throws IllegalStateException se o apartamento não estiver LIVRE
     * @pre O apartamento deve estar com status LIVRE
     * @post O status do apartamento passa a ser RESERVADO e os dados do hóspede são salvos
     */
    public void reservar(Hospede h) {
        if(h == null)   {
            throw new IllegalArgumentException("Erro nos dados do hospede");
        }    
        if(!estaLivre()){
            throw new IllegalStateException("Apartamento não disponível");
        }
                
        status = Status.RESERVADO;
        hospede = h;  

    }

    /*
     * Realiza o check-in no apartamento
     *
     * @param hospede Dados do hospede que ira se hospedar
     * @throws IllegalArgumentException se o hospede for null
     * @throws IllegalStateException se o apartamento já estiver OCUPADO
     * @pre O apartamento deve estar RESERVADO
     * @post O status do apartamento passa a ser OCUPADO e os dados do hospede são sobrescritos
     */
    public void checkin(Hospede h) {
        if(h == null){
            throw new IllegalArgumentException("Erro nos dados do hospede");
        }   
        if(!estaReservado()){
                throw new IllegalStateException("Apartamento não reservado");
        }   
             
        status = Status.OCUPADO;
        hospede = h;   
                
    }

    /*
     * Realiza o check-out do apartamento
     *
     * @throws IllegalStateException Se o apartamento não estiver OCUPADO
     * @pre O apartamento deve estar com status OCUPADO
     * @post O status do apartamento passa a ser LIVRE e os dados do hospede são apagados
     */
    public void checkout() {
        if(!estaOcupado()){
            throw new IllegalStateException("Apartamento não ocupado");  
        }
            
        status = Status.LIVRE;
        hospede = null; 
    }

    /*
     * Cancela a reserva atual do apartamento
     *
     * @throws IllegalStateException Se o apartamento não estiver RESERVADO
     * @pre O apartamento deve estar com status RESERVADO
     * @post O status do apartamento passa a ser LIVRE e os dados do hospede sao apagados
     */
    public void cancelarReserva(){
        if(!estaReservado()){
             throw new IllegalStateException("Apartamento não reservado");   
        }
            
        status = Status.LIVRE;
        hospede = null;
    }

    public boolean estaLivre() { return status == Status.LIVRE; }
    public boolean estaReservado() { return status == Status.RESERVADO; }
    public boolean estaOcupado() { return status == Status.OCUPADO; }

    public float getPrecoDiaria() { return 0f; }

    public char getSymbol() {
        switch (status) {
            case LIVRE: return '.';
            case RESERVADO: return 'R';
            case OCUPADO: return 'O';
            default: return '?';
        }
    }
}
