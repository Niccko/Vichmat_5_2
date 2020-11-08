import java.util.Scanner;

public class Main {
    public static double mult(double[] a, double[] b){
        double c = 0;
        for (int i = 0; i < a.length; i++) {
            c += a[i]*b[i];
        }
        return c;
    }

    public static double[] multIndex(double[] a, double b){
        double c[] = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i]*b;
        }
        return c;
    }

    public static double[] subtract(double[] a, double[] b){
        double c[] = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i]-b[i];
        }
        return c;
    }

    public static double[] getColumn(double[][] mtx, int index){
        double[] c = new double[mtx.length];
        for (int i = 0; i < mtx.length; i++) {
            c[i] = mtx[i][index];
        }
        return c;
    }

    public static double[][] createFromVectors(double[] a, double[] b, double[] c){
        double[][] mtx = new double[a.length][a.length];
        for (int i = 0; i <a.length ; i++) {
            mtx[i][0] = a[i];
        }
        for (int i = 0; i <b.length ; i++) {
            mtx[i][1] = b[i];
        }
        for (int i = 0; i <c.length ; i++) {
            mtx[i][2] = c[i];
        }
        return mtx;
    }

    public static void printMatrix(double[][] mtx){
        for (int i = 0; i < mtx.length; i++) {
            for (int j = 0; j < mtx.length; j++) {
                System.out.printf("%.2f ",mtx[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] input = new double[3][3];
        double[] ans = new double[3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                input[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < 3; i++) {
            ans[i] = sc.nextInt();
        }

        double[] r1;
        double[] r2;
        double[] r3;
        double[][] t = new double[3][3];

        r1 = getColumn(input,0);
        t[0][1] = mult(r1,getColumn(input,1))/mult(r1,r1);
        r2 = subtract(getColumn(input,1),multIndex(r1,t[0][1]));
        t[0][2] = mult(r1,getColumn(input,2))/mult(r1,r1);
        t[1][2] = mult(r2,getColumn(input,2))/mult(r2,r2);
        r3 = subtract(getColumn(input,2),multIndex(r1,t[0][2]));
        r3 = subtract(r3,multIndex(r2,t[1][2]));
        double[][] r = createFromVectors(r1,r2,r3);
        printMatrix(r);
        printMatrix(t);
        double[] x = new double[3];
        for (int i = 2; i >= 0; i--) {
            x[i] = mult(getColumn(r,i),ans)/mult(getColumn(r,i),getColumn(input,i));
            ans = subtract(ans,multIndex(getColumn(input,i),x[i]));
        }
        for (int i = 0; i < 3; i++) {
            System.out.printf("%.1f ",x[i]);
        }
    }
}
