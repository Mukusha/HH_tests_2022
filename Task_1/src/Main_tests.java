import java.util.Scanner;

public class Main_tests {
    public static void main(String[] args) {
/*
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String [] zn = line.split(" ");
        int n=Integer.parseInt(zn[0]);
        int m=Integer.parseInt(zn[1]);
        int s=Integer.parseInt(zn[2]);
        int[] mass1 = new int[n];
        int[] mass2 = new int[m];
        int i=0;
        int max = Math.max(m, n);
        while(i<max){
             line = in.nextLine();
             zn = line.split(" ");
             if (i<n) mass1[i] =Integer.parseInt(zn[0]);
            if (i<m) mass2[i] =Integer.parseInt(zn[1]);
             i++;
        }
        System.out.println(findMax( n,  m,  s,  mass1,  mass2));
*/
        testRes_1();
        testRes_2();
        testRes_3();
        testRes_4();
        testRes_5();
        testRes_6();
        testRes_7();
        testRes_8();
        testRes_9();
        testRes_10();
        testRes_11();
        testRes_12();
        testRes_13();
    }

    static int findMax(int n, int m, int s, int[] mass1, int[] mass2){
        if (mass1[0]>s && mass2[0]>s) return 0;
        int sum=0, sum1, sum2,sumSt1=0,sumSt2=0; //текущаяя сумма
        int v=0, vnew = 0; //текущее кол-во резюме
        int is=0; // запомнить на каком шаге остановились

        int minNM = Math.min(n, m);
        for (int i = 0; i < minNM; i++) { // идем по стопками до тех пор пока есть пара
            sum = sum+ mass1[i]+mass2[i];
            sumSt1 = sumSt1+mass1[i];
            sumSt2 = sumSt2+mass2[i];

            if (sum>s) {
                vnew=i*2;
                if(v<vnew) v=vnew;
                is=i;
                break;}

            if (sum==s) {
                vnew=(i+1)*2;
                if(v<vnew) v=vnew;
                is=i;
                break;}

                // дальше берем резюме только с 1вой стопки
                sum1=sum;
                for (int j = i+1; j < n; j++) {
                    sum1=sum1+mass1[j];
                    if (sum1>s) {
                        vnew=(i+1)*2+(j-i-1);
                        if(v<vnew) v=vnew;
                        break;}
                    if (sum1==s) {
                        vnew=(i+1)*2+(j-i);
                        if(v<vnew) v=vnew;
                        break;}
                }
                if(sum1<s) vnew=(i+1)*2+(n-i-1);
                if(v<vnew) v=vnew;

                // дальше берем резюме только со 2рой стопки
                sum2=sum;
                for (int j = i+1; j < m; j++) {
                    sum2=sum2+mass2[j];
                    if (sum2>s) {
                        vnew=(i+1)*2+(j-i-1);
                        if(v<vnew) v=vnew;
                        break;}
                    if (sum2==s) {
                        vnew=(i+1)*2+(j-i);
                        if(v<vnew) v=vnew;
                        break;}
                }
                if(sum2<s) vnew=(i+1)*2+(m-i-1);
                if(v<vnew) v=vnew;
            }


        // возможно выгоднее брать только из 1вой стопки
        if(v<n && sumSt1<s){
            for (int i = is+1; i < n; i++) {
                sumSt1=sumSt1+mass1[i];
                if (sumSt1==s) {
                    if(v<(i+1)) v=i+1;
                    break;}
                if (sumSt1>s) {
                    if(v<i) v=i;
                    break;}
            }
            if(sumSt1<s) v=n;
        }

        // возможно выгоднее брать только из 2рой стопки
        if(v<m && sumSt2<s){
            for (int i = is+1; i < m; i++) {
                sumSt2+=mass2[i];
                if (sumSt2==s) {
                    if(v<(i+1)) v=i+1;
                    break;}
                if (sumSt2>s) {
                    if(v<i) v=i;
                    break;}
            }
            if(sumSt2<s) v=m;
        }

        return v;
    }

