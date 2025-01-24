package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.OTP;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Long> {
    OTP findByEmailAndOtpCode(String email, String code);
}

