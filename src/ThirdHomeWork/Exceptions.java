package ThirdHomeWork;

 class Exceptions extends Exception {
    public Exceptions(String message) {
        super(message);
    }
}
 class CheckVolumePersonData extends RuntimeException{
    public CheckVolumePersonData(String message){
        super(message);
    }
 }