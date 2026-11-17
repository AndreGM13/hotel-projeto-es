package hotel.modelo;

public class ApartamentoPremium extends Apartamento {
    private static final long serialVersionUID = 1L;
    private static final float PRECO_DIARIA = 350.0f;

    public ApartamentoPremium(int andar, int numero) {
        super(andar, numero);
    }

    @Override
    public float getPrecoDiaria() {
        return PRECO_DIARIA;
    }
}