    public static void  testRes_1(){
        //больше чем во втором столбце, но не макс
        int n = 7, m=3, s=7, result = 6;
        int[] mass_1 = {1,2,1,1,1,1,1};
        int[] mass_2 = {42,2,23};

        if (findMax(n,m,s,mass_1,mass_2) == result)        System.out.println("Тест 1: \n        1)Все верно!");
        else System.out.println("Тест 1: \nПолучилось: "+ findMax(n,m,s,mass_1,mass_2)+" Должно:"+result);
        //1 элемент
        if (findMax(6,2,3, new int[]{2, 32,33,22,1,1}, new int[]{11, 66}) == 1)        System.out.println("        2)Все верно!");
        else System.out.println("        2)Получилось: "+ findMax(6,2,3, new int[]{2, 32,33,22,1,1}, new int[]{11, 66})+" Должно:1");
        //макс
        if (findMax(4,3,9, new int[]{2,1, 2,3}, new int[]{22,11, 66}) == 4)        System.out.println("        3)Все верно!");
        else System.out.println("        3)Получилось: "+ findMax(4,3,9, new int[]{2,1, 2,3}, new int[]{22,11, 66})+" Должно:4");
        //меньше чем во втором
        if (findMax(5,3,3, new int[]{2,1, 32,6,7}, new int[]{22,11, 66}) == 2)        System.out.println("        4)Все верно!");
        else System.out.println("        4)Получилось: "+ findMax(5,3,3, new int[]{2,1, 32,6,7}, new int[]{22,11, 66})+" Должно:2");
    }

    public static void  testRes_2(){
        //строки ровно со вторым <s
        int n = 3, m=2, s=13, result =4;
        int[] mass_1 = {2,6,65};
        int[] mass_2 = {2,2};
        if (findMax(n,m,s,mass_1,mass_2) == result)        System.out.println("Тест 2: \n        1)Все верно!");
        else System.out.println("Тест 2: \n       2)Получилось: "+ findMax(n,m,s,mass_1,mass_2)+" Должно:"+result);
        //ровно со вторым, но строк < m и их сумма <s
        if (findMax(4,3,30, new int[]{2, 2, 77, 452}, new int[]{2, 6, 33}) == 4)  System.out.println("        2)Все верно!");
        else System.out.println("\n       2)Получилось: "+ findMax(4,3,30, new int[]{2, 2, 77, 452}, new int[]{2, 6, 33})+" Должно:4");
        //1 строка <s
        if (findMax(3,2,5, new int[]{2, 32,8}, new int[]{2, 66}) == 2)        System.out.println("        3)Все верно!");
        else System.out.println("        3)Получилось: "+ findMax(3,2,3, new int[]{2, 32,8}, new int[]{2, 66})+" Должно:2");
        //1 строка =s
        if (findMax(3,2,4, new int[]{2, 32,8}, new int[]{2, 66}) == 2)        System.out.println("        4)Все верно!");
        else System.out.println("        4)Получилось: "+ findMax(3,2,3, new int[]{2, 32,8}, new int[]{2, 66})+" Должно:2");
    }

    public static void  testRes_3(){
        int n = 7, m=4, s=3, result = 3;
        int[] mass_1 = {1,1,1,1,1,1,1};
        int[] mass_2 = {1, 2, 3, 4};
        if (findMax(n,m,s,mass_1,mass_2) == result)        System.out.println("Тест 3: \n        1)Все верно!");
        else System.out.println("Тест 3: \n       2)Получилось: "+ findMax(n,m,s,mass_1,mass_2)+" Должно:"+result);

        if (findMax(7,4,6, new int[]{1,1,1,8,1,1,1}, new int[]{1, 1, 3, 4}) == 5)  System.out.println("        2)Все верно!");
        else System.out.println("\n       2)Получилось: "+ findMax(7,4,6, new int[]{1,1,1,8,1,1,1}, new int[]{1, 1, 3, 4})+" Должно:5");

        if (findMax(7,4,8, new int[]{1,1,1,1,1,8,1}, new int[]{1, 1, 3, 4}) == 7)  System.out.println("        3)Все верно!");
        else System.out.println("\n       3)Получилось: "+ findMax(7,4,8, new int[]{1,1,1,1,1,8,1}, new int[]{1, 1, 3, 4})+" Должно:7");

        if (findMax(7,4,18, new int[]{1,1,1,1,4,5,6}, new int[]{1, 2, 3, 4}) == 9)  System.out.println("        4)Все верно!");
        else System.out.println("\n       4)Получилось: "+ findMax(7,4,18, new int[]{1,1,1,1,4,5,6}, new int[]{1, 2, 3, 4})+" Должно:9");

        if (findMax(7,4,25, new int[]{1,1,1,1,4,5,6}, new int[]{1, 2, 3, 4}) == 10)  System.out.println("        5)Все верно!");
        else System.out.println("\n       5)Получилось: "+ findMax(7,4,25, new int[]{1,1,1,1,4,5,6}, new int[]{1, 2, 3, 4})+" Должно:10");

        if (findMax(7,4,30, new int[]{1,1,1,1,4,5,6}, new int[]{1, 2, 3, 4}) == 11)        System.out.println("        6)Все верно!");
        else System.out.println("        6)Получилось: "+ findMax(7,4,30, new int[]{1,1,1,1,4,5,6}, new int[]{1, 2, 3, 4})+" Должно:11");
    }

