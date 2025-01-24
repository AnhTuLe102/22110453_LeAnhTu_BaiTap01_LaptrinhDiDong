package Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.OTP;
import repository.OTPRepository;

import java.time.LocalDateTime;
import java.util.Random;
@Service
public class OTPService {
	@Autowired
    private OTPRepository otpRepository;

    public String generateOTP(String email) {
        String otp = String.valueOf(new Random().nextInt(999999));
        OTP otpEntity = new OTP();
        otpEntity.setEmail(email);
        otpEntity.setOtpCode(otp);
        otpEntity.setExpirationTime(LocalDateTime.now().plusMinutes(5));
        otpRepository.save(otpEntity);
        return otp;
    }

    public boolean validateOTP(String email, String otpCode) {
        OTP otp = otpRepository.findByEmailAndOtpCode(email, otpCode);
        if (otp == null || otp.getExpirationTime().isBefore(LocalDateTime.now())) {
            return false;
        }
        otpRepository.delete(otp);
        return true;
    }
}
