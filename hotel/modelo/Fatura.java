package hotel.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fatura {
    private Apartamento apartamento;
    private int dias;
    private List<Consumo> consumos;

    public Fatura(Apartamento apto, int dias, List<Consumo> consumos) {
        if (apto == null) {
            throw new IllegalArgumentException("Apartamento não pode ser nulo");
        }
        if (dias <= 0) {
            throw new IllegalArgumentException("Quantidade de dias deve ser positiva");
        }
        if (consumos == null) {
            throw new IllegalArgumentException("Lista de consumos não pode ser nula");
        }

        this.apartamento = apto;
        this.dias = dias;
        this.consumos = new ArrayList<>(consumos);
    }

    public float getTotalGeral() {
        return getTotalDiarias() + getTotalConsumos();
    }

    public Apartamento getApartamento() { return apartamento; }
    public int getDias() { return dias; }
    public List<Consumo> getConsumos() {
        return Collections.unmodifiableList(consumos);
    }

    public float getTotalDiarias() {
        return apartamento.getPrecoDiaria() * dias;
    }

    public float getTotalConsumos() {
        float total = 0;
        for (Consumo consumo : consumos) {
            total += consumo.getTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Fatura: R$ " + getTotalGeral();
    }
}
