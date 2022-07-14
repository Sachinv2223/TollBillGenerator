package com.cts.controller;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.entities.BankDetails;
import com.cts.entities.Manager;
import com.cts.entities.TollDetails;
import com.cts.entities.User;
import com.cts.entities.UserFeedback;
import com.cts.entities.UserTollDetails;
import com.cts.repositories.BankRepository;
import com.cts.repositories.ManagerRepository;
import com.cts.repositories.TollDetailsRepository;
import com.cts.repositories.UserRepository;
import com.cts.repositories.UserTollDetailsRepository;
import com.cts.services.AdminService;
import com.cts.services.ManagerService;
import com.cts.services.TollDetailsService;
import com.cts.services.UserFeedbackService;
import com.cts.services.UserService;

/*This is the controller class for all general purpose mappings*/
@Controller
public class IndexController {

	@Autowired
	private UserService uservice;

	@Autowired
	private ManagerService mservice;

	@Autowired
	private AdminService aservice;

	@Autowired
	private TollDetailsService tservice;

	@Autowired
	private UserFeedbackService ufservice;

	@Autowired
	private UserRepository urepo;

	@Autowired
	private ManagerRepository mrepo;

	@Autowired
	private TollDetailsRepository trepo;

	@Autowired
	private UserTollDetailsRepository utrepo;

	@Autowired
	private BankRepository brepo;


	// Mapping for User Login page
	@GetMapping("/")
	public String indexUser() {
		return "indexUser";
	}

	// Mapping for Manger Login page
	@GetMapping("/manager")
	public String indexManager() {
		return "indexManager";
	}

	// Mapping for Admin login page
	@GetMapping("/admin")
	public String indexAdmin() {
		return "indexAdmin";
	}

	/* Registration of user */
	// Mapping for user registration page
	@GetMapping("/regUser")
	public String regUser(@ModelAttribute(name = "user") User user) {
		return "regUser";
	}

	/*
	 * For showing error while writing same email id and saving registration of user
	 */

	// validating user fields and checking uniqueness of email id
	@PostMapping("/regUser")
	public String regUserSave(@Valid @ModelAttribute(name = "user") User user, BindingResult result,
			HttpSession session, Model m) {
		if (result.hasErrors()) {
			return "regUser";
		}
		try {
			uservice.saveUser(user);
		} catch (Exception e) {
			m.addAttribute("errMsg", "* Email Id is already registered. Please use another email");
			return "regUser";
		}
		session.setAttribute("regMsg", "Registered Successfully!!!");
		return "redirect:/";
	}

	/* For mapping to manager registration page */
	@GetMapping("/regManager")
	public String regManager(@ModelAttribute(name = "manager") Manager manager) {
		return "regManager";
	}

	/* To check unique email while enetring manager registration */
	@PostMapping("/regManager")
	public String regManagerSave(@Valid @ModelAttribute(name = "manager") Manager manager, BindingResult result,
			HttpSession session, Model m) {
		if (result.hasErrors()) {
			return "regManager";
		}
		try {
			mservice.saveManager(manager);
		} catch (Exception e) {
			m.addAttribute("errMsg", "* Email Id is already registered. Please use another email");
			return "regManager";
		}
		session.setAttribute("regMsg", "Your Details are Submitted for Admin Approval!!!");
		return "redirect:/manager";
	}

	/*
	 * create table in mysql as well as add admin details in that table to run admin
	 * login
	 */

	// validating admin
	@PostMapping("/loginAdmin")
	public String adminLogin(HttpSession session, @RequestParam String aemail, @RequestParam String apassword) {
		boolean isValidAdmin = aservice.validateAdmin(aemail, apassword);

		if (!isValidAdmin) {
			session.setAttribute("logMsg", "Invalid Credentials");
			return "redirect:/admin";
		} else {
			session.removeAttribute("logMsg");
			return "homeAdmin";
		}
	}

	// getting all manager details as List
	@GetMapping("/managerDetails")
	public String showManagerDetails(Model m) {
		List<Manager> managerList = mservice.showAllManagers();
		m.addAttribute("managerList", managerList);
		return "managerDetails";
	}

