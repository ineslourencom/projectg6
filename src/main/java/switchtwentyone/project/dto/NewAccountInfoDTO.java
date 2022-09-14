package switchtwentyone.project.dto;


public class NewAccountInfoDTO {
    private String email;
    private String name;
    private String function;
    private String password;
    private String photo;



public NewAccountInfoDTO(String email, String name, String function, String password, String photo){
this.email=email;
this.name=name;
this.function=function;
this.password=password;
this.photo=photo;
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

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewAccountInfoDTO)) return false;
        NewAccountInfoDTO that = (NewAccountInfoDTO) o;
        return email.equals(that.email) && name.equals(that.name) && function.equals(that.function) && password.equals(that.password) && photo.equals(that.photo);
    }
}