    public static void  testRes_4(){
        int n = 7, m=4, s=3, result = 3;
        int[] mass_1 = {1, 2, 3,4,5,6,7};
        int[] mass_2 = {1, 1,1,1};

        if (findMax(n,m,s,mass_1,mass_2) == result)        System.out.println("Тест 4: \n        1)Все верно!");
        else System.out.println("Тест 4: \n        1)Получилось: "+ findMax(n,m,s,mass_1,mass_2)+" Должно:"+result);

        if (findMax(7,4,5, new int[]{1, 2, 3,4,5,6,7}, new int[]{1, 1,1,1}) == 5)        System.out.println("        2)Все верно!");
        else System.out.println("        2)Получилось: "+ findMax(7,4,5, new int[]{1, 2, 3,4,5,6,7}, new int[]{1, 1,1,1})+" Должно:5");

        if (findMax(7,4,8, new int[]{1, 2, 3,4,5,6,7}, new int[]{1, 1,1,1}) == 6)        System.out.println("        3)Все верно!");
        else System.out.println("        3)Получилось: "+ findMax(7,4,8, new int[]{1, 2, 3,4,5,6,7}, new int[]{1, 1,1,1})+" Должно:6");
    }

    public static void  testRes_5(){
        int n = 7, m=7, s=8, result = 7;
        int[] mass_1 = {1,1,1,1,1,1,1};
        int[] mass_2 = {4, 2, 3, 4,2,8,3};
        //полностью
        if (findMax(n,m,s,mass_1,mass_2) == result)        System.out.println("Тест 5: \n        1)Все верно!");
        else System.out.println("Тест 5: \n        1)Получилось: "+ findMax(n,m,s,mass_1,mass_2)+" Должно:"+result);
        //1 элем
        if (findMax(2,2,3, new int[]{2, 32}, new int[]{22, 66}) == 1)        System.out.println("        2)Все верно!");
        else System.out.println("        2)Получилось: "+ findMax(2,2,3, new int[]{2, 32}, new int[]{22, 66})+" Должно:1");
        // не полностью
        if (findMax(3,3,4, new int[]{2,1, 32}, new int[]{23,11, 66}) == 2)        System.out.println("        3)Все верно!");
        else System.out.println("        3)Получилось: "+ findMax(3,3,4, new int[]{2,1, 32}, new int[]{23,11, 66})+" Должно:2");
        // сумма =
        if (findMax(6,6,3, new int[]{2, 1,33,22,1,1}, new int[]{11, 66,3,2,5,4}) == 2)        System.out.println("        4)Все верно!");
        else System.out.println("        2)Получилось: "+ findMax(6,6,3, new int[]{2, 1,33,22,1,1}, new int[]{11, 66,3,2,5,4})+" Должно:2");

    }

    public static void  testRes_6(){
        //полностью
         int n = 7, m=7, s=7, result =7;
         int[] mass_1 = {23,2,3,23,73,12,3};
         int[] mass_2 = {1,1,1,1,1,1,1};
        if (findMax(n,m,s,mass_1,mass_2) == result)        System.out.println("Тест 6: \n        1)Все верно!");
        else System.out.println("Тест 6: \n        1)Получилось: "+ findMax(n,m,s,mass_1,mass_2)+" Должно:"+result);
        // 1 элем
        if (findMax(2,2,3, new int[]{22, 32}, new int[]{2, 66}) == 1)        System.out.println("        2)Все верно!");
        else System.out.println("        2)Получилось: "+ findMax(2,2,3, new int[]{22, 32}, new int[]{2, 66})+" Должно:1");
        //2 элем
        if (findMax(3,3,4, new int[]{22,11, 32}, new int[]{2,1, 66}) == 2)        System.out.println("        3)Все верно!");
        else System.out.println("        3)Получилось: "+ findMax(3,3,4, new int[]{22,11, 32}, new int[]{2,1, 66})+" Должно:2");
        // сумма !=
        if (findMax(6,6,80, new int[]{200, 123,33,22,1,1}, new int[]{11, 66,33,2,5,4}) == 2)        System.out.println("        4)Все верно!");
        else System.out.println("        2)Получилось: "+ findMax(6,6,80, new int[]{200, 123,33,22,1,1}, new int[]{11, 66,33,2,5,4})+" Должно:2");
    }