	// checking user credentials for login
	@PostMapping("/loginUser")
	public String userLogin(@RequestParam String uuserName, @RequestParam String upassword, HttpSession session) {

		boolean isValidUser = uservice.validateUser(uuserName, upassword);
		if (!isValidUser) {
			session.setAttribute("logMsg", "Invalid Credentials");
			return "redirect:/";
		} else {
			session.removeAttribute("logMsg");
			session.removeAttribute("regMsg");
			User user = urepo.findByUuserNameAndUpassword(uuserName, upassword);
			session.setAttribute("eUser", user);
			return "homeUser";
		}
	}

	// checking Manager credentials for login
	@PostMapping("/loginManager")
	public String userManager(@RequestParam String muserName, @RequestParam String mpassword, HttpSession session) {

		boolean isValidManager = mservice.validateManager(muserName, mpassword);
		if (!isValidManager) {
			session.setAttribute("logMsg", "Invalid Credentials");
			return "redirect:/manager";
		} else {
			boolean approvedManager = mservice.approvedManager(muserName, mpassword);
			if (!approvedManager) {
				session.setAttribute("logMsg", "Registration not approved");
				session.removeAttribute("regMsg");
				return "redirect:/manager";
			}
			session.removeAttribute("logMsg");
			session.removeAttribute("regMsg");
			Manager eManager = mrepo.findByMuserNameAndMpassword(muserName, mpassword);
			session.setAttribute("eManager", eManager);
			return "homeManager";
		}
	}

	// to show manager details to admin
	@GetMapping("/modifyApproval")
	public String modifyMapproval(@RequestParam int mid, ModelMap model) {
		Manager eManager = mrepo.findById(mid).orElse(null);
		model.addAttribute("eManager", eManager);
		return "modifyApproval";
	}

	// to save modified manager approval
	@PostMapping("/saveModifiedApproval")
	public String saveModifiedApprovalByAdmin(@ModelAttribute Manager eManager, HttpSession session) {
		mservice.saveManager(eManager);
		session.setAttribute("logMsg", "Approval Status changed Successfully!!!");
		return "redirect:/managerDetails";

	}

	// to delete manager registration
	@GetMapping("/deleteApproval")
	public String deleteMapproval(@RequestParam int mid, HttpSession session) {
		mrepo.deleteById(mid);
		session.setAttribute("logMsg", "Manager registration deleted Sucessfully!!!");
		return "redirect:/managerDetails";
	}

	/* To show toll details to manager */
	@GetMapping("/tollDetailsIndex")
	public String viewTollDetailsByManager(Model m) {
		List<TollDetails> tollList = tservice.showAllTollDetails();
		m.addAttribute("tollList", tollList);
		return "tollDetailsManager";

	}

	/* Map to add toll detail page by manager */
	@GetMapping("/addTollDetails")
	public String addTollDetailsByManager() {
		return "addTollDetails";
	}

	/* To save toll details to repository by manager */
	@PostMapping("/saveTollDetail")
	public String saveTollDetailsByManager(@ModelAttribute TollDetails tollDetails, HttpSession session) {
		tservice.saveTollDetail(tollDetails);
		session.setAttribute("logMsg", "Toll Details Added Sucessfully!!!");
		return "redirect:/tollDetailsIndex";
	}

	/* To edit a toll detail by manager */
	@GetMapping("/modifyTollDetails")
	public String modifyTollDetailsByManager(@RequestParam int tid, ModelMap model) {
		TollDetails etollDetails = trepo.findById(tid).orElse(null);
		model.addAttribute("etollDetails", etollDetails);
		return "editTollDetails";
	}

	/* To update toll details modified by manager */
	@PostMapping("/updateTollDetails")
	public String updateTollDetailsByManager(@ModelAttribute TollDetails etollDetails, HttpSession session) {
		trepo.save(etollDetails);
		session.setAttribute("logMsg", "Toll Details Updated Successfully!!!");
		return "redirect:/tollDetailsIndex";
	}

	/* To delete a toll detail by manager */
	@GetMapping("/deleteTollDetails")
	public String deleteTollDetailsByManager(@RequestParam int tid, HttpSession session) {
		trepo.deleteById(tid);
		session.setAttribute("logMsg", "Toll details deleted Sucessfully!!!");
		return "redirect:/tollDetailsIndex";
	}

