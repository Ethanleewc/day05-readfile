package milton;

public class Books implements Comparable<Books> {

    private String publisher;
    private String title;

    public Books(String publisher, String title) {
        this.publisher = publisher;
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "\n" + publisher + ":  " + title;
    }


    @Override
    public int compareTo(Books o) {
        Books comparingTo = (Books)o;
        int difference = this.toString().compareTo(comparingTo.toString());
        return difference;
    }
}
