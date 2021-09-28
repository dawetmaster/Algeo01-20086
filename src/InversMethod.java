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
        if(invers!=null) {
            hasil = Matriks.kali(invers, b);
            return hasil;
        }
        return null;
    }
    /* TODO:bugfixing and testing */
    public static Matriks invers(Matriks m){
        //menggunakan metode gauss jordan
        int i,j;
        Matriks invers = m.cloneMatriks();//matriks awal
        Matriks identity = makeIdentity(m.Nkolom);//matriks identitas yang akan diubah meenjadi invers
        //cek baris pertama yang bernilai 0,lalu swap
        int target = invers.Nbaris-1;//baris terakhir buat target swap
     //   while(!invers.isIdentity()){//selama si m bukan matriks identitas
        //membuat matriks segitiga atas
        for(i=0;i<invers.Nkolom-1;i++){
            for(j=i+1;j<invers.Nbaris;j++){
                double koef = invers.matriks[j][i]/invers.matriks[i][i];
                if(Double.isNaN(koef)){
                    continue;//kalau si koef NaN berarti gak perlu ada iterasi
                }
                for(int k=0;k<invers.Nkolom;k++){//mengapplynya ke tiap baris
                    invers.matriks[j][k] -= (koef*invers.matriks[i][k]);
                    identity.matriks[j][k] -= (koef*identity.matriks[i][k]);
                    System.out.println(MatriksBalikanMethod.MatrikstoString(invers));
                    System.out.println();
                }
                if(isAllZero(invers.matriks[j])){
                    return null;
                }
            }
        }
        System.out.println(MatriksBalikanMethod.MatrikstoString(invers));
            //membuat matriks segitiga bawah
            for(i= invers.Nkolom-1;i>0;i--){
                for(j= i-1;j>=0;j--){
                    double koef = invers.matriks[j][i]/invers.matriks[i][i];
                    if(Double.isNaN(koef)){
                        continue;//kalau si koef NaN berarti gak perlu ada iterasi
                    }
                    for(int k=0;k<invers.Nkolom;k++){//mengapplynya ke tiap baris
                        invers.matriks[j][k] -= (koef*invers.matriks[i][k]);
                        identity.matriks[j][k] -= (koef*identity.matriks[i][k]);
                    }
                    if(isAllZero(invers.matriks[j])){
                        return null;
                    }
                }
            }
        System.out.println(MatriksBalikanMethod.MatrikstoString(invers));
            //menormalkan elemen diagonal matriks identitas
            for(int k=0;k<invers.Nbaris;k++) {
                double factor = 1/invers.matriks[k][k];
                for (int l = 0; l < invers.Nkolom; l++) {
                    identity.matriks[k][l] *= factor;
                    invers.matriks[k][l] *= factor;
                }
            }
       // }
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
        for(i=0;i<m.Nbaris;i++){
            if(isAllZero(invers.matriks[i])) {
                return null;//kalau ada elemen yang 0 semua berarti gak punya invers
            }
        }
        if(invers.isIdentity()){
            System.out.println("Ahoy kawan");
            System.out.println(MatriksBalikanMethod.MatrikstoString(invers));
            System.out.println(MatriksBalikanMethod.MatrikstoString(identity));
        }
        //kalau gak ada baris yg 0 semua berarti ada inversnya
            return identity;
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