	/* To show toll details to Admin */
	@GetMapping("/TollDetailsAdmin")
	public String viewTollDetailsByAdmin(Model m) {
		List<TollDetails> tollList = tservice.showAllTollDetails();
		m.addAttribute("tollList", tollList);
		return "tollDetailsAdmin";

	}

	/* To edit a toll detail by admin */
	@GetMapping("/modifyTollDetailsAdmin")
	public String modifyTollDetailsByAdmin(@RequestParam int tid, ModelMap model) {
		TollDetails etollDetails = trepo.findById(tid).orElse(null);
		model.addAttribute("etollDetails", etollDetails);
		return "editTollApprovalAdmin";
	}

	/* To update toll details modified by Admin */
	@PostMapping("/updateTollDetailsAdmin")
	public String updateTollDetailsByAdmin(@ModelAttribute TollDetails etollDetails, HttpSession session) {
		trepo.save(etollDetails);
		session.setAttribute("logMsg", "Toll Details Updated Successfully!!!");
		return "redirect:/TollDetailsAdmin";
	}

	/* To delete a toll detail by Admin */
	@GetMapping("/deleteTollDetailsAdmin")
	public String deleteTollDetailsByAdmin(@RequestParam int tid, HttpSession session) {
		trepo.deleteById(tid);
		session.setAttribute("logMsg", "Toll details deleted Sucessfully!!!");
		return "redirect:/TollDetailsAdmin";
	}

	/* get to homepage of user */
	@GetMapping("/toHomepageUser")
	public String toHomepageUser() {
		return "homeUser";
	}

	/* get to homepage of manager */
	@GetMapping("/toHomepageManager")
	public String toHomepageManager() {
		return "homeManager";
	}

	/* get to homepage of admin */
	@GetMapping("/toHomepageAdmin")
	public String toHomepageAdmin() {
		return "homeAdmin";
	}

	/* get to tolldetails of user */
	@GetMapping("/TollDetailsUser")
	public String showLocations(Model m) {
		List<TollDetails> locationsList = tservice.showAllTollDetails();
		m.addAttribute("locationsList", locationsList);
		return "userTollDetails";
	}

	/* logout of user */
	@GetMapping("/logoutUser")
	public String userLogout(HttpSession session) {
		session.removeAttribute("eUser");
		return "indexUser";
	}

	/* logout of manager */
	@GetMapping("/logoutManager")
	public String managerLogout(HttpSession session) {
		session.removeAttribute("eManager");
		return "indexManager";
	}

	/* logout of admin */
	@GetMapping("/logoutAdmin")
	public String adminLogout() {
		return "indexAdmin";
	}

	/* go to add user feedback page */
	@GetMapping("/addUserFeedback")
	public String AddFeedbackbyUser() {
		return "addFeedbackUser";
	}

	/* To save feedback given by user */
	@PostMapping("/saveUserFeedback")
	public String saveUser(@ModelAttribute UserFeedback userFeedback, HttpSession session) {
		ufservice.saveUserFeedback(userFeedback);
		session.setAttribute("logMsg", "Feedback Added Sucessfully!!!");
		return "homeUser";
	}

	/* To show user feedback to admin */
	@GetMapping("/viewUserFeedbackByAdmin")
	public String viewUserFeedbackByAdmin(Model m) {
		List<UserFeedback> userFeedbackList = ufservice.showAllUserFeedback();
		m.addAttribute("userFeedbackList", userFeedbackList);
		return "userFeedbackDetailsAdmin";

	}

	@GetMapping("/addFeedbackDownloadMsg")
	public String addFeedbackDownloadMsgAdmin(HttpSession session) {
		session.setAttribute("logMsg", "Feedback Downloaded Sucessfully!!!");
		return "redirect:/viewUserFeedbackByAdmin";
	}

	@GetMapping("/addTollDetailsDownloadMsg")
	public String addTollDetailsDownloadMsgAdmin(HttpSession session) {
		session.setAttribute("logMsg", "Toll Details Downloaded Sucessfully!!!");
		return "redirect:/TollDetailsAdmin";
	}

