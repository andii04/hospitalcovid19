package hospital;

public class CommandCOVID19Emergency implements ICommand{
    Hospital hospital;
    public CommandCOVID19Emergency(Hospital hospital){
        this.hospital = hospital;
    }
    public void execute(){

    };
}
