package com.example.expense_tracker.service;

import java.util.List;

import com.example.expense_tracker.entity.Expense;

public interface ExpenseService {

    Expense createExpense(Expense expense);

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);

    Expense updateExpense(Long id, Expense expense);

    void deleteExpense(Long id);
}