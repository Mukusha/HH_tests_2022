import java.util.Scanner;
/*
* Файл прошедший проверку*/
public class Main {
    public static void main(String[] args) {

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

}
