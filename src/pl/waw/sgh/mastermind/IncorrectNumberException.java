package pl.waw.sgh.mastermind;

public class IncorrectNumberException extends Exception {
    IncorrectNumberException(String num){
        super(num);
    }
}
