import Controller.LoginController;
public class app {
    public static void main (String[] args){
        start();
    }

    static void start(){
        new LoginController().openView();
    }
}

