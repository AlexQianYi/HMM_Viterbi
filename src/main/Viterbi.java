package main;


public class Viterbi {
	
	private int length = 0;
	private int[] Obverse = null;
	
	private double[][] emission = {{0.6, 0.2, 0.2}, {0.2, 0.6, 0.2}, {0.2, 0.2, 0.6}};
	private double[][] transition = {{0.6666667, 0.1666667, 0.1666667}, {0.1666667, 0.6666667, 0.1666667}, {0.1666667, 0.1666667, 0.6666667}};
	
	private int[] PredResult = null;
	
	private int[][] path = null;
	
	
	public Viterbi(int l, int[] input) {
		this.length = l;
		this.Obverse = new int[length];
		this.Obverse = input;	
		this.PredResult = new int[length];
		this.path = new int[3][length];
	}
	
	public double runViterbi() {
		double temp_result = 0.0;
		int tempLength = this.length;
		double tempPosD1 = 0.0;
		double tempPosD2 = 0.0;
		double tempPosD3 = 0.0;
		int tempLabel = 0;
		int maxPos = 0;
		
		if(tempLength == 0) {
			return temp_result;
		}else {
			
			tempLabel = this.Obverse[0]-1;
			System.out.println(tempLabel);
			tempPosD1 = emission[0][tempLabel];
			tempPosD2 = emission[1][tempLabel];
			tempPosD3 = emission[2][tempLabel];
			
			//temp_result = saveResult(tempPosD1, tempPosD2, tempPosD3, 0);
			
			tempLength --;
			
			int i = 1;
			while(tempLength > 0) {
				//System.out.println(i);
				tempPosD1 = iterViterbi(tempPosD1, tempPosD2, tempPosD3, this.Obverse[i]-1, 0, i);
				tempPosD2 = iterViterbi(tempPosD1, tempPosD2, tempPosD3, this.Obverse[i]-1, 1, i);
				tempPosD3 = iterViterbi(tempPosD1, tempPosD2, tempPosD3, this.Obverse[i]-1, 2, i);
				temp_result = saveResult(tempPosD1, tempPosD2, tempPosD3, i);
				tempLength --;
				i++;
			}
			
		}
		if(tempPosD1 >= tempPosD2 && tempPosD1 >= tempPosD3) {
			maxPos = 1;
		}else if(tempPosD2 >= tempPosD1 && tempPosD2 >= tempPosD3) {
			maxPos = 2;
		}else {
			maxPos = 3;
		}
		recursion(maxPos);
		return temp_result;
	}
	
	private double iterViterbi(double lastD1, double lastD2, double lastD3, int label, int Di, int i) {
		double result = 0.0;
		double tempPosD1Di = 0.0;
		double tempPosD2Di = 0.0;
		double tempPosD3Di = 0.0;
		
		// P(D1)*P(D1->Di)*P(label|Di)
		tempPosD1Di = lastD1 * this.transition[0][Di]*emission[Di][label];
		
		// P(D2)*P(D2->Di)*P(label|Di)
		tempPosD2Di = lastD2 * this.transition[1][Di]*emission[Di][label];
		
		// P(D3)*P(D3->Di)*P(label|Di)
		tempPosD3Di = lastD3 * this.transition[2][Di]*emission[Di][label];
		
		if(tempPosD1Di >= tempPosD2Di && tempPosD1Di >= tempPosD3Di) {
			this.path[Di][i] = 1;
		}else if(tempPosD2Di >= tempPosD1Di && tempPosD2Di >= tempPosD3Di) {
			this.path[Di][i] = 2;
		}else {
			this.path[Di][i] = 3;
		}
		
		return Math.max(tempPosD1Di, Math.max(tempPosD2Di, tempPosD3Di));
	}
	
	private void recursion(int x) {
		int temp = x;
		for(int i=length-1; i>1; i--) {
			if(this.path[0][i] == temp) {
				this.PredResult[i] = 1;
				temp = 1;
				continue;
			}else if(this.path[1][0] == temp) {
				this.PredResult[i] = 2;
				temp = 2;
				continue;
			}else {
				this.PredResult[i] = 3;
				temp = 3;
				continue;
			}
		}
	}
	
	private double saveResult(double PosD1, double PosD2, double PosD3, int label) {
		if(PosD1 >= PosD2 && PosD1 >= PosD3) {
			this.PredResult[label] = 1;
			return PosD1;
		}
		else if(PosD2 >= PosD1 && PosD2 >= PosD3) {
			this.PredResult[label] = 2;
			return PosD2;
		}else {
			this.PredResult[label] = 3;
			return PosD3;
		}
	}
	
	public static void main(String[] args) {
		
		Label_Seq read = new Label_Seq("src/main/diceSequences.txt");
		int num = 6;
		
		for (int i=0; i<num; i++) {
			int len = read.length[i];
			int[] seq = new int[len];
			System.arraycopy(read.array[i], 0, seq, 0, len);
			Viterbi vit = new Viterbi(len, seq);
			
			double result = 0.0;
			result = vit.runViterbi();
			
			for(int j=1; j<len; j++) {
				System.out.print(vit.PredResult[j]);
			}
			System.out.println("");
			
			System.out.println(result);
			System.out.println("");
		}
	}
	

}
