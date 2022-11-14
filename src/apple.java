import java.util.Arrays;
import java.util.Random;

public class apple{
		int board_size = 16;
		Random ran = new Random();
		int[] pos = {ran.nextInt(board_size) + 1, ran.nextInt(board_size) + 1};
		
		public void Init(snake s) {
			while((pos[0] == s.shead[0] && pos[1] == s.shead[1]) || (Arrays.stream(s.stail).anyMatch(x -> x[0] == pos[0] && x[1] == pos[1]))) {
				pos[0] = ran.nextInt(board_size) + 1;
				pos[1] = ran.nextInt(board_size) + 1;		
			}
		}
		
 	}
