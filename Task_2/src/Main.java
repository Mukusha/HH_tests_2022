import java.util.ArrayList;
import java.util.Scanner;
/*
* Файл прошедший проверку
*/
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String [] zn = line.split(" ");
        int n=Integer.parseInt(zn[0]);
        int m=Integer.parseInt(zn[1]);
        int[][] mass = new int[m][n];
        for (int i = 0; i < m; i++) {
            line = in.nextLine();
            zn = line.split(" ");
            for (int j = 0; j < n; j++) {
                mass[i][j] =Integer.parseInt(zn[j]);
            }
        }

        System.out.println(choosingLandPlot( n,  m, mass));

    }
    static int choosingLandPlot(int n, int m, int[][] mass){
        boolean[][] massBoolean = new boolean[m][n]; //отметка смотрели ли мы эту точку
        int[] region = {0,0}; // площать + кол-во единиц
        int[] regionNew;
        int xLeftUp, yLeftUp, xRightDown, yRightDown; // координаты региона
        ArrayList<Point> neighbours = new ArrayList<>(); // непроверенные соседние участки

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(mass[i][j]==0 || massBoolean[i][j]) continue;
                neighbours.add(new Point(i,j));
                xLeftUp = i; yLeftUp=j;
                xRightDown=i; yRightDown =j;
                while(neighbours.size()!=0) {
                    if(!massBoolean[neighbours.get(0).getX()][neighbours.get(0).getY()]){
                        massBoolean[neighbours.get(0).getX()][neighbours.get(0).getY()]=true;
                        int xStart = neighbours.get(0).getX()-1;
                        int xFin = neighbours.get(0).getX()+1;
                        if (xStart<0)  xStart = 0;
                        if (xFin>=m)  xFin = m-1;
                        int yStart = neighbours.get(0).getY()-1;
                        int yFin = neighbours.get(0).getY()+1;
                        if (yStart<0)  yStart = 0;
                        if (yFin>=n)  yFin = n-1;
                        for (int k = xStart; k <=xFin; k++) {
                            for (int l = yStart; l <=yFin; l++) {
                                if (!massBoolean[k][l] && mass[k][l] == 1) {
                                    neighbours.add(new Point(k, l));
                                    if (k < xLeftUp) xLeftUp = k;
                                    if (l < yLeftUp) yLeftUp = l;
                                    if (k > xRightDown) xRightDown = k;
                                    if (l > yRightDown) yRightDown = l;
                                }
                            }
                        }
                    }
                    neighbours.remove(0);
                }
                //  Считаем кол-во плодородных участков в регионе
                int sum=0;
                for (int k = xLeftUp; k <= xRightDown; k++) {
                    for (int l = yLeftUp; l <= yRightDown; l++) {
                        sum+=mass[k][l];
                    }
                }

                regionNew= new int[]{(xRightDown - xLeftUp+1) * (yRightDown - yLeftUp+1), sum};

                if(sum<2) continue;
                double ce;
                double ceNew;
                if(region[0]==0) region=regionNew;
                else {
                    ce=(double)region[1]/region[0];
                    ceNew = (double) regionNew[1]/regionNew[0];
                    if(ceNew>ce) {
                        region=regionNew;
                    }
                    else if (ceNew==ce) {
                        if(regionNew[0]>region[0]) region=regionNew;
                    }

                }
            }
        }
        return region[0];
    }

    static class Point{
        private final int x;
        private final int y;

        Point(int x,int y){
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}