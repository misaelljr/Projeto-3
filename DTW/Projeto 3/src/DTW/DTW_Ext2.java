package DTW;

import DTW3D.Coords_Ext2;
import DTW3D.Serie_Ext2;

public class DTW_Ext2 {

	//calculo do DTW para retorno da distancia
	public double DTWcalc(Serie_Ext2 s, Serie_Ext2 t) {
		double custo=0;

		double DTW [][] = new double[s.getSerie().size()][t.getSerie().size()];  

		for(int i=0;i<s.getSerie().size();i++)
			DTW[i][0] = Double.MAX_VALUE;
		for(int j=0;j<t.getSerie().size();j++)
			DTW[0][j] = Double.MAX_VALUE;
		DTW[0][0] = 0;

		for (int i= 1; i<s.getSerie().size();i++){
			for (int j= 1; j<t.getSerie().size();j++){
				custo = distanceCalc(s.getSerie().get(i-1), t.getSerie().get(j-1)); //o custo é a distância entre os pontos
				DTW[i][j] = custo + Math.min(DTW[i-1][j], Math.min(DTW[i][j-1], DTW[i-1][j-1])); //incremento do custo
			}
		}

		return DTW[s.getSerie().size()-1][t.getSerie().size()-1];
	}

	//calculo da distancia para dados tridimensionais
	public double distanceCalc(Coords_Ext2 p1, Coords_Ext2 p2) {
		double x = Math.pow(p1.getX() - p2.getX(), 2);
		double y = Math.pow(p1.getY() - p2.getY(), 2);
		double z = Math.pow(p1.getZ() - p2.getZ(), 2);
		return Math.sqrt(x + y + z);
	}

}
