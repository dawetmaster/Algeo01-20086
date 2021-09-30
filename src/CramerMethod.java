public class CramerMethod {

    public static Matriks solve(Matriks matA, Matriks matB) {
        //menyelesaikan SPL menggunakan metode cramer dan mencari determinan menggunakan ekspansi kofaktor
        Matriks hasil = new Matriks(matA.Nbaris,1);
        double detMat;
        detMat = matA.determinantCofactor();
        if(detMat==0){
            return null;
        }
        for(int j=0;j<matA.Nkolom;j++){
            Matriks temp = matA.cloneMatriks();
            //ganti kolom ke-i dengan matB
            for(int i=0;i<temp.Nbaris;i++){
                temp.matriks[i][j] = matB.matriks[i][0];
                System.out.println(temp.matriks[i][j]);
            }
            temp.writeMatriks();
            System.out.println("detA:"+temp.determinantCofactor());
            System.out.println("detB:"+detMat);
            hasil.matriks[j][0] = temp.determinantCofactor()/detMat;
           // System.out.println("Hasil:"+hasil.matriks[j][0]);
        }
        return hasil;
    }
}
