package controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import Service.EmailService;
import Service.OTPService;
import model.User;
import repository.UserRepository;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPService otpService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setEnabled(false);
        userRepository.save(user);

        String otp = otpService.generateOTP(user.getEmail());
        emailService.sendEmail(user.getEmail(), "Account Activation", "Your OTP: " + otp);

        return "Registration successful! Check your email for the OTP.";
    }

    @PostMapping("/activate")
    public String activateAccount(@RequestParam String email, @RequestParam String otp) {
        if (otpService.validateOTP(email, otp)) {
            User user = userRepository.findByEmail(email);
            user.setEnabled(true);
            userRepository.save(user);
            return "Account activated successfully!";
        }
        return "Invalid OTP or expired.";
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email) {
        String otp = otpService.generateOTP(email);
        emailService.sendEmail(email, "Reset Password", "Your OTP: " + otp);
        return "OTP sent to your email.";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String email, @RequestParam String otp, @RequestParam String newPassword) {
        if (otpService.validateOTP(email, otp)) {
            User user = userRepository.findByEmail(email);
            user.setPassword(newPassword); // Nên mã hóa mật khẩu trước khi lưu
            userRepository.save(user);
            return "Password reset successful!";
        }
        return "Invalid OTP or expired.";
    }
}
