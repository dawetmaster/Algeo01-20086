public class Main {
    //METHOD
    public static void main(String[] args){
        /*
        Matriks m = new Matriks(3,3);
       // m.readMatriks();
        for(int i=0;i<m.Nbaris;i++){
            m.matriks[i][i] = 1.0;
        }
        m.writeMatriks();
        long startTime = System.nanoTime();
        System.out.println(m.determinantCofactor());
        long stopTime = System.nanoTime();
        System.out.printf("Durasi komputasi determinan dengan ekspansi kofaktor: %d ns\n", stopTime - startTime);
        startTime = System.nanoTime();
        System.out.println(m.determinantReduction());
        stopTime = System.nanoTime();
        System.out.printf("Durasi komputasi determinan dengan reduksi: %d ns\n", stopTime - startTime);
        Matriks M = new Matriks(2, 6);
        for (int i = 0; i < M.Nbaris; i++) {
            for (int j = 0; j < M.Nkolom; j++) {
                M.matriks[i][j] = i + j + 1;
            }
        }
        M.writeMatriks();
        EchelonRedux.selfReduce(M);
        M.writeMatriks();
        EchelonRedux.selfReduce(M, true);
        M.writeMatriks();
        if(m.isIdentity()){
            System.out.println("Mantappu");
        }
       // System.out.println("Hallo!");
        for(int j=4;j<m.Nkolom-1;j++){
            m.matriks[m.Nbaris-1][j] = 1;
        }
        if(!GaussMethod.isUniqueSol(m)){
            System.out.println("Nyaho....");
        }
        */
        MainForm mat = new MainForm();
        mat.run();
    }
}
