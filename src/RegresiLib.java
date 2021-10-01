public class RegresiLib {
    public static double sigma2Var(double[]var1,double[]var2){
        //prekondisi:panjang var 1 dan var 2 sama
        double sigma = 0;
        for(int i=0;i<var2.length;i++){
            sigma += (var1[i]*var2[i]);
        }
        return sigma;
    }
}
