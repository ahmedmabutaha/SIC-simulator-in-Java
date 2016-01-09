
public class instruction {
    
    private String name ,opcode;
    
    public instruction(String name , String opcode)
    {
        this.name=name; 
        this.opcode=opcode;
    }

    public String getName() {
        return name;
    }

    public String getOpcode() {
        return opcode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    @Override
    public String toString() {
        return   name ;
    }
   
}
