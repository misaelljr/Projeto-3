package DTW;


public class DTW_Ext1 {
	
	//calculo do DTW para retorno da distancia com o valor de w para restringir 
	//campo de busca (algoritmo similar ao do wikipedia)
	public double DTWcalc(double[] s, double[] t, int w) {
		double custo=0;

		double DTW [][] = new double[s.length][t.length];  

		w = Math.max(w, Math.abs(s.length-t.length));

		for(int i=0;i<s.length;i++)
			for(int j=0;j<t.length;j++)
				DTW[i][j] = Double.MAX_VALUE;
		DTW[0][0] = 0;

		for (int i= 1; i<s.length;i++){
			for (int j= Math.max(1, i-w); j<Math.min(t.length, i+w);j++){
				custo = distanceCalc(s[i], t[j]);
				DTW[i][j] = custo + Math.min(DTW[i-1][j], Math.min(DTW[i][j-1], DTW[i-1][j-1]));
			}
		}

		return DTW[s.length-1][t.length-1];

	}
	
	//distância euclidiana entre dois pontos
	public double distanceCalc(double p1, double p2) {
		return (p1 - p2) * (p1 - p2);
	}

}
