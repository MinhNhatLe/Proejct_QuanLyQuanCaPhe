package Model;

public class ClsChiTiet {
    private String user;
    private String name;
    
    public ClsChiTiet(){
        user="User";
        name="cafe cafe";
    }
    
    public ClsChiTiet(String us, String na){
        this.user=us;
        this.name=na;
    }

    public ClsChiTiet(ClsChiTiet detail){
        this.user=detail.user;
        this.name=detail.name;
    } 
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
