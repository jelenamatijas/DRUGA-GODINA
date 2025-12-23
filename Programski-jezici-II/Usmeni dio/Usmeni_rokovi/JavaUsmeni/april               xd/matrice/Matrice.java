public class Matrice{
	public static void main(String... args){
		int[][][] matrica = {{{1,2,3,4},{5,2}},{{0},{21,22,32}}};
		System.out.println(matrica.length);
		for(int i = 0; i< matrica.length;i++){
			System.out.println(matrica[i].length);
			for(int j=0; j<matrica[i].length;j++){
				System.out.println(matrica[i][j].length);
				for(int k = 0; k< matrica[i][j].length;k++){
					System.out.println(matrica[i][j][k]);
				}
			}
		}
	}
}