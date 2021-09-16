public class Matriks {
    //Atribut
    int Nbaris;
    int Nkolom;
    double[][] matriks;
    //method
    //konstruktor
    public Matriks(int nbaris,int nkolom){
        /* Kontruktor matriks*/
        this.Nbaris = nbaris;
        this.Nkolom = nkolom;
        this.matriks = new double[Nbaris][Nkolom];
        //inisialisasi matriks
        for (int i = 0; i < this.Nbaris; i++)
            for (int j = 0; j < this.Nkolom; j++)
                matriks[i][j] = 0;
    }
    /*NOTE: Belum bikin input dari GUI nya
    public void readMatriks() {*/
        /* I.S. semua elemen matriks bernilai 0*/
        /* F.S. Matriks terisi elemen */
     /*   for (int i = 0; i < this.Nbaris; i++)
            for (int j = 0; j < this.Nkolom; j++)
                //NOTE:isi dengan input
                matriks[i][j] = 0;
    }
    */
    public void writeMatriks(){
        /* I.S. matriks terdefinisi*/
        /* F.S. mencetak matriks ke layar*/
        for(int i=0;i<this.Nbaris;i++) {
            for (int j = 0; j < this.Nkolom; j++)
                System.out.print(matriks[i][j] + " ");
            System.out.println("");
        }
    }
}
