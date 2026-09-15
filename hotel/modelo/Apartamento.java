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

    public void reservar(Hospede h) {
        status = Status.RESERVADO;
        hospede = h;

    }

    public void checkin(Hospede h) {
        status = Status.OCUPADO;
        hospede = h;

    }

    public void checkout() {
        status = Status.LIVRE;
        hospede = null;

    }

    public void cancelarReserva() {
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