	/* get to tolldetails of user */
	@PostMapping("/confirmPayment")
	public String showLocationsUser(Model m, @RequestParam String fsource, @RequestParam String fdestination,
			@RequestParam String fvtype, @RequestParam String foneortwo, @RequestParam int fprice,
			@RequestParam int ftid, @RequestParam String fjdate, HttpSession session) {

		User eUser = (User) session.getAttribute("eUser");
		UserTollDetails utDetails = new UserTollDetails();
		utDetails.setPrice(fprice);
		utDetails.setOneortwo(foneortwo);
		utDetails.setUser(eUser);

		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		utDetails.setCreatedDate(date);

		DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		LocalDate jdate = LocalDate.parse(fjdate, f);

		utDetails.setJourneyDate(jdate);

		TollDetails eTollDetails = trepo.findByFromLocationAndToLocationAndVechtype(fsource, fdestination, fvtype);

		utDetails.setTolldetails(eTollDetails);
		session.setAttribute("eprice", fprice);
		utrepo.save(utDetails);

		return "bankLoginBuying";
	}

	/* get to tolldetails of user */
	@PostMapping("/checkBankCredentials")
	public String checkingBankCrendentials(ModelMap m, HttpSession session, @RequestParam String buserName,
			@RequestParam String bpassword) {
		BankDetails eBankDetails = brepo.findByBuserNameAndBpassword(buserName, bpassword);
		if (eBankDetails == null) {
			session.setAttribute("logMsg", "Invalid Credentials");
			return "bankLoginBuying";
		} else {
			session.removeAttribute("logMsg");
			int balanceAmount = eBankDetails.getBalanceAmount();
			int charge = (int) session.getAttribute("eprice");

			balanceAmount = balanceAmount - charge;
			eBankDetails.setBalanceAmount(balanceAmount);
			brepo.save(eBankDetails);

			m.addAttribute("charge", charge);
			m.addAttribute("balanceAmount", balanceAmount);
			return "confirmationPage";
		}
	}

	/* To edit user profile */
	@GetMapping("/editUserProfile")
	public String editUserProfileByUser(HttpSession session, ModelMap model) {

		User eUser = (User) session.getAttribute("eUser");
		model.addAttribute("eUser", eUser);
		return "editUserProfile";
	}

	/* To update user profile */
	@PostMapping("/updateUserProfile")
	public String updateUserProfileByUser(@Valid @ModelAttribute(name = "eUser") User eUser, BindingResult result,
			HttpSession session, Model m) {
		if (result.hasErrors()) {
			return "editUserProfile";
		}
		try {
			uservice.saveUser(eUser);
		} catch (Exception e) {
			m.addAttribute("errMsg", "* Email Id is already registered. Please use another email");
			return "editUserProfile";
		}
		session.setAttribute("logMsg", "Profile Updated Successfully!!!");
		session.setAttribute("eUser", eUser);
		return "homeUser";
	}

	/* To view user journey details in a table */
	@GetMapping("/viewUserJourneyDetails")
	public String viewUserJourneyDetailsByUser(Model m, HttpSession session) {
		User eUser = (User) session.getAttribute("eUser");
		int eUid = eUser.getUid();
		m.addAttribute("eUid", eUid);

		List<UserTollDetails> userTollList = utrepo.findAll();
		m.addAttribute("userTollList", userTollList);

		return "viewJourneyDetails";
	}

	/* to cancel a planned journey */
	@GetMapping("/deleteJourneyDetails")
	public String deleteJourneyDetailsByUser(@RequestParam int id, @RequestParam int price, HttpSession session,
			ModelMap model) {

		model.addAttribute("price", price);
		utrepo.deleteById(id);
		session.setAttribute("log1Msg", "Journey Cancelled Sucessfully!!!");
		return "bankLoginDeletion";

	}

