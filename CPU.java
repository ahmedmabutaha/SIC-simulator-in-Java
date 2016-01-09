
import java.util.Hashtable;
import javax.swing.JOptionPane;


public class CPU extends reg {
      static  private  String [][] fram;
      static  private  String [] ram;
      static int i=0;
      static int count=0;
      static reg reg=new reg();
      static String [] arr = new String[4];

    public CPU(String[][] fram, String[] ram) {
        CPU.ram = ram;
        CPU.fram = fram;
    }
    
    public static void step(simulator aThis)
    {   
        String key = null;
        try{
            arr[0]= "";arr[1]= "";arr[2] = "";
            for (int j = 0; j < 3; j++) {
               
                    if(ram[i] == (null)){
                        i++;j=j-1;
                    }else{
                    String bin = converts.hexToBin(ram[i]);
                    creatints();
                        if(j == 0){
                              key = ints.get(bin).toString();
                              
                        }else{
                              arr[1] += ram[i];
                              arr[2] = (i+1)+"";
                        }
                    
                    arr[0] += bin;
                    
                    i++;
                    }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "end of object", "Finish", 2);
        }
        if("HLT".equals(key)){
        findIns(key, "0000");
        simulator.stepbtn.setEnabled(false);
        }else{
            findIns(key, arr[1]);
        
        }
         
          reg.setPc(Integer.parseInt(arr[2]));
          simulator.IR.setText(arr[0]);
          simulator.pc.setText(converts.intToHex(Integer.parseInt(arr[2])));
    }

