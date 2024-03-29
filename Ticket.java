import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//All needed importations for file handling
public class Ticket {
    int seat;
    String row;
    int price;
    Person person; //Used Person class here

    public Ticket(int seat, String row, int price, Person person) {
        this.seat = seat;
        this.row = row;
        this.price = price;
        this.person = person;
    }

    //Getters
    public int getSeat() {
        return seat;
    }

    public String getRow() {
        return row;
    }

    public int getPrice() {
        return price;
    }

    public Person getPerson() { // the return type is person
        return person;
    }

    /**
     * Setter have been used because the coursework instructed to do so
     * But they have not been implemented in the code due to them having no usages.
     */
    public void setSeat(String seat){
       // this.seat=seat;
    }
    public void setRow(String row){
        this.row=row;
    }
    public void setPrice(int price){
        this.price=price;
    }
    public void setPerson(Person person){
        this.person=person;
    }

    //FILE HANDLING SECTION
    public void save(){
        try{
            File file=new File("D:\\MyDEGREE\\SOFTWARE DEV\\SfDev2\\JavaCW\\w20515441_PlaneManagement\\"+""+ getRow()+(getSeat()+1)+".txt");
            if (file.createNewFile()){
                System.out.println("File Created is: "+getRow()+(getSeat()+1)+".txt");
                FileWriter writer = new FileWriter(file);
                writer.write("Ticket Information:\n");
                writer.write("---------------------\n");
                writer.write("Seat No: " + getRow() + (getSeat()+1)+ "\n");
                writer.write("Seat price: $" + getPrice() + "\n");
              //  Person person = getPerson();
                writer.write("Passenger: " + person.getName() + " " + person.getSurname() + "\n");
                writer.write("Email: " + person.getEmail() + "\n");
                writer.close();
            }else{
                System.out.print("File already exists.");
            }
        }catch (IOException e){
            System.out.print("ERROR");
            e.printStackTrace();
        }
    }
}




