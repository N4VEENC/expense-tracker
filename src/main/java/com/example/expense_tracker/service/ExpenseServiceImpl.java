package com.example.expense_tracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.expense_tracker.entity.Expense;
import com.example.expense_tracker.exception.ResourceNotFoundException;
import com.example.expense_tracker.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Expense not found with id: " + id
                        )
                );
    }

    @Override
    public Expense updateExpense(Long id, Expense expense) {

        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Expense not found with id: " + id
                        )
                );

        existingExpense.setDescription(expense.getDescription());
        existingExpense.setAmount(expense.getAmount());
        existingExpense.setCategory(expense.getCategory());
        existingExpense.setPaymentMethod(expense.getPaymentMethod());
        existingExpense.setExpenseDate(expense.getExpenseDate());
        existingExpense.setNotes(expense.getNotes());

        return expenseRepository.save(existingExpense);
    }

    @Override
    public void deleteExpense(Long id) {

        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Expense not found with id: " + id
                        )
                );

        expenseRepository.delete(existingExpense);
    }
}