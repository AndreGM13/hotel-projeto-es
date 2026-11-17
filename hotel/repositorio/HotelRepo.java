package hotel.repositorio;

import hotel.negocio.Hotel;
import java.io.*;

public class HotelRepo {
    private static final String ARQUIVO = "hotel.dat";

    public static void salvar(Hotel hotel) throws IOException {
        if (hotel == null) {
            throw new IllegalArgumentException("Hotel não pode ser nulo");
        }

        try (ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            saida.writeObject(hotel);
        }
    }

    public static Hotel carregar() throws IOException, ClassNotFoundException {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            Object objeto = entrada.readObject();
            if (!(objeto instanceof Hotel)) {
                throw new InvalidObjectException("O arquivo não contém dados de um hotel");
            }
            return (Hotel) objeto;
        }
    }
}
