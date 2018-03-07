package Model;

public class NoteCreateReq{

    public String username;
    public String text;
    public String date;
    public String title;

    public NoteCreateReq(){}

    public NoteCreateReq(String username, String text, String date, String title) {
        this.username = username;
        this.text = text;
        this.date= date;
        this.title = title;
    }


}