    public static void  testRes_7(){
        int n = 4, m=4, s=3, result = 2;
        int[] mass_1 = {1,3,5,7};
        int[] mass_2 = {2,4,6,8};

        if (findMax(n,m,s,mass_1,mass_2) == result)        System.out.println("Тест 7: \n        1)Все верно!");
        else System.out.println("Тест 7: \n        1)Получилось: "+ findMax(n,m,s,mass_1,mass_2)+" Должно:"+result);

        if (findMax(n,m,22,mass_1,mass_2) == 6)        System.out.println("        2)Все верно!");
        else System.out.println("        2)Получилось: "+ findMax(n,m,13,mass_1,mass_2)+" Должно:6");

        if (findMax(n,m,36,mass_1,mass_2) == 8)        System.out.println("        3)Все верно!");
        else System.out.println("        3)Получилось: "+ findMax(n,m,36,mass_1,mass_2)+" Должно:8");
    }

    public static void  testRes_8(){
        int n = 4, m=4, s=5, result = 4;
        int[] mass_1 = {1,1,1,3};
        int[] mass_2 = {1,4,6,8};

        if (findMax(n,m,s,mass_1,mass_2) == result)        System.out.println("Тест 8: \n        1)Все верно!");
        else System.out.println("Тест 8: \n        1)Получилось: "+ findMax(n,m,s,mass_1,mass_2)+" Должно:"+result);

        if (findMax(n,m,8,mass_1,mass_2) == 5)        System.out.println("        2)Все верно!");
        else System.out.println("        2)Получилось: "+ findMax(n,m,8,mass_1,mass_2)+" Должно:5");

        if (findMax(n,m,6, new int[]{1, 1, 1, 2}, new int[]{1, 1, 2, 2}) == 5)        System.out.println("        3)Все верно!");
        else System.out.println("        3)Получилось: "+ findMax(n,m,6, new int[]{1, 1, 1, 2}, new int[]{1, 1, 2, 2})+" Должно:5");

        if (findMax(n,m,11,mass_1,mass_2) == 6)        System.out.println("        4)Все верно!");
        else System.out.println("        4)Получилось: "+ findMax(n,m,11,mass_1,mass_2)+" Должно:6");
    }

    public static void  testRes_9(){
        int n = 4, m=4, s=5, result = 3;
        int[] mass_1 = {2,4,6,8};
        int[] mass_2 = {1,2,4,1};

        if (findMax(n,m,s,mass_1,mass_2) == result)        System.out.println("Тест 9: \n        1)Все верно!");
        else System.out.println("Тест 9: \n        1)Получилось: "+ findMax(n,m,s,mass_1,mass_2)+" Должно:"+result);

        if (findMax(n,m,10,mass_1,mass_2) == 5)        System.out.println("        2)Все верно!");
        else System.out.println("        2)Получилось: "+ findMax(n,m,10,mass_1,mass_2)+" Должно:5");

        if (findMax(n,m,13,mass_1,mass_2) == 5)        System.out.println("        3)Все верно!");
        else System.out.println("        3)Получилось: "+ findMax(n,m,13,mass_1,mass_2)+" Должно:5");

        if (findMax(n,m,14,mass_1,mass_2) == 6)        System.out.println("        4)Все верно!");
        else System.out.println("        4)Получилось: "+ findMax(n,m,14,mass_1,mass_2)+" Должно:6");
    }

    public static void  testRes_10(){
        int n = 2, m=4, s=20, result =4;
        int[] mass_1 = {24,32};
        int[] mass_2 = {2,6,3,7};

        if (findMax(n,m,s,mass_1,mass_2) == result)        System.out.println("Тест 10: \n        1)Все верно!");
        else System.out.println("Тест 10: \nПолучилось: "+ findMax(n,m,s,mass_1,mass_2)+" Должно:"+result);
        //1 элемент
        if (findMax(2,6,3, new int[]{11,66}, new int[]{2, 32,33,22,1,1}) == 1)        System.out.println("        2)Все верно!");
        else System.out.println("        2)Получилось: "+ findMax(2,6,3, new int[]{11,66}, new int[]{2, 32,33,22,1,1})+" Должно:1");
        //макс
        if (findMax(3,4,9, new int[]{22,11,33}, new int[]{2,1,2, 3}) == 4)        System.out.println("        3)Все верно!");
        else System.out.println("        3)Получилось: "+ findMax(3,4,9, new int[]{22,11,33}, new int[]{2,1,2, 3})+" Должно:4");
        //меньше чем во втором
        if (findMax(3,5,3, new int[]{22,11, 66}, new int[]{2,1, 32,6,7}) == 2)        System.out.println("        4)Все верно!");
        else System.out.println("        4)Получилось: "+ findMax(3,5,3, new int[]{22,11, 66}, new int[]{2,1, 32,6,7})+" Должно:2");


    }

