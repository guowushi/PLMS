package PLMS.GUI;

/**

 */
public class UserBean  {
        private int UserID;
        private int RoleID;
        private String UserName;
        private String Password;
        private int Blongto;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int roleID) {
        RoleID = roleID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getBlongto() {
        return Blongto;
    }

    public void setBlongto(int blongto) {
        Blongto = blongto;
    }
}
