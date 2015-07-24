package DTW;


public class DTW {

	//calculo do DTW para retorno da distancia (algoritmo similar ao do wikipedia)
	public double DTWcalc(double[] s, double[] t) {
		double custo=0;

		double DTW [][] = new double[s.length][t.length];  

		for(int i=0;i<s.length;i++)
			DTW[i][0] = Double.MAX_VALUE;
		for(int i=0;i<t.length;i++)
			DTW[0][i] = Double.MAX_VALUE;
		DTW[0][0] = 0;

		for (int i= 1; i<s.length;i++){
			for (int j= 1; j<t.length;j++){
				custo = distanceCalc(s[i], t[j]); //o custo é a distância entre os pontos
				DTW[i][j] = custo + Math.min(DTW[i-1][j], Math.min(DTW[i][j-1], DTW[i-1][j-1])); //incremento do custo da distância
			}
		}

		return DTW[s.length-1][t.length-1]; 

	}
	
	//distância entre dois pontos
	public double distanceCalc(double p1, double p2) {
		return (p1 - p2) * (p1 - p2);
	}

}
