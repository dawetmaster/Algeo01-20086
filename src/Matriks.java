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

    public double determinantReduction() {
        /* I.S. jumlah baris dan jumlah kolom harus sama */
        /* F.S. diperoleh determinan */
        if (this.Nbaris == this.Nkolom) {
            double multiplier;
            // Start dari kolom pertama
            for (int col = 0; col < this.Nkolom; col++) {
                // Loop baris
                for (int row = col+1; row < this.Nbaris; row++) {
                    multiplier = this.matriks[row][col] / this.matriks[col][col];
                    // Loop pengurangan dari kolom pertama sampai kolom terakhir
                    for (int i = 0; i < this.Nkolom; i++) {
                        this.matriks[row][i] = this.matriks[row][i] - multiplier * this.matriks[col][i];
                    }
                }
            }
            // hitung hasil
            double result = 1;
            for (int i = 0; i < this.Nbaris; i++) {
                result *= this.matriks[i][i];
            }
            return result;
        } else {
            return Double.NaN;
        }
    }

    /*----------------------------------------------------------------
    * TODO: Complete this method with recursion
    ----------------------------------------------------------------*/
    public double determinantCofactor(int row) {
        /* I.S. jumlah baris dan jumlah kolom harus sama */
        /* F.S. diperoleh determinan */
        if (this.Nbaris == this.Nkolom) {
            // algoritma ekspansi kofaktor dengan cara rekursif
            // basis 
            if (row == 2) {
                return (this.matriks[0][0] * this.matriks[1][1] - this.matriks[1][0] * this.matriks[0][1]);
            } else {
                // rekurens, pakai yang banyak nol-nya
                // NOTE: BELUM SELESAI REKURSINYA
            }           
        } else {
            return Double.NaN;
        }
    }
}
