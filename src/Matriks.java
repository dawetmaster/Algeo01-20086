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
    //Input/Output
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
    public void writeMatriks(){//Note: Belum dikonekin sama gui
        /* I.S. matriks terdefinisi*/
        /* F.S. mencetak matriks ke layar*/
        for(int i=0;i<this.Nbaris;i++) {
            for (int j = 0; j < this.Nkolom; j++)
                System.out.print(matriks[i][j] + " ");
            System.out.println();
        }
    }
    /* Predikat */
    public boolean isIdentity(){
        //menghasilkan true jika Matriks ini adalah matriks identitas,yakni elemen di diagonal utama semuanya bernilai true
        //kamus lokal
        boolean isIdentity;//menghasilkan true jika Matriks ini matriks identitas
        int i,j;//variabel looping
        //Algoritma
        isIdentity = true;
        i=0;
        j=0;
        while(i<this.Nbaris && isIdentity){
            while(j<this.Nkolom && isIdentity){
                if(i==j) {//untuk elemen diagonal utama
                    if(Double.compare(this.matriks[i][j],1.0)!=0)//kalau nilai nya sama dengan 0 berarti nilai si elemen matriksnya 1. Ada di dokumentasi fungsinya
                        isIdentity = false;
                }
                else{
                    if(this.matriks[i][j]!=0){
                        isIdentity = false;
                    }
                }
                j++;
            }
            i++;
        }
        return isIdentity;
    }
    /* Fungsi dan Prosedur*/
    //* TODO: cek fungsi ini bila sudah ada fungsi untuk input
    public static Matriks kali(Matriks m1,Matriks m2){
        //fungsi yang melakukan perkalian matriks antara m1 dan m2
        //prekondisi: m1.NBaris = m2.NKolom
        Matriks mat = new Matriks(m1.Nbaris,m2.Nkolom);
        for(int i=0;i<mat.Nbaris;i++){
            for(int j=0;j<mat.Nkolom;j++){
                double sum_ = 0;
                for(int k=0;k<m1.Nkolom;k++)
                    sum_+= (m1.matriks[i][k] * m2.matriks[k][j]);
                mat.matriks[i][j] = sum_;
            }
        }
        return mat;
    }

    public double determinantReduction () {
        /* I.S. jumlah baris dan jumlah kolom harus sama */
        /* F.S. diperoleh determinan */
        if (this.Nbaris == this.Nkolom) {
            double multiplier;
            // Start dari kolom pertama
            for (int col = 0; col < this.Nkolom; col++) {
                // Loop baris
                for (int row = col + 1; row < this.Nbaris; row++) {
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
    public double determinantCofactor ( int row){
        /* I.S. jumlah baris dan jumlah kolom harus sama */
        /* F.S. diperoleh determinan */
        if (this.Nbaris == this.Nkolom) {
            // algoritma ekspansi kofaktor dengan cara rekursif
            // basis
            if (row == 2) {
                return (this.matriks[0][0] * this.matriks[1][1] - this.matriks[1][0] * this.matriks[0][1]);
            } //else {
                // rekurens, pakai yang banyak nol-nya
                // NOTE: BELUM SELESAI REKURSINYA

            //}
        }
        return Double.NaN;
    }
}
