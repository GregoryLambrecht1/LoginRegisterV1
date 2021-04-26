import java.util.ArrayList;
import java.util.List;

public class Users {
    private List<User> userList = new ArrayList<>();
    private List<String> userNames = new ArrayList<>();

    public Users() {
    }

    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    //method for adding the username to the list
    public void add(User user){
       userList.add(user);
       userNames.add(user.getUsername());
    }
    //for testing purpose , to see if the data got stored
    public void print(){
        System.out.println(getUserList());
        System.out.println(getUserNames());
    }

    //method to see if the username alrdy exists
    public boolean checkIfUserExistAlready(String username){
        if (userNames.contains(username)){
            return true;
        }
        return false;
    }

    //boolean gives true if the username and password are equal to the username and password in the usersList
    public boolean login(String username , String password){
        for (int i = 0; i < userList.size();i++){
            if (userList.get(i).getUsername().equals(username)){
                if (userList.get(i).getPassword().equals(password)){
                    return true;
                }
            }
        }
        return false;
    }
}
