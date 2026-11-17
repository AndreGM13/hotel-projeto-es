package hotel.modelo;

public class ApartamentoSimples extends Apartamento {
    private static final long serialVersionUID = 1L;
    private static final float PRECO_DIARIA = 150.0f;

    public ApartamentoSimples(int andar, int numero) {
        super(andar, numero);
    }

    @Override
    public float getPrecoDiaria() {
        return PRECO_DIARIA;
    }
}