    public static void  testRes_11(){
        int n = 3, m=7, s=4, result = 2;
        int[] mass_1 = {1,3,5};
        int[] mass_2 = {2,4,6,7,8,9,10};

        if (findMax(n,m,s,mass_1,mass_2) == result)        System.out.println("Тест 11: \n        1)Все верно!");
        else System.out.println("Тест 11: \n        1)Получилось: "+ findMax(n,m,s,mass_1,mass_2)+" Должно:"+result);

        if (findMax(n,m,10,mass_1,mass_2) == 4)        System.out.println("        2)Все верно!");
        else System.out.println("        2)Получилось: "+ findMax(n,m,10,mass_1,mass_2)+" Должно:4");

        if (findMax(n,m,23,mass_1,mass_2) == 6)        System.out.println("        3)Все верно!");
        else System.out.println("        3)Получилось: "+ findMax(n,m,23,mass_1,mass_2)+" Должно:6");

    }

    public static void  testRes_12(){
        int n = 4, m=7, s=3, result = 3;
        int[] mass_1 = {1,1,1,1};
        int[] mass_2 = {1,2,3,4,5,6,7};

        if (findMax(n,m,s,mass_1,mass_2) == result)        System.out.println("Тест 12: \n        1)Все верно!");
        else System.out.println("Тест 12: \n        1)Получилось: "+ findMax(n,m,s,mass_1,mass_2)+" Должно:"+result);

        if (findMax(n,m,5,mass_1,mass_2) == 5)        System.out.println("        2)Все верно!");
        else System.out.println("        2)Получилось: "+ findMax(n,m,5,mass_1,mass_2)+" Должно:5");

        if (findMax(n,m,8,mass_1,mass_2) == 6)        System.out.println("        3)Все верно!");
        else System.out.println("        3)Получилось: "+ findMax(n,m,8,mass_1,mass_2)+" Должно:6");
    }

    public static void  testRes_13(){
        int n = 4, m=7, s=3, result = 3;
        int[] mass_1 = {1, 2, 3, 4};
        int[] mass_2 = {1,1,1,1,1,1,1};
        if (findMax(n,m,s,mass_1,mass_2) == result)        System.out.println("Тест 13: \n        1)Все верно!");
        else System.out.println("Тест 13: \n       2)Получилось: "+ findMax(n,m,s,mass_1,mass_2)+" Должно:"+result);

        if (findMax(4,7,6, new int[]{1, 1, 3, 4}, new int[]{1,1,1,8,1,1,1}) == 5)  System.out.println("        2)Все верно!");
        else System.out.println("       2)Получилось: "+ findMax(4,7,6, new int[]{1, 1, 3, 4}, new int[]{1,1,1,8,1,1,1})+" Должно:5");

        if (findMax(4,7,8, new int[]{1, 1, 3, 4}, new int[]{1,1,1,1,1,8,1}) == 7)  System.out.println("        3)Все верно!");
        else System.out.println("        3)Получилось: "+ findMax(4,7,8, new int[]{1, 1, 3, 4}, new int[]{1,1,1,1,1,8,1})+" Должно:7");

        if (findMax(4,7,18, new int[]{1, 2, 3, 4}, new int[]{1,1,1,1,4,5,6}) == 9)  System.out.println("        4)Все верно!");
        else System.out.println("       4)Получилось: "+ findMax(4,7,18, new int[]{1, 2, 3, 4}, new int[]{1,1,1,1,4,5,6})+" Должно:9");

        if (findMax(4,7,25, new int[]{1, 2, 3, 4}, new int[]{1,1,1,1,4,5,6}) == 10)  System.out.println("        5)Все верно!");
        else System.out.println("       5)Получилось: "+ findMax(4,7,25, new int[]{1, 2, 3, 4}, new int[]{1,1,1,1,4,5,6})+" Должно:10");

        if (findMax(4,7,30, new int[]{1, 2, 3, 4}, new int[]{1,1,1,1,4,5,6}) == 11)        System.out.println("        6)Все верно!");
        else System.out.println("        6)Получилось: "+ findMax(4,7,30, new int[]{1, 2, 3, 4}, new int[]{1,1,1,1,4,5,6})+" Должно:11");

    }

}

