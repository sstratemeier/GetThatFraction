import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    public static void draw(Player p1, Player p2, Player currentPlayer, Matrix<Fraction> mat) {
        StringBuilder s = new StringBuilder();
        s.append(String.join(
                "\n",
                playerScore(p1),
                playerScore(p2),
                currentPlayerInfo(currentPlayer),
                "\n"
        ));

        for(int y = MAX.START_Y; y <= MAX.END_Y; y++) {
            for(int x = MAX.START_X; x <= MAX.END_X; x++) {
                if(p1.position.x == x && p1.position.y == y) {
                    s.append("  " + p1.getShortName() + "   ");
                } else if( p2.position.x == x && p2.position.y == y) {
                    s.append("  " +  p2.getShortName() + "   ");
                } else {
                    int num = mat.getValue(x, y).getNumerator().intValue();
                    int det = mat.getValue(x, y).getDenominator().intValue();
                    int numDigits = MathUtil.digits(num);
                    int detDigits = MathUtil.digits(det);
                    int maxDigits = 2;
                    if(det == 1) {
                        s.append("  " + num +  "   ");
                    } else {
                        s.append(num)
                                .append("/")
                                .append(det)
                                .append(StringUtil.repeat(" ", maxDigits * 2 - numDigits - detDigits))
                                .append(" ");
                    }
                }
            }
            s.append("\n");
        }
        System.out.println(s.toString());
    }

    private static String playerScore(Player p){
        return p.getName() + " Score: " + p.score.floatValue();
    }

    private static String currentPlayerInfo(Player p){
        return "Current Player: " + p.getName();
    }
}