package dao;

import java.time.LocalDate;
import java.util.List;

import entities.Transaction;

public interface TransactionService {
    public List<Transaction> returnActiveTrans(List<Transaction> trans);
    public void returnBooks(List<Integer> ids, LocalDate dateOfReturn);
    public List<Transaction> getAllActive();
    public List<Transaction> getAll();
    public void deleteTransaction(int id);
    public Transaction getTransaction(int id);
    public void createTransaction(Transaction trans);
    public void updateTransaction(Transaction trans);
}
