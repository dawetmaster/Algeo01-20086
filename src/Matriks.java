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
            System.out.println("");
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
}
