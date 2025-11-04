package app.web.controller;

import app.transaction.model.Transaction;
import app.transaction.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ModelAndView getTransactions(HttpSession httpSession) {
        UUID userId = (UUID) httpSession.getAttribute("user_id");
        List<Transaction> allUserTransactions = transactionService.getAllByUserId(userId);

        ModelAndView transactionPage = new ModelAndView();
        transactionPage.setViewName("transactions");
        transactionPage.addObject("transactions", allUserTransactions);

        return transactionPage;
    }

    @GetMapping("/{id}")
    public ModelAndView getTransaction(@PathVariable UUID id) {
        Transaction transaction = transactionService.getById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transaction-result");
        modelAndView.addObject("transaction", transaction);

        return modelAndView;
    }
}
