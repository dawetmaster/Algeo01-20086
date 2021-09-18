public class InversMethod {
    //atribut
    //method
    public static Matriks makeIdentity(int dimension){
        //menghasilkan matriks identitas
        //prekondisi: nbaris=nkolom
        Matriks m = new Matriks(dimension,dimension);
        for(int i=0;i<m.Nbaris;i++){
            for(int j=0;j<m.Nkolom;j++){
                m.matriks[i][j] = (i==j)? 1 : 0;
            }
        }
        return m;
    }
    public static Matriks solve(Matriks a,Matriks b) {
        Matriks hasil = new Matriks(a.Nbaris, 1);
        Matriks invers = invers(a);
        //solusi : x = invers(A)*b
        hasil = Matriks.kali(invers,b);
        return hasil;
    }
    /* TODO:Membuat fungsi inversnya menggunakan gauss jordan*/
    private static Matriks invers(Matriks m){
        int i,j;
        Matriks invers = m.cloneMatriks();
        Matriks identity = makeIdentity(m.Nkolom);
        //cek baris pertama yang bernilai 0,lalu swap
        int target = invers.Nbaris-1;//baris terakhir buat target swap
        for(i=0;i<m.Nbaris;i++){
            if(isAllZero(invers.matriks[i])) {
                return null;//kalau ada elemen yang 0 semua berarti gak punya invers
            }
        }
        //melakukan proses invers dengan metode gauss-jordan
                /*
                double[] temp;
                //swap matriks yg mau diinvers
                temp = invers.matriks[i];
                invers.matriks[i] = invers.matriks[target];
                invers.matriks[target] = temp;
                //swap matriks identitas
                temp = identity.matriks[i];
                identity.matriks[i] = identity.matriks[target];
                identity.matriks[target] = temp;
                target--;
               }
            }
        }
        if(target!=invers.Nbaris-1){
            //kalau ada swap,otomatis gak ada inversnya,soalnya ada elemen yang 0 semua
            return null;
        }*/
        return invers;
    }
    private static boolean isAllZero(double[] row){
        //menghasilkan true jika semua elemen di baris bernilai 0
        boolean isAllZero = true;
        int i=0;
        while(i<row.length && isAllZero){
            if(row[i]!=0){
                isAllZero = false;
            }
            else{
                i++;
            }
        }
        return isAllZero;
    }
}
