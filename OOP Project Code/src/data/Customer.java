package data;

public class Customer extends Account{
    // private String address;
	private double[] draft;
    private String name;
	private String email;
    private String address;
    

    public Customer(){
        super();
        this.email = "Nil";
        this.name = "Nil";
    }
    public Customer(String uname, String pass){
        super(uname,pass);
        this.email = "Nil";
        this.name = "Nil";
    }
    
    public Customer(String fname, String email, String uname, String pass){
        super(uname,pass);
        this.email = email;
        this.name = fname;
    }


    public void setReadingLength(int length){
    	draft = new double[length];
    }
    
    public void addReading(int index, double readings){
        draft[index] = readings;
    }
    
    

    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
	public double[] getDraft() {
		return draft;
	}
	public void setDraft(double[] draft) {
		this.draft = draft;
	}  
}
