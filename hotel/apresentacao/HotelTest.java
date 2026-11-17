package hotel.apresentacao;

import hotel.modelo.*;
import hotel.negocio.Hotel;
import hotel.repositorio.HotelRepo;
import java.io.File;
import java.nio.file.Files;
import hotel.servico.RelatorioSvc;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class HotelTest {
    private static int passou = 0;
    private static int total = 0;
    
    static void testarReservarAptoLivre(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        try {
            if(hotel.getApartamento(1, 4).estaLivre() && hotel.reservarApartamento(1, 4, hospede)){
                passou++;
            }else{
                System.out.println("Falha: testarReservarAptoLivre");
            }
        } catch (Exception e) {
            System.out.println("Falha: testarReservarAptoLivre");
            System.out.println(e);
        }       
    }

    static void testarReservarAptoOcupadoFalha(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        try {
            if(!(hotel.reservarApartamento(1, 4, hospede) && hotel.getApartamento(1, 4).estaLivre())){
                passou++;
            }else{
                System.out.println("Falha: testarReservarAptoOcupadoFalha");
            }
        } catch (Exception e) {
            passou++;
        }
    }

    static void testarCheckIn(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        try {
            if(hotel.reservarApartamento(1, 4, hospede) && hotel.realizarCheckin(1 , 4 , hospede)){
                passou++;
            }else{
                System.out.println("Falha: testarCheckIn");
            }
        } catch (Exception e) {
            System.out.println("Falha: testarCheckIn");
            System.out.println(e);
        }
    }

    static void testarCheckInFalha(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        try {
            if (!hotel.realizarCheckin(1, 4, hospede)) {
                passou++;
            } else {
                System.out.println("Falha: testarCheckInFalha");
            }
        } catch (Exception e) {
            passou++;
        }
    }
    
    static void testarCheckOut(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        try {
            if (hotel.reservarApartamento(1, 4, hospede) && hotel.realizarCheckin(1, 4, hospede) && hotel.realizarCheckout(1, 4)) {
                passou++;
            } else {
                System.out.println("Falha: testarCheckOut");
            }
        } catch (Exception e) {
            System.out.println("Falha: testarCheckOut");
            System.out.println(e);
        }
    }

    static void testarCheckOutFalha(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");

        try {
            if (!hotel.realizarCheckout(1, 4)) {
                passou++;
            } else {
                System.out.println("Falha: testarCheckOutFalha");
            }
        } catch (Exception e) {
            passou++;
        }
    }

    static void testarCancelarReserva(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        try {
            if (hotel.reservarApartamento(1, 4, hospede) && hotel.cancelarReserva(1, 4)) {
                passou++;
            } else {
                System.out.println("Falha: testarCancelarReserva");
            }
        } catch (Exception e) {
            System.out.println("Falha: testarCancelarReserva");
            System.out.println(e);
        }
    }

    static void testarCancelarReservaFalha(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        try {
            if (!hotel.cancelarReserva(1, 4)) {
                passou++;
            } else {
                System.out.println("Falha: testarCancelarReserva");
            }
        } catch (Exception e) {
            passou++;
        }
    }
    
    static void testarMostrarMapa(){
        total++;
        try {
            Hotel hotel = new Hotel();
            hotel.mostrarMapa();
            passou++;
        } catch (Exception e) {
            System.out.println("Falha: testarMostrarMapa");
            System.out.println(e);
        }
        
    }

    static void testarConsultarApartamentoOcupado(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        try {
            hotel.reservarApartamento(1, 4, hospede);
            hotel.realizarCheckin(1, 4 , hospede);
            hotel.consultarApartamento(1, 4);
            passou++;
        } catch (Exception e) {
            System.out.println("Falha: testarConsultarApartamentoOcupado");
            System.out.println(e);
        }
    }

    static void testarConsultarApartamentoLivre(){
        total++;
        Hotel hotel = new Hotel();
        try {
            hotel.consultarApartamento(0, 0); 
            passou++;
        } catch (Exception e) {
            System.out.println("Falha: testarConsultarApartamentoLivre");
            System.out.println(e);
        }
    }
    static void testarConsultarApartamentoReservado(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        try {
            hotel.reservarApartamento(1, 4, hospede);
            hotel.consultarApartamento(1, 4);
            passou++;
        } catch (Exception e) {
            System.out.println("Falha: testarConsultarApartamentoReservado");
            System.out.println(e);
        }
    }
    static void testarCalcularTaxaOcupacao(){
        total++;
        Hotel hotel = new Hotel();
        //calculo para um hóspede, deve retornar 1/280 = 0,0036 aprox
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        try{
            hotel.reservarApartamento(1, 4, hospede);
            hotel.realizarCheckin(1, 4, hospede);
            System.out.println(hotel.calcularTaxaOcupacao());
            passou++;
        }catch (Exception e){
            System.out.println("Falha: testarCalcularTaxaOcupacao");
            System.out.println(e);
        }

    }

    static void testarCalcularTaxaReservas(){
        total++;
        Hotel hotel = new Hotel();
        //calculo para um hóspede, deve retornar 1/280 = 0,0036 aprox
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        try {
            hotel.reservarApartamento(1, 4, hospede);
            System.out.println(hotel.calcularTaxaReservas());
            passou++;
        } catch (Exception e) {
            System.out.println("Falha: testarCalcularTaxaReservas");
            System.out.println(e);
        }
    }

    static void testarCadastrarServico(){
        total++;
        Hotel hotel = new Hotel();
        try{
            hotel.cadastrarServico("teste", 100);
            hotel.cadastrarServico("teste2", 67);
            System.out.println(hotel.getServicos()); //para visualizar se o servico foi cadastrado
            passou++;

        }catch(Exception e){
            System.out.println("Falha: testarCadastrarServico");
            System.out.println(e);
        }
    }

    static void testarCadastrarServicoFalha(){
        total++;
        Hotel hotel = new Hotel();
        try {
            //testar para preco <= 0, deve devolver exeption
            hotel.cadastrarServico("teste", -1);
            System.out.println("Falha: testarCadastrarServicoFalha");

        } catch (UnsupportedOperationException e) {
            System.out.println("Falha: testarCadastrarServicoFalha");
            System.out.println(e);
        } catch (Exception e){
            passou++;
        }
    }

    static void testarConsumo(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        hotel.reservarApartamento(1, 4, hospede);
        hotel.realizarCheckin(1, 4, hospede);
        try{
            hotel.cadastrarServico("teste", 100);
            hotel.registrarConsumo(1,4, 0, 1);
            //teste integrado para ver se o método getConsumosDoApartamento tambem funciona
            System.out.println(hotel.getConsumosDoApartamento(1,4));
            passou++;

        }catch(Exception e){
            System.out.println("Falha: testarConsumo");
            System.out.println(e);
        }
    }

    static void testarConsumoFalha(){
        total++;
        //teste para quarto nao ocupado
        Hotel hotel = new Hotel();
        try{
            hotel.cadastrarServico("teste", 100);
            if(!hotel.registrarConsumo(1,4, 0, 1)){
                passou++;
            }else{
                System.out.println("Falha: testarConsumoFalha");
            }
        }catch(Exception e){
            System.out.println("Falha: testarConsumoFalha");
            System.out.println(e);
        }

    }
    static void testarEmitirFatura(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        
        hotel.reservarApartamento(1, 4, hospede);
        hotel.realizarCheckin(1, 4, hospede);

        try {
            System.out.println(hotel.emitirFatura(1, 4, 5));
            passou++;
        } catch (Exception e) {
            System.out.println("Falha: testarEmitirFatura");
            System.out.println(e);
        }
    }

    static void testarRankingServicos(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede = new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");

        try {
            hotel.reservarApartamento(1, 4, hospede);
            hotel.realizarCheckin(1, 4, hospede);
            hotel.cadastrarServico("Café", 20);
            hotel.cadastrarServico("Lavanderia", 35);
            hotel.cadastrarServico("Massagem", 60);
            hotel.registrarConsumo(1, 4, 0, 2);
            hotel.registrarConsumo(1, 4, 2, 1);
            hotel.registrarConsumo(1, 4, 1, 3);
            
            RelatorioSvc relatorio = new RelatorioSvc(hotel);
            relatorio.gerarRankingServicos();
            passou++;
        } catch (Exception e) {
            System.out.println("Falha: testarRankingServicos");
            System.out.println(e);
        }
    }

    static void testarRelatorioOcupacao(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede = new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");

        try {
            hotel.reservarApartamento(1, 4, hospede);
            hotel.realizarCheckin(1, 4, hospede);
            hotel.emitirFatura(1, 4, 4);

            RelatorioSvc relatorio = new RelatorioSvc(hotel);
            relatorio.gerarRelatorioOcupacao();
            passou++;
        } catch (Exception e) {
            System.out.println("Falha: testarRelatorioOcupacao");
            System.out.println(e);
        }
    }

    static void testarResumoFinanceiro(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede = new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");

        try {
            hotel.reservarApartamento(1, 4, hospede);
            hotel.realizarCheckin(1, 4, hospede);
            hotel.emitirFatura(1, 4, 4);

            RelatorioSvc relatorio = new RelatorioSvc(hotel);
            relatorio.gerarResumoFinanceiro();
            passou++;
        } catch (Exception e) {
            System.out.println("Falha: testarResumoFinanceiro");
            System.out.println(e);
        }
    }

    static void testarTotaisFatura(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede = new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        hotel.reservarApartamento(1, 0, hospede);
        hotel.realizarCheckin(1, 0, hospede);
        hotel.cadastrarServico("Café", 25);
        hotel.cadastrarServico("Lavanderia", 10);
        hotel.registrarConsumo(1, 0, 0, 3);
        hotel.registrarConsumo(1, 0, 1, 2);

        try {
            Fatura fatura = hotel.emitirFatura(1, 0, 2);
            boolean totaisCorretos = fatura.getTotalDiarias() == 700
                    && fatura.getTotalConsumos() == 95
                    && fatura.getTotalGeral() == 795
                    && hotel.getConsumosDoApartamento(1, 0).size() == 2;
            hotel.registrarConsumo(1, 0, 0, 1);
            totaisCorretos = totaisCorretos && fatura.getTotalGeral() == 795;
            if (totaisCorretos) {
                passou++;
            } else {
                System.out.println("Falha: testarTotaisFatura");
            }
        } catch (Exception e) {
            System.out.println("Falha: testarTotaisFatura");
            System.out.println(e);
        }
    }

    static void testarPersistencia(){
        total++;
        File arquivo = new File("hotel.dat");
        boolean arquivoExistia = arquivo.exists();
        byte[] dadosAnteriores = null;
        boolean testeValido = false;

        try {
            if (arquivoExistia) {
                dadosAnteriores = Files.readAllBytes(arquivo.toPath());
            }

            Hotel original = new Hotel();
            Hospede hospede = new Hospede("456", "Maria", "Rua A", "9999", "maria@example.com");
            original.reservarApartamento(2, 0, hospede);
            original.realizarCheckin(2, 0, hospede);
            original.cadastrarServico("Café", 25);
            original.registrarConsumo(2, 0, 0, 2);

            Hotel carregado;
            HotelRepo.salvar(original);
            carregado = HotelRepo.carregar();

            boolean dadosPreservados = carregado.getApartamento(2, 0).estaOcupado()
                    && "Maria".equals(carregado.getApartamento(2, 0).getHospede().getNome())
                    && carregado.getServicos().size() == 1
                    && carregado.getConsumosDoApartamento(2, 0).size() == 1
                    && carregado.getConsumosDoApartamento(2, 0).get(0).getServico()
                            == carregado.getServicos().get(0);
            testeValido = dadosPreservados;
        } catch (Exception e) {
            System.out.println("Falha: testarPersistencia");
            System.out.println(e);
        } finally {
            try {
                if (arquivoExistia) {
                    Files.write(arquivo.toPath(), dadosAnteriores);
                } else {
                    Files.deleteIfExists(arquivo.toPath());
                }
            } catch (Exception e) {
                testeValido = false;
                System.out.println("Falha ao restaurar arquivo de persistência: " + e);
            }
        }

        if (testeValido) {
            passou++;
        } else {
            System.out.println("Falha: testarPersistencia");
        }
    }

    public static void main(String[] args) {
        testarReservarAptoLivre();
        testarReservarAptoOcupadoFalha();
        testarCheckIn();
        testarCheckInFalha();
        testarCancelarReserva();
        testarCancelarReserva();
        testarCancelarReservaFalha();
        testarCheckOut();
        testarCheckOutFalha();
        testarMostrarMapa();
        System.out.println();
        testarConsultarApartamentoOcupado();
        System.out.println();
        testarConsultarApartamentoLivre();
        System.out.println();
        testarConsultarApartamentoReservado();
        System.out.println();
        testarCalcularTaxaOcupacao();
        testarCalcularTaxaReservas();
        testarCadastrarServico();
        testarCadastrarServicoFalha();
        testarConsumo();
        testarConsumoFalha();
        testarEmitirFatura();
        testarTotaisFatura();
testarPersistencia();
        System.out.println();
        testarRelatorioOcupacao();
        System.out.println();
        testarResumoFinanceiro();
        System.out.println();
        testarRankingServicos();

        System.out.println(passou + "/" + total + " testes passaram");
    }
    

}
