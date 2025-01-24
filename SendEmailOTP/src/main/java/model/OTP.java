package model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class OTP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email; // Email của người nhận OTP
    private String otpCode;  // Mã OTP
    private LocalDateTime expirationTime; // Thời gian hết hạn OTP
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getOtpCode() {
		return otpCode;
	}
	public void setOtpCode(String otpCode) {
		this.otpCode = otpCode;
	}
	public LocalDateTime getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(LocalDateTime expirationTime) {
		this.expirationTime = expirationTime;
	}
	
	public OTP() {
		
	}
	public OTP(Long id, String email, String code, LocalDateTime expirationTime) {
		super();
		this.id = id;
		this.email = email;
		this.otpCode = code;
		this.expirationTime = expirationTime;
	}

    
}
