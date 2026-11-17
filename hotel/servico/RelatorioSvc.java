package hotel.servico;

import hotel.modelo.*;
import hotel.negocio.Hotel;
import java.util.*;

public class RelatorioSvc {
    private Hotel hotel;

    public RelatorioSvc(Hotel hotel) {
        this.hotel = hotel;
    }

    public void gerarRelatorioOcupacao() {
        ArrayList<Fatura> faturas =  hotel.getFaturas();
        int andares = hotel.NUM_ANDARES;
        int numeros = hotel.APTOS_POR_ANDAR;
        int[][] ocupado = new int[andares][numeros];

        for(int i = 0; i < andares; i++ ){//inicializando matriz de ocupacao
            for(int j = 0; j < numeros; j++){
                ocupado[i][j] = 0;
            }
        }

        for(Fatura fatura : faturas){//soma quantos dias cada apartamento ficou reservado
            ocupado[fatura.getApartamento().getAndar()][fatura.getApartamento().getNumero()] += fatura.getDias();
        }

        for(int i = 0; i < andares; i++ ){//inicializando matriz de ocupacao
            for(int j = 0; j < numeros; j++){
                System.out.println(i + " andar;  " + j + " apartamento   "+ "dias ocupado: " + ocupado[i][j]);
            }
        }

    }

    public void gerarRankingServicos() {
        ArrayList<Servico> servicos = hotel.getServicos();
        int[] qtd = new int[servicos.size()]; //lista da quantidade de vezes que cada servico foi usado ligado ao indice original
        ArrayList<Consumo> consumos = hotel.getConsumos();
        
        for(Consumo consumo : consumos){
            for(int i = 0; i < servicos.size(); i++){
                if((consumo.getServico().getNome()).equals(servicos.get(i).getNome())){
                    qtd[i] += consumo.getQuantidade();
                }
            }
        }

        System.out.println("RANK DE SERVICOS:");
        int maior , indice , rank , cont;
        cont = 0;
        rank = 0;
        while (cont < servicos.size()) {
            maior = 0;
            indice = 0;
            rank++;
            for(int i = 0; i < servicos.size(); i++){
                if(qtd[i] >= maior){
                    maior = qtd[i];
                    indice = i;
                }
            }

            String saida = rank + "-" + servicos.get(indice).getNome() + ": " + qtd[indice] + " vezes";
            System.out.println(saida);
            qtd[indice] = -1;
            cont++;
        }
    }

    public void gerarResumoFinanceiro() {
        ArrayList<Fatura> faturas = hotel.getFaturas();
        float total = 0;
        for(Fatura fatura : faturas){
            total += fatura.getTotalGeral();
        }
        System.out.println("Faturamento atual: R$" + total);
    }
}
