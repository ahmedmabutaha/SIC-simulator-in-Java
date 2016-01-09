
public class Ram {
  
    private final String [] raq;
    private final String [][] fullram= new String[32768][2];

    public Ram(String[] raq) {
        
        this.raq = raq;
        
    }

    public String[][] getFullram() {
        String addr;
        for(int i=0;i<raq.length;i++){
          addr = Integer.toHexString(i);
          fullram[i][0] = addr;
          fullram[i][1] = raq[i];
        }
        return fullram;
    }
    public String[] getRaq() {
        return raq;
    }
}

