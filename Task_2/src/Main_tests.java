import java.util.ArrayList;

public class Main_tests {
    public static void main(String[] args) {
        //считать данные
     /*   Scanner in = new Scanner(System.in);
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

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print (mass[i][j]+" ");
            }
            System.out.println();
        }*/
     //   System.out.println(findMax( n,  m,  s,  mass1,  mass2));

        //тесты
        test1Prim();
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
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

    static void test1Prim(){
        int n = 5, m=4, result=9;
        int[][]mass={
                {0,1,1,0,0},
                {1,1,1,0,1},
                {1,1,0,0,1},
                {0,0,0,1,0}};

      /*  for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print (mass[i][j]+" ");
            }
            System.out.println();
        }*/
        if ( choosingLandPlot( n, m, mass) == result)        System.out.println("Тест примеры: \n        1)Все верно!");
          else System.out.println("Тест примеры: \n        1)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно:"+result);

        m=3;
        mass= new int[][]{
                {1, 1, 1, 0, 1},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 0, 1}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        2)Все верно!");
        else System.out.println("        2)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        n = 3;
        result=6;
        mass= new int[][]{
                {1,1,0},
                {1,0,0},
                {1,0,1}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        3)Все верно!");
        else System.out.println("        3)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        result=3;
        mass= new int[][]{
                {1, 0, 1},
                {1, 0, 1},
                {1, 0, 0}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        4)Все верно!");
        else System.out.println("        4)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        m=5; result=9;
        mass= new int[][]{
                {1, 0, 1},
                {0, 1, 1},
                {1, 0, 1},
                {0, 0, 0},
                {0, 1, 0}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        5)Все верно!");
        else System.out.println("        5)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        n = 5; m=3; result=15;
        mass= new int[][]{
                {1,1,1,1,1},
                {1,0,0,0,1},
                {1,0,1,0,1}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        6)Все верно!");
        else System.out.println("        6)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

    }
    static void test1(){
        int n = 3, m=2, result=0;
        int[][]mass={{0,0,0},{0,0,0}};

        if ( choosingLandPlot( n, m, mass) == result)        System.out.println("\nТест 1 (острова отсутствуют): \n        1)Все верно!");
        else System.out.println("\nТест 1: \n        1)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно:"+result);

        n = 4; m=4;
        mass= new int[][]{{0,0,0,0}, {0,0,0,0}, {0,1,0,0},{0, 0,0,0}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        2)Все верно!");
        else System.out.println("        2)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        n = 2; m=2;
        mass= new int[][]{{0,1}, {0,0}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        3)Все верно!");
        else System.out.println("        3)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);
    }

    static void test2(){
        int n = 3, m=6, result=3;
        int[][]mass={{0,0,0},{0,1,0},{0,1,0},{0,1,0},{0,0,0},{0,1,0}};

          if ( choosingLandPlot( n, m, mass) == result)        System.out.println("\nТест 2 (1 остров): \n        1)Все верно!");
           else System.out.println("\nТест 2: \n        1)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно:"+result);

        n = 4; m=4; result=4;
        mass= new int[][]{{0,0,0,0}, {0,1,1,0}, {0,0,1,0},{0, 0,0,0}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        2)Все верно!");
        else System.out.println("        2)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        n = 2; m=2; result=0;
        mass= new int[][]{{1,0}, {0,0}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        3)Все верно!");
        else System.out.println("        3)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);
    }

    static void test3(){
        int n = 3, m=6, result=3;
        int[][]mass={{0,0,0},{0,1,0},{0,1,0},{0,1,0},{0,0,0},{0,1,0}};

    /*    for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print (mass[i][j]+" ");
            }
            System.out.println();
        }*/
        if ( choosingLandPlot( n, m, mass) == result)        System.out.println("\nТест 3 (2 острова): \n        1)Все верно!");
        else System.out.println("Тест 3: \n        1)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно:"+result);

        n = 7; m=5; result=4;
        mass= new int[][]{
                {0,0,0,0,0,0,0},
                {0,1,1,0,0,0,0},
                {0,0,1,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,1}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        2)Все верно!");
        else System.out.println("        2)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        result=4;
        mass= new int[][]{
                {0,0,0,0,1,0,0},
                {0,1,1,0,1,0,0},
                {0,0,1,0,1,0,0},
                {0,0,0,0,1,0,0},
                {0,0,0,1,0,0,0}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        3)Все верно!");
        else System.out.println("        3)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        result=9;
        mass= new int[][]{
                {1,0,0,0,0,0,0},
                {1,1,1,0,1,0,0},
                {1,0,1,0,1,1,1},
                {1,0,0,0,1,1,0},
                {1,0,0,0,0,0,0}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        4)Все верно!");
        else System.out.println("        4)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        result=35;
        mass= new int[][]{
                {1,0,0,0,0,0,0},
                {1,1,1,1,1,1,1},
                {1,0,0,0,0,0,1},
                {1,0,0,0,0,0,0},
                {1,0,0,0,1,0,0}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        5)Все верно!");
        else System.out.println("        5)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        result=10;
        mass= new int[][]{
                {1,0,0,0,0,0,0},
                {1,1,1,1,1,1,1},
                {0,0,0,0,0,0,1},
                {1,1,1,1,1,0,1},
                {1,1,1,1,1,0,1}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        6)Все верно!");
        else System.out.println("        6)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        result=10;
        mass= new int[][]{
                {1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0},
                {0,0,0,0,0,0,1},
                {1,1,1,1,1,0,1},
                {1,1,1,1,1,0,0}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        7)Все верно!");
        else System.out.println("        7)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);
    }

    static void test4(){
        int n = 7, m=5, result=4;
        int[][]mass={
                {0,0,0,0,0,1,0},
                {0,1,1,0,0,0,0},
                {0,0,1,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,1}};

        if ( choosingLandPlot( n, m, mass) == result)        System.out.println("\nТест 4 (3 острова): \n        1)Все верно!");
        else System.out.println("Тест 4: \n        1)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно:"+result);

        result=6;
        mass= new int[][]{
                {0,0,0,1,1,1,1},
                {1,1,0,1,0,0,0},
                {1,0,0,0,0,1,0},
                {1,0,0,0,0,1,1},
                {1,0,0,0,0,1,1}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        2)Все верно!");
        else System.out.println("        2)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        result=3;
        mass= new int[][]{
                {0,0,1,1,1,1,0},
                {0,1,1,0,0,0,1},
                {0,1,1,0,1,0,1},
                {0,0,0,0,1,0,0},
                {1,1,1,0,0,0,0}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        3)Все верно!");
        else System.out.println("        3)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        result=35;
        mass= new int[][]{
                {1,0,0,0,0,0,1},
                {1,1,1,0,1,0,0},
                {1,0,1,1,1,1,1},
                {1,0,0,0,1,1,0},
                {1,0,1,0,0,0,1}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        4)Все верно!");
        else System.out.println("        4)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        result=2;
        mass= new int[][]{
                {1,0,0,0,0,0,0},
                {1,1,0,1,1,1,1},
                {1,0,0,0,0,0,1},
                {1,0,1,0,1,0,0},
                {1,0,1,0,1,0,0}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        5)Все верно!");
        else System.out.println("        5)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

    }

    static void test5(){
        int n = 7, m=5, result=3;
        int[][]mass={
                {0,0,0,0,1,1,1},
                {0,1,1,0,1,0,0},
                {0,0,1,0,0,0,1},
                {0,0,0,0,0,0,1},
                {0,0,0,1,1,0,1}};

        if ( choosingLandPlot( n, m, mass) == result)        System.out.println("\nТест 5 (5-6 островов): \n        1)Все верно!");
        else System.out.println("Тест 4: \n        1)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно:"+result);

        result=6;
        mass= new int[][]{
                {0,0,0,1,1,1,1},
                {1,1,0,1,0,0,0},
                {1,0,0,0,0,1,0},
                {1,0,1,0,0,1,1},
                {1,0,0,1,0,1,1}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        2)Все верно!");
        else System.out.println("        2)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        result=3;
        mass= new int[][]{
                {1,0,1,1,1,1,0},
                {0,0,1,0,0,0,1},
                {0,1,1,0,1,0,1},
                {0,0,0,0,1,0,0},
                {1,1,1,0,0,0,1}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        3)Все верно!");
        else System.out.println("        3)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        result=35;
        mass= new int[][]{
                {1,0,0,1,0,0,1},
                {1,1,0,0,0,0,0},
                {1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0},
                {1,0,1,0,1,0,1}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        4)Все верно!");
        else System.out.println("        4)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        result=2;
        mass= new int[][]{
                {1,0,0,0,0,0,0},
                {1,1,0,1,1,0,1},
                {1,0,0,0,0,0,1},
                {1,0,1,0,1,0,0},
                {1,0,0,1,0,0,1}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        5)Все верно!");
        else System.out.println("        5)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);
    }

    static void test6(){
        int n = 7, m=5, result=8;
        int[][]mass={
                {1,0,0,0,1,1,1},
                {0,1,0,1,0,0,0},
                {0,1,0,0,0,0,1},
                {0,0,0,0,0,1,0},
                {0,0,0,1,0,0,1}};

        if ( choosingLandPlot( n, m, mass) == result)        System.out.println("\nТест 6 (при равном выбераем большую площадь): \n        1)Все верно!");
        else System.out.println("Тест 6: \n        1)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно:"+result);

        result=8;
        mass= new int[][]{
                {0,0,0,1,1,1,1},
                {1,1,0,1,1,0,0},
                {1,0,0,0,0,0,0},
                {0,0,1,0,0,0,1},
                {0,0,0,1,0,1,0}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        2)Все верно!");
        else System.out.println("        2)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        result=8;
        mass= new int[][]{
                {0,0,0,1,1,1,1},
                {1,1,0,1,0,0,0},
                {1,0,0,0,0,0,0},
                {0,0,0,1,1,1,1},
                {0,0,0,1,0,1,0}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        3)Все верно!");
        else System.out.println("        3)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        result=0;
        mass= new int[][]{
                {1,0,0,1,0,0,1},
                {0,0,0,0,0,0,0},
                {0,0,0,1,0,1,0},
                {0,0,0,0,0,0,0},
                {1,0,1,0,1,0,1}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        4)Все верно!");
        else System.out.println("        4)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        result=4;
        mass= new int[][]{
                {1,0,0,0,0,0,0},
                {1,1,0,1,1,1,1},
                {1,0,0,0,0,0,0},
                {1,0,1,0,1,0,1},
                {1,0,0,0,0,0,1}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        5)Все верно!");
        else System.out.println("        5)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);
    }

    static void test7(){
        int n = 7, m=7, result=49;
        int[][]mass={
                {1, 0,1, 0, 1, 0, 1},
                {1, 0,1, 0, 1, 0, 1},
                {0, 1,0, 0, 0, 1, 0},
                {0, 1,0, 1, 0, 1, 0},
                {1, 0,1, 0, 1, 0, 0},
                {1, 0,1, 0, 1, 0, 0},
                {0, 1,0, 1, 0, 1, 0}};

        if ( choosingLandPlot( n, m, mass) == result)        System.out.println("\nТест 7 (какого-то чела): \n        1)Все верно!");
        else System.out.println("Тест 7: \n        1)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно:"+result);

        n = 7; m=9; result=16;
        mass= new int[][]{
                {1, 0, 0, 1, 0, 0, 1},
                {0, 1, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0, 0, 1},
                {0, 1, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0, 0, 1},
                {0, 1, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 1, 0},};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        2)Все верно!");
        else System.out.println("        2)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

      //  result=16;
        mass= new int[][]{
                {1, 0, 0, 1, 0, 0, 1},
                {0, 0, 1, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 0},
                {1, 0, 0, 1, 0, 0, 1},
                {0, 0, 1, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 0},
                {1, 0, 0, 1, 0, 0, 1},
                {0, 0, 1, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 0}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        3)Все верно!");
        else System.out.println("        3)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        n = 6; m=6;  result=4;
        mass= new int[][]{
                {1, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 1, 0},
                {1, 0, 0, 0, 0, 1}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        4)Все верно!");
        else System.out.println("        4)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        n = 2; m=2; result=4;
        mass= new int[][]{
                {1, 0},
                {0, 1},};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        5)Все верно!");
        else System.out.println("        5)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        n = 2; m=2; result=4;
        mass= new int[][]{
                {1, 1},
                {1, 1},};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        6)Все верно!");
        else System.out.println("        6)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        n = 5; m=3; result=3;
        mass= new int[][]{
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {1, 1, 1, 0, 1}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        7)Все верно!");
        else System.out.println("        7)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        n = 5; m=6; result=9;
        mass= new int[][]{
                {0, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 1, 0, 0},
                {1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        8)Все верно!");
        else System.out.println("        8)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        n = 3; m=2; result=2;
        mass= new int[][]{
                {1, 0, 1},
                {1, 0, 1}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        9)Все верно!");
        else System.out.println("        9)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        n = 3; m=2; result=6;
        mass= new int[][]{
                {0, 1, 0},
                {1, 0, 1},};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        10)Все верно!");
        else System.out.println("        10)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        n = 7; m=7; result=49;
        mass= new int[][]{
                {1, 0, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 1, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 1, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        11)Все верно!");
        else System.out.println("        11)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        n = 7; m=7; result=49;
        mass= new int[][]{
                {1, 0, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 1, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 1, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        12)Все верно!");
        else System.out.println("        12)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        n = 7; m=7; result=4;
        mass= new int[][]{
                {1, 0, 0, 1, 1, 1, 1},
                {0, 1, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 1, 0, 1},
                {0, 1, 0, 1, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 1},
                {0, 1, 0, 1, 0, 1, 1},
                {0, 1, 1, 1, 1, 1, 0}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        12)Все верно!");
        else System.out.println("        12)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        n = 7; m=7; result=49;
        mass= new int[][]{
                {0, 1, 0, 1, 0, 1, 1},
                {0, 1, 1, 1, 1, 1, 1},
                {0, 1, 0, 1, 0, 1, 0},
                {1, 1, 0, 0, 0, 1, 1},
                {0, 1, 0, 1, 0, 1, 0},
                {0, 1, 1, 1, 1, 1, 0},
                {1, 0, 0, 1, 0, 0, 1}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        13)Все верно!");
        else System.out.println("        13)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);

        n = 7; m=7; result=49;
        mass= new int[][]{
                {0, 1, 0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1, 0, 1},
                {0, 1, 0, 0, 0, 1, 0},
                {1, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 1, 0},
                {1, 0, 1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0, 1, 0}};
        if (choosingLandPlot( n, m, mass) == result)        System.out.println("        14)Все верно!");
        else System.out.println("        14)Получилось: "+ choosingLandPlot( n, m, mass)+" Должно: "+result);
    }
}