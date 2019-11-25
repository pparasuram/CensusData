package cscc.edu;

public class Surname implements java.io.Serializable{
    private String surname;
    private double frequency;
    private int rank;

    public Surname(String surname, double frequency, int rank) {
        this.surname = surname;
        this.frequency = frequency;
        this.rank = rank;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    public void setFields(String inputLine ){
        String [] surnameDataArray = new String [4];
        surnameDataArray = inputLine.split(" +");
        setSurname(surnameDataArray[0]);
        setFrequency(Double.parseDouble(surnameDataArray[1]));
        setRank(Integer.parseInt(surnameDataArray[3]));

    }
    public void printSurname(){
        System.out.println(this.getSurname() + " " + this.getFrequency() + " " + this.getRank());
    }
}
