package DTW;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import DTW3D.Coords_Ext2;
import DTW3D.Serie_Ext2;

public class Main_Ext2 {

	public static void main(String[] args) throws IOException {
		DTW_Ext2 dtw = new DTW_Ext2();
		ArrayList<Serie_Ext2> conjuntoTeste = new ArrayList<Serie_Ext2>(); //conjuntos de dados de teste
		ArrayList<Serie_Ext2> conjuntoTreinamento = new ArrayList<Serie_Ext2>(); //conjunto de dados de treinamento

		double acerto=0;
		double erro = 0;
		int index = 0;
		double min;
		double distancia;

		//medição de eficência
		// Memória total
		System.out.println("Memória total: "
				+ Runtime.getRuntime().totalMemory());

		// Memória livre
		System.out.println("Memória livre: "
				+ Runtime.getRuntime().freeMemory());

		// Tempo de inicio
		double initialTime = System.nanoTime();


		//inicio da leitura dos arquivos - Os arquivos são lidos separadamente e add em ArrayList

		try {
			//primeiro arquivo
			FileReader fin1 = new FileReader("teste3D.txt");
			Locale.setDefault(Locale.US);
			Scanner src = new Scanner(fin1);
			double x, y, z;
			while (src.hasNextLine()) {
				String linha = src.nextLine();
				Scanner leitorLinha = new Scanner(linha);
				Serie_Ext2 serie = new Serie_Ext2(leitorLinha.nextInt());
				while (leitorLinha.hasNextDouble()) {
					x = leitorLinha.nextDouble();
					y = leitorLinha.nextDouble();
					z = leitorLinha.nextDouble();
					serie.getSerie().add(new Coords_Ext2(x, y, z));
				}
				conjuntoTeste.add(serie);
			}

			//segundo arquivo
			FileReader fin2 = new FileReader("treino3D.txt");
			Locale.setDefault(Locale.US);
			Scanner src1 = new Scanner(fin2);
			double x1, y1, z1;
			while (src1.hasNextLine()) {
				String linha = src1.nextLine();
				Scanner leitorLinha = new Scanner(linha);
				Serie_Ext2 serie = new Serie_Ext2(leitorLinha.nextInt());
				while (leitorLinha.hasNextDouble()) {
					x1 = leitorLinha.nextDouble();
					y1 = leitorLinha.nextDouble();
					z1 = leitorLinha.nextDouble();
					serie.getSerie().add(new Coords_Ext2(x1, y1, z1)); 
				}
				conjuntoTreinamento.add(serie);
			}

			src.close();
			src1.close();
			fin1.close();
			fin2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		//laço para percorrer as series com o classificador 1-Nearest-Neighbor (1NN)
		for(Serie_Ext2 serieTeste : conjuntoTeste){
			int idSerie = serieTeste.getId();
			min = Double.MAX_VALUE;
			for(Serie_Ext2 serieTreino : conjuntoTreinamento){
				distancia = dtw.DTWcalc(serieTeste, serieTreino); // passando valores à função DTW
				if(min>distancia){
					min = distancia;
					index = serieTreino.getId();	
				}	

			}

			System.out.println(" x: "+idSerie+" xv: "+index);

			//para contar como "acerto" apenas é realizada a comparação do label da serie 1 com o index da localização do min
			if(idSerie == index){
				acerto++;
			}else{
				erro++;
			}
		}

		//contabilização de resultados
		double percAcert = acerto*100/804;
		double percErro = erro*100/804;

		System.out.printf("acertos: "+acerto + " " + "porcentagem: %.2f" +"\n", percAcert);
		System.out.printf("erros: "+erro + " " + "porcentagem: %.2f" +"\n", percErro);
		System.out.println("Tempo de execução total: "+ (System.nanoTime() - initialTime)/1000000000.0);
		System.out.println("Memória total: "+ Runtime.getRuntime().totalMemory());
		System.out.println("Memória livre: "+ Runtime.getRuntime().freeMemory());	

	}
}