    public static void full(simulator aThis)
    { 
        String key = null;
        i=0;
        int countIns=0;
        try{
         for(int n=0;n<=ram.length;n++){
             arr[0]= "";arr[1]= "";arr[2] = "";
            for (int jf = 0; jf < 3; jf++) {
               
                    if(ram[i] == (null)){
                        i++;jf=jf-1;
                    }else{
                    String bin = converts.hexToBin(ram[i]);
                    creatints();
                        if(jf == 0){
                              key = ints.get(bin).toString();
                        }else{
                              arr[1] += ram[i];
                              arr[2] = (i+1)+"";
                        }
                    
                    arr[0] += bin;
                    
                    i++;
                    }
            }
          if("HLT".equals(key)){
                findIns(key, "0000");
                simulator.fullbtn.setEnabled(false);
                simulator.stepbtn.setEnabled(false);
            }else{
            findIns(key, arr[1]);
            }
          reg.setPc(Integer.parseInt(arr[2]));
          simulator.IR.setText(arr[0]);
          simulator.pc.setText(converts.intToHex(Integer.parseInt(arr[2])));
         }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "end of object");
            
        }
    }    
    
    private static void findIns(String key, String value){
        String g="";
        int new_value;
        boolean xreg = xreg();
        
        String vae = value.substring(1, 4);
        
        new_value = Integer.parseInt(vae, 16);
        
        for(int x=0;x<3;x++){
                   g+= fram[new_value+x][1];                  
        }
        switch (key) {
          case "LDA":
              if(xreg){reg.setAc(Integer.parseInt(g)+reg.getX());}
              else{reg.setAc(Integer.parseInt(g));}
              simulator.AC.setText(g);
              break;
          case "ADD":
              int add;
              if(xreg){add = reg.getAc()+Integer.parseInt(g)+reg.getX();}
              else{add = reg.getAc()+Integer.parseInt(g);}
              reg.setAc(Integer.parseInt(String.format("%06d", add)));
              simulator.AC.setText(String.format("%06d", add));
              break;
          case "SUB":
              int sub;
              if(xreg){sub = reg.getAc()-Integer.parseInt(g)-reg.getX();}
              else{sub = reg.getAc()-Integer.parseInt(g);}
              reg.setAc(Integer.parseInt(String.format("%06d", sub)));
              simulator.AC.setText(String.format("%06d", sub));
              break;
          case "MUL":
              int mul;
              if(xreg){mul = reg.getAc()*Integer.parseInt(g)*reg.getX();}
              else{mul = reg.getAc()*Integer.parseInt(g);}
              reg.setAc(Integer.parseInt(String.format("%06d", mul)));
              simulator.AC.setText(String.format("%06d", mul));
              break;
          case "DIV":
              int div;
              if(xreg){div = reg.getAc()/Integer.parseInt(g)/reg.getX();}
              else{div = reg.getAc()/Integer.parseInt(g);}
              reg.setAc(Integer.parseInt(String.format("%06d", div)));
              simulator.AC.setText(String.format("%06d", div));
              break;
          case "STA":
              int AC,c1=0,c2=2;String new_AC;
              
              if(xreg){AC = reg.getAc()+reg.getX();}
              else{AC = reg.getAc();}
              
              String AC4ram = String.format("%06d", AC);
              for(int n=0;n<3;n++){
                  new_AC = AC4ram.substring(c1,c2);
                  simulator.model.setValueAt(new_AC, new_value+n, 1);
                  fram[new_value+n][1]=new_AC;
                  c1=c1+2;c2=c2+2;
              }
              break;
          case "HLT":
              JOptionPane.showMessageDialog(null, "End of object");
              
              break;
          case "LDB":
              break;
          case "LDL":
              if(xreg){reg.setL(Integer.parseInt(g)+reg.getX());}
              else{reg.setL(Integer.parseInt(g));}
              simulator.L.setText(g);
              break;
          case "LDX":
              reg.setX(Integer.parseInt(g));
              simulator.x.setText(g);
              break;
          case "LDCH":
              int LDCH;
              String g1;    
              int new_value1 = Integer.parseInt(vae, 16);
                     
              if(xreg){
               g1   = fram[new_value1+reg.getX()][1];
               reg.setAc(Integer.parseInt(g1));
              simulator.AC.setText(g1+"");
              break;
            
              }
              else{ LDCH = Integer.parseInt(g.substring(0, 2));
              reg.setAc(LDCH);
              simulator.AC.setText(LDCH+"");
              break;  
              }
                
          case "STCH":
              if(xreg){
              fram[new_value][1]=reg.getAc()+"";
              simulator.model.setValueAt(reg.getAc(), new_value+reg.getX(), 1);
              }else{
              fram[new_value][1]=reg.getAc()+"";
              simulator.model.setValueAt(reg.getAc(), new_value, 1);
              }
              break;
          case "STL":
              int STL,cu1=0,cu2=2;String new_L;
              if(xreg){STL = reg.getL()+reg.getX();}
              else{STL = reg.getL();}

              String L4ram = String.format("%06d", STL);
              for(int n=0;n<3;n++){
                  new_L = L4ram.substring(cu1,cu2);
                  simulator.model.setValueAt(new_L, new_value+n, 1);
                  fram[new_value+n][1]=new_L;
                  cu1=cu1+2;cu2=cu2+2;
              }
              break;
          case "STX":
              int STX,co1=0,co2=2;String new_X;

              STX = reg.getX();

              String X4ram = String.format("%06d", STX);
              for(int n=0;n<3;n++){
                  new_X = X4ram.substring(co1,co2);
                  simulator.model.setValueAt(new_X, new_value+n, 1);
                  fram[new_value+n][1]=new_X;
                  co1=co1+2;co2=co2+2;
              }
              break; 
          case "TIX":
              char tix;
              int newX = reg.getX()+1;

              if(newX < Integer.parseInt(g)){
                  tix = '<';
              }else if(newX > Integer.parseInt(g)){ tix = '>'; }else{
               tix = '=';
              }
              
              reg.setSw(tix);
              reg.setX(newX);
              simulator.sw.setText(tix+"");
              simulator.x.setText(String.format("%06d", newX));
              break;
           case "JLT":
               int to_pc;
              if(reg.getSw() == '<'){
                  to_pc = Integer.parseInt(vae, 16);
                  i = to_pc;
                  simulator.pc.setText(to_pc + "");
              }else{
                    break;
              }
              break;
           case "JEQ":
               int topc;
              if(reg.getSw() == '='){
                  topc = Integer.parseInt(vae, 16);
                  i = topc;
                  simulator.pc.setText(topc + "");
              }else{
                    break;
              }
              break;
           case "JGT":
               int topc1;
              if(reg.getSw() == '>'){
                  topc1 = Integer.parseInt(vae, 16);
                  i = topc1;
                  simulator.pc.setText(topc1 + "");
              }else{
                    break;
              }
              break; 
           case "J":
                  i = Integer.parseInt(value, 16);
                  reg.setPc(i);
                  simulator.pc.setText(i + "");
                break;               
           case "AND":
              int and, m2bin, newAnd;
              if(xreg){and = reg.getAc()+reg.getX();}
              else{and = reg.getAc();}
              m2bin = Integer.parseInt(converts.hexToBin(g));
              newAnd = Integer.parseInt(converts.hexToBin(and+"")) & m2bin;
              newAnd = converts.binaryTodec(newAnd+"");
             
              reg.setAc(newAnd);
              simulator.AC.setText(String.format("%06d", newAnd));
              break;
           case "OR":
              int or, me2bin, newOr;
              if(xreg){or = reg.getAc()+reg.getX();}
              else{or = reg.getAc();}
              me2bin = Integer.parseInt(converts.hexToBin(g));
              newOr = Integer.parseInt(converts.hexToBin(or+"")) | me2bin;
              newOr = converts.binaryTodec(newOr+"");
             
              reg.setAc(newOr);
              simulator.AC.setText(String.format("%06d", newOr));
            break;    
          case "COMP":
              char comp;
              
              if(reg.getAc() < Integer.parseInt(g)){
                  comp = '<';
              }else if(reg.getAc() > Integer.parseInt(g)){ comp = '>'; }else{
               comp = '=';
              }
              
              reg.setSw(comp);
              simulator.sw.setText(comp+"");
              break;
          case "RSUB":
              int rsub;
              if(xreg){rsub = reg.getL()+reg.getX();}
              else{rsub = reg.getL();}
              i = rsub;
              reg.setPc(rsub);
              simulator.pc.setText(rsub + "");
              break;
          case "JSUB":
              int jsub;
              if(xreg){jsub = reg.getPc()+reg.getX();}
              else{jsub = reg.getPc();}
              reg.setL(jsub);
              i = Integer.parseInt(value, 16);
              reg.setPc(i);
              simulator.pc.setText(i + "");
              break;
      }
    }     
    
    private static boolean xreg() {
        char checkOFx = arr[0].charAt(8);
          return checkOFx == '1';
    }
    
    private static final  Hashtable  ints = new Hashtable ();
     static void creatints()
    {   
    instruction ADD= new instruction("ADD","00011000");
    instruction AND =new instruction("AND", "01000000");
    instruction COMP=new instruction("COMP", "00101000");
    instruction DIV=new instruction("DIV", "00100100");
    instruction J=new instruction("J", "00111100");
    instruction JEQ=new instruction("JEQ","00110000");
    instruction JGT=new instruction("JGT","00110100");
    instruction JLT=new instruction("JLT", "00111000");
    instruction JSUB=new instruction("JSUB", "00111000");
    instruction LDA= new instruction("LDA","00000000");
    instruction LDB= new instruction("LDB","01101000");
    instruction LDCH= new instruction("LDCH","01010000");
    instruction LDL= new instruction("LDL","00001000");
    instruction LDX= new instruction("LDX","00000100");
    instruction MUL= new instruction("MUL","00100000");
    instruction OR= new instruction("OR","01000100");
    instruction RSUB= new instruction("RSUB","01001100");
    instruction STA= new instruction("STA","00001100");
    instruction STB= new instruction("STB","01111000");
    instruction STCH= new instruction("STCH","01010100");
    instruction STI= new instruction("STI","11010100");
    instruction STL= new instruction("STL","00010100");
    instruction STX= new instruction("STX","00010000");
    instruction SUB= new instruction("SUB","00011100");
    instruction TIX= new instruction("TIX","00101100");
    instruction HLT= new instruction("HLT","11111100");
    instruction [] list = new instruction[30];
    ints.put("00011000", ADD);
    ints.put("01000000", AND);
    ints.put("00101000", COMP);
    ints.put("00100100",DIV );
    ints.put("00111100",J );
    ints.put("00110000", JEQ);
    ints.put("00110100", JGT);
    ints.put("00111000", JLT);
    ints.put("01001000", JSUB);
    ints.put("00000000",LDA);
    ints.put("01101000",LDB);
    ints.put("01010000",LDCH);
    ints.put("00001000",LDL);
    ints.put("00000100",LDX);
    ints.put("00100000",MUL);
    ints.put("01000100",OR);
    ints.put("01001100",RSUB);
    ints.put("00001100",STA);
    ints.put("01111000",STB);
    ints.put("01010100",STCH);
    ints.put("11010100",STI);
    ints.put("00010100",STL);
    ints.put("00010000",STX);
    ints.put("00011100",SUB);
    ints.put("00101100",TIX);
    ints.put("11111100",HLT);
   

    }
}
