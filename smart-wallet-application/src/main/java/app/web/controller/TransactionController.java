package app.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping()
public class TransactionController {

    @GetMapping("/transfers")
    public ModelAndView getTransferPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transfer");

        return modelAndView;
    }

//    @GetMapping("/transactions")
//    public ModelAndView getTransactions() {
//        ModelAndView transactionPage = new ModelAndView();
//        transactionPage.setViewName("transactions");
//        transactionPage.addObject();
//    }
}