	/* To enter bank details for refund */
	@PostMapping("/checkBankCredentialsDeletion")
	public String checkingBankCrendentialsDeletionUser(ModelMap m, HttpSession session, @RequestParam String buserName,
			@RequestParam String bpassword, @RequestParam int price) {
		BankDetails eBankDetails = brepo.findByBuserNameAndBpassword(buserName, bpassword);
		if (eBankDetails == null) {
			session.setAttribute("logMsg", "Invalid Credentials");
			m.addAttribute("price", price);
			return "bankLoginDeletion";
		} else {
			session.removeAttribute("logMsg");
			int balanceAmount = eBankDetails.getBalanceAmount();
			balanceAmount = balanceAmount + price;
			eBankDetails.setBalanceAmount(balanceAmount);
			brepo.save(eBankDetails);

			m.addAttribute("price", price);
			m.addAttribute("balanceAmount", balanceAmount);
			return "confirmationPageDeletion";
		}
	}

	/* To modify an already planned user journey */
	@PostMapping("/ModifyTollDetailsUser")
	public String modifyLocationsUser(Model m, @RequestParam String fsource, @RequestParam String fdestination,
			@RequestParam String fvtype, @RequestParam String foneortwo, @RequestParam int fprice,
			@RequestParam int ftid, @RequestParam String fjdate, HttpSession session) {
		List<TollDetails> locationsList = tservice.showAllTollDetails();

		m.addAttribute("locationsList", locationsList);
		m.addAttribute("defaultSource", fsource);
		m.addAttribute("defaultDestination", fdestination);
		m.addAttribute("defaultVtype", fvtype);
		m.addAttribute("defaultOneorTwo", foneortwo);
		m.addAttribute("defaultPrice", fprice);
		m.addAttribute("modifyId", ftid);
		m.addAttribute("defaultJDate", fjdate);
		return "modifyUserTollDetails";
	}

	/* modify tolldetails of user */
	@PostMapping("/confirmModifyPayment")
	public String modifyConfirmPayment(Model m, @RequestParam String fsource, @RequestParam String fdestination,
			@RequestParam String fvtype, @RequestParam String foneortwo, @RequestParam int foldprice,
			@RequestParam int fprice, @RequestParam int ftid, @RequestParam String fjdate, HttpSession session) {

		User eUser = (User) session.getAttribute("eUser");
		UserTollDetails utDetails = new UserTollDetails();
		utDetails.setId(ftid);
		utDetails.setPrice(fprice);
		utDetails.setOneortwo(foneortwo);
		utDetails.setUser(eUser);

		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		utDetails.setCreatedDate(date);

		DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		LocalDate jdate = LocalDate.parse(fjdate, f);

		utDetails.setJourneyDate(jdate);

		TollDetails eTollDetails = trepo.findByFromLocationAndToLocationAndVechtype(fsource, fdestination, fvtype);

		utDetails.setTolldetails(eTollDetails);
		session.setAttribute("eprice", fprice);
		session.setAttribute("eoldprice", foldprice);
		utrepo.save(utDetails);

		return "bankLoginModification";
	}

	/* To check bank credentials for bank transactions */
	@PostMapping("/checkBankCredentialsformodification")
	public String checkingBankCrendentialsformodification(ModelMap m, HttpSession session,
			@RequestParam String buserName, @RequestParam String bpassword) {
		BankDetails eBankDetails = brepo.findByBuserNameAndBpassword(buserName, bpassword);
		if (eBankDetails == null) {
			session.setAttribute("logMsg", "Invalid Credentials");
			return "bankLoginBuying";
		} else {
			session.removeAttribute("logMsg");
			int balanceAmount = eBankDetails.getBalanceAmount();
			int charge = (int) session.getAttribute("eprice");
			int oldcharge = (int) session.getAttribute("eoldprice");
			if (charge >= oldcharge) {
				charge = charge - oldcharge;
				balanceAmount = balanceAmount - charge;
				eBankDetails.setBalanceAmount(balanceAmount);
				brepo.save(eBankDetails);

				m.addAttribute("charge", charge);
				m.addAttribute("balanceAmount", balanceAmount);
				return "confirmationPage";
			} else {
				charge = oldcharge - charge;
				balanceAmount = balanceAmount + charge;
				eBankDetails.setBalanceAmount(balanceAmount);
				brepo.save(eBankDetails);

				m.addAttribute("price", charge);
				m.addAttribute("balanceAmount", balanceAmount);
				return "confirmationPageDeletion";
			}
		}
	}
}
