package app.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/wallets")
public class WalletController {

    @GetMapping
    public ModelAndView getWalletPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("wallets");

        return modelAndView;
    }
}
