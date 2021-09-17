public class main {
    //METHOD
    public static void main(String[] args){
        Matriks m = new Matriks(3,3);
       // m.readMatriks();
        for(int i=0;i<m.Nbaris;i++){
            m.matriks[i][i] = 1.0;
        }
        m.writeMatriks();
        if(m.isIdentity()){
            System.out.println("Mantappu");
        }
        System.out.println("Halo!");
        MainForm mat = new MainForm();
        mat.run();
    }
}
