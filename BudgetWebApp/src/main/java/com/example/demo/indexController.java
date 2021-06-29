package com.example.demo;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
public class indexController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ExpenseService service;

    @Autowired
    private AccountService accountService;



//  Login / Home
    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/home")
    public String homeController(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Optional<User> currUser = userRepo.findById(userDetails.getID());
        User currentUser;
        if (currUser.isPresent()) {
            currentUser = currUser.get();

            model.addAttribute("user", currentUser );
        }

        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "register_success";
    }

//    Accounts

    @GetMapping("/new_account")
    public String newAccountPage(Model model) {
        Account account = new Account();
        model.addAttribute("a", account);

        return "new_account";
    }

    @RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
    public String saveAccount(Account a, @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.user;
        a.setUser(user);
        accountService.save(a);
        return "redirect:/home";
    }

    @GetMapping("/accounts")
    public String viewAccounts(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<Account> listAccounts = accountService.findByUserId(userDetails.getID());
        model.addAttribute("listAccounts", listAccounts);

        return "accounts";
    }

    @RequestMapping("/editAccount/{id}")
    public ModelAndView editAccount(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_account");
        Account account = accountService.get(id);
        mav.addObject("account", account);

        return mav;
    }

    @RequestMapping("/deleteAccount/{id}")
    public String deleteAccount(@PathVariable(name = "id") int id) {
        accountService.delete(id);
        return "redirect:/home";
    }



//    Expenses

    @GetMapping("/new_expense")
    public String newExpensePage(Model model) {
        Expense expense = new Expense();
        model.addAttribute("e", expense);

        return "new_expense";
    }

    @RequestMapping(value = "/saveExpense", method = RequestMethod.POST)
    public String saveExpense(Expense e, @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.user;
        e.setAuthor(user);
        service.save(e);
        return "redirect:/home";
    }

    @GetMapping("/expenses")
    public String viewExpenses(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<Expense> listExpenses = service.findByAuthorId(userDetails.getID());

        model.addAttribute("listExpenses", listExpenses);

        return "expense";
    }

    @RequestMapping("/editExpense/{id}")
    public ModelAndView editExpense(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_expense");
        Expense expense = service.get(id);
        mav.addObject("expense", expense);

        return mav;
    }

    @RequestMapping("/deleteExpense/{id}")
    public String deleteExpense(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/home";
    }


//    Balance / Deposit INFO

    @GetMapping("/new_deposit")
    public String newDepositPage(ModelMap map, @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<Account> accountList = accountService.findByUserId(userDetails.getID());
        map.addAttribute("accountList", accountList);

        return "deposit";
    }

//    @RequestMapping(value = "/saveDeposit", method = RequestMethod.POST)
//    public String saveDeposit(Account a, @AuthenticationPrincipal CustomUserDetails userDetails) {
//
//    }

// Model Pages
    @GetMapping("/list_page")
    public String listPage(Model model) {
        List<User> listUsers = (List<User>) userRepo.findAll();
        model.addAttribute("listUsers", listUsers);
        return "list_page";
    }
    @GetMapping("/detail_page")
    public String detailPage(Model model) {
        List<User> listUsers = (List<User>) userRepo.findAll();
        model.addAttribute("listUsers", listUsers);
        return "detail_page";
    }
}
