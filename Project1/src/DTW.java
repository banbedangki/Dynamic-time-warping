
public class DTW {
	public float[] seq1;
	public float[] seq2;
	public int[][] warpingPath;
	public double warpingDistance;
	public int n;
	public int m;
	public int k;
	
	public DTW(float[] sample, float[] template){
		seq1 = sample;
		seq2 = template;
		n = seq1.length;
		m = seq2.length;
		k = 1;
		warpingPath = new int[n + m][2];
		warpingDistance = 0.0;
		this.compute();
	}
	
	//compute
	public void compute(){
		double accumlateDistance = 0.0;
		double[][] d = new double[n][m]; //local distances
		double[][] D = new double[n][m]; //global distances
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				d[i][j] = distanceBetween(seq1[i], seq2[j]);
			}
		}
		
		D[0][0] = d[0][0];
		for(int i = 1; i < n; i++){
			D[i][0] = d[i][0] + D[i - 1][0];
		}
		for(int j = 1; j < m; j++){
			D[0][j] = d[0][j] + D[0][j - 1];
		}
		
		for(int i = 1; i < n; i++){
			for(int j = 1; j < m; j++){
				accumlateDistance = Math.min(Math.min(D[i - 1][j], D[i  - 1][j - 1]), D[i][j - 1]);
				accumlateDistance += d[i][j];
				D[i][j] = accumlateDistance;
			}
		}
		accumlateDistance = D[n - 1][m - 1];
		
		int i = n - 1;
		int j = m - 1;
		int minIndex = 1;
		
		warpingPath[k - 1][0] = i;
		warpingPath[k - 1][1] = j;
		
		while((i + j) != 0){
			if(i == 0){
				j -= 1;
			}else if(j == 0){
				i -= 1;
			}else{
				double[] array = {D[i - 1][j], D[i][j - 1], D[i - 1][j - 1]};
				minIndex = this.getIndexOfMinium(array);
				if(minIndex == 0){
					i -= 1;
				}else if(minIndex == 1){
					j -= 1;
				}else if(minIndex == 2){
					i -= 1;
					j -= 1;
				}
			}
			k++;
			warpingPath[k - 1][0] = i;
			warpingPath[k - 1][1] = j;
		}
		warpingDistance = accumlateDistance/k;
		this.reversePath(warpingPath);
		
	}
	
	//reversePath
	public void reversePath(int[][] path){
		int[][] newPath = new int[k][2];
		for(int i = 0; i < k; i++){
			for(int j = 0; j < 2; j++){
				newPath[i][j] = path[k - i - 1][j];
			}
		}
		warpingPath = newPath;
	}
	
	//distanceBetween
	public double distanceBetween(double p1, double p2){
		return (p1 - p2) * (p1 - p2);
	}
	
	//get Index of minium
	public int getIndexOfMinium(double[] array){
		int index = 0;
		double val = array[0];
		for(int i = 1; i < array.length; i++){
			if(array[i] < val){
				val = array[i];
				index = i;
			}
		}
		return index;
	}
	
	public String toString(){
		String reVal = "Warping distance: " + warpingDistance + "\n";
		reVal += "Warping Path: {";
		for (int i = 0; i < k; i++){
			reVal += "(" + warpingPath[i][0] + ", " + warpingPath[i][1] + ")";
			reVal += (i == k - 1) ? "}" : ",";
		}
		return reVal;
	}
	
}
