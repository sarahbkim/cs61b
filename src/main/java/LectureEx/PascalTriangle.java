/**
 * Created by sarahbkim on 2/11/15.
 */
public class PascalTriangle {

    public static int[][] pascalTriangle(int n) {
        int[][] pt = new int[n][];
        for (int i = 0; i < n; i++) {
            pt[i] = new int[i + 1];                            // Construct row i.
            pt[i][0] = 1;                              // Leftmost value of row i.
            for (int j = 1; j < i; j++) {
                pt[i][j] = pt[i - 1][j - 1] + pt[i - 1][j];  // Sum 2 entries above.
            }
            pt[i][i] = 1;                             // Rightmost value of row i.
        }

        return pt;
    }


    public static void main(String[] args) {
        PascalTriangle test = new PascalTriangle();
        System.out.println(test.pascalTriangle(3));

    }
}
