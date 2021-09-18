public class main {
    //METHOD
    public static void main(String[] args){
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
        if(m.isIdentity()){
            System.out.println("Mantappu");
        }
        System.out.println("Halo!");
        MainForm mat = new MainForm();
        mat.run();
    }
}
