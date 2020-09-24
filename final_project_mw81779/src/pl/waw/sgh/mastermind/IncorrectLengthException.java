package pl.waw.sgh.mastermind;

public class IncorrectLengthException extends Exception{
    IncorrectLengthException(String len){
        super(len);
    }
}