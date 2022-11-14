import java.util.Arrays;

public class snake{
		int board_size = 16;
		int[] shead = {(int) board_size / 2, (int) board_size / 2};
		int[][] stail = new int[(board_size * board_size)][2];
		int alive = 1;
		int snakeLength = 1;
		
		public void Init() {
			for(int i = 0; i < board_size * board_size; i++){
			for(int j = 0; j < 2; j++)
					stail[i][j] = 0;
				}
		}
		

		public void checkDeadlyMove(int[] direction) {
			int[] newPos = {shead[0] + direction[0], shead[1] + direction[1]};
			if(newPos[0] == 0 || newPos[1] == 0 || newPos[0] == board_size + 1 || newPos[1] == board_size + 1 || (Arrays.stream(stail).anyMatch(x -> x[0] == newPos[0] && x[1] == newPos[1]))) {
				alive = 0;
		    }	
		}
		
		public void move(int[] direction, boolean appleMatch) {
			int[] newPos = {shead[0] + direction[0], shead[1] + direction[1]};
			
			if(appleMatch) {
				snakeLength += 1;
				
				if (snakeLength == board_size * board_size - 1) {
					alive = 2;
				}

				
				for(int i = snakeLength - 2; i > 0; i--){
						stail[i][0] = stail[i-1][0];
						stail[i][1] = stail[i-1][1];
					}
					
					stail[0][0] = shead[0];
					stail[0][1] = shead[1];
					shead[0] = newPos[0];
					shead[1] = newPos[1];
				
				
			}
			
			else {
				
				for(int i = snakeLength - 2; i > 0; i--){
						stail[i][0] = stail[i-1][0];
						stail[i][1] = stail[i-1][1];
				}
					
				if (snakeLength > 1) {
					   stail[0][0] = shead[0];
					   stail[0][1] = shead[1];
				}
					
				shead[0] = newPos[0];
				shead[1] = newPos[1];
				}
			}

		}
		
	
	
	
