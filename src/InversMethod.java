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
}
