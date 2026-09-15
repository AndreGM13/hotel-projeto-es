package hotel.apresentacao;

import hotel.modelo.*;
import hotel.negocio.Hotel;
public class HotelTest {
    private static int passou = 0;
    private static int total = 0;
    
    static void testarReservarAptoLivre(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        if(hotel.reservarApartamento(1, 4, hospede) &&  hotel.getApartamento(1, 4).estaLivre()){
            passou++;
        }else{
            System.out.println("Falha: testarReservarAptoLivre");
        }
    }

    static void testarReservarAptoOcupadoFalha(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        if(!(hotel.reservarApartamento(1, 4, hospede) && hotel.getApartamento(1, 4).estaLivre())){
            passou++;
        }else{
            System.out.println("Falha: testarReservarAptoOcupadoFalha");
        }
    }

    static void testarCheckIn(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        if(hotel.reservarApartamento(1, 4, hospede) && hotel.realizarCheckin(1 , 4 , hospede)){
            passou++;
        }else{
            System.out.println("Falha: testarCheckIn");
        }
    }

    static void testarCheckInFalha(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        if(!hotel.realizarCheckin(1 , 4 , hospede)){
            passou++;
        }else{
            System.out.println("Falha: testarCheckInFalha");
        }
    }
    
    static void testarCheckOut(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        if(hotel.realizarCheckin(1 , 4 , hospede) && hotel.realizarCheckout(1 ,4)){
            passou++;
        }else{
            System.out.println("Falha: testarCheckOut");
        }
    }

    static void testarCheckOutFalha(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        if(!hotel.realizarCheckout(1 ,4)){
            passou++;
        }else{
            System.out.println("Falha: testarCheckOutFalha");
        }
    }

    static void testarCancelarReserva(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        if(hotel.reservarApartamento(1, 4, hospede) && hotel.cancelarReserva(1, 4)){
            passou++;
        }else{
            System.out.println("Falha: testarCancelarReserva");
        }
    }

    static void testarCancelarReservaFalha(){
        total++;
        Hotel hotel = new Hotel();
        Hospede hospede =  new Hospede("123", "Ítalo", "puc", "123", "italo@pucsp.edu.br");
        if (!hotel.cancelarReserva(1, 4)) {
            passou++;
        }else{
            System.out.println("Falha: testarCancelarReserva");
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
        

        System.out.println(passou + "/" + total + " testes passaram");
    }
    

}
