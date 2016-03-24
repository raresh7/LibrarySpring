import dao.UserService;
import dao.UserServiceImpl.UserServiceFactory;

public class JavaTesting {

    public static void main(String[] args) {
        
        UserService serv = UserServiceFactory.getLocalUserService();
        //serv.persistUser();
        System.out.println(serv.getUser("user2").getAddress());
    }

}
