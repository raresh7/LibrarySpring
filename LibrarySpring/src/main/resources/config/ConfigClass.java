package config;

import dao.UserServiceImpl.UserServiceFactory;

@Confguration
public class ConfigClass {
    
    @Bean(name="userService")
    public UserService getUserService(){
        return UserServiceFactory.getLocalUserService();
    }
    
    @Bean(name="bookService")
    public BookService getBookService(){
        return new BookServiceImpl();
    }
    
    @Bean(name="transService")
    public TransactionService getTransactionService(){
        return new TransactionServiceImpl();
    }
}
