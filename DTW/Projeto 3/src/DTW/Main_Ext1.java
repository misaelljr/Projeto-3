package DTW;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main_Ext1 {

	public static void main(String[] args) throws FileNotFoundException {
		DTW_Ext1 dtw = new DTW_Ext1();
		Scanner input1 = null;
		Scanner input2 = null;
		double teste[] = null;
		double treino[] = null;
		ArrayList<Integer> label_teste= new ArrayList<Integer>();
		ArrayList<Integer> label_treino =  new ArrayList<Integer>();
		ArrayList<Double> test = new ArrayList<Double>();
		double acerto=0;
		double erro = 0;
		int index = 0;
		double min;

		//medição de eficência
		// Memória total
		System.out.println("Memória total: "
				+ Runtime.getRuntime().totalMemory());

		// Memória livre
		System.out.println("Memória livre: "
				+ Runtime.getRuntime().freeMemory());

		// Tempo de inicio
		double initialTime = System.nanoTime();

		//leitura dos arquivos teste.txt e treino.txt
		try {
			input1 = new Scanner(new File("teste.txt")).useLocale(Locale.US);   

			while(input1.hasNextLine()){
				String serie1[] = input1.nextLine().split(" ");
				teste = new double[serie1.length];
				label_teste.add(Integer.parseInt(serie1[0])); //separando valor label

				for(int i=0;i<teste.length;i++){
					teste [i]= Double.parseDouble(serie1[i]); //convertendo valores para double, da série, em teste
				}

				input2 = new Scanner(new File("treino.txt")).useLocale(Locale.US);

				while(input2.hasNextLine()){
					String serie2[] = input2.nextLine().split(" ");
					treino = new double[serie2.length];
					label_treino.add(Integer.parseInt(serie2[0])); //separando label de treino (sera utilizado no 1nn)

					for(int i=0;i<treino.length;i++){
						treino[i] = Double.parseDouble(serie2[i]); //convertendo valores para double, da série, em treino
					}

					test.add(dtw.DTWcalc(teste,treino, 0)); //passando valores de series para DTW com calculo da percentagem como 3 parâmetro
				}

				// 1nn
				min = test.get(0);
				for(int i=0;i<test.size();i++){
					double c = (double) test.get(i);
					if(min>c){
						min = test.get(i);
						index = label_treino.get(i);	
					}	
				}
				test.clear();
				label_treino.clear();

				System.out.println(" x: "+serie1[0]+" xv: "+index);

				//para contar como "acerto" apenas é realizada a comparação do label da serie 1 com o index da localização do min
				if(Integer.parseInt(serie1[0]) == index){
					acerto++;
				}else{
					erro++;
				}
				input2.close();
			}

			//contabilização de resultados
			double percAcert = acerto*100/960;
			double percErro = erro*100/960;

			System.out.printf("acertos: "+acerto + " " + "porcentagem: %.2f" +"\n", percAcert);
			System.out.printf("erros: "+erro + " " + "porcentagem: %.2f" +"\n", percErro);
			System.out.println("Tempo de execução total: "+ (System.nanoTime() - initialTime)/1000000000.0);
			System.out.println("Memória total: "+ Runtime.getRuntime().totalMemory());
			System.out.println("Memória livre: "+ Runtime.getRuntime().freeMemory());			

			input1.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}