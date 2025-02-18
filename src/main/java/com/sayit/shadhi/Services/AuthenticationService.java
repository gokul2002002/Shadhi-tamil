package com.sayit.shadhi.Services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayit.shadhi.DTOs.AstrologerCreationDTO;
import com.sayit.shadhi.DTOs.LoginDTO;
import com.sayit.shadhi.Enums.GeneralStatus;
import com.sayit.shadhi.Exceptions.UserAlreadyExistException;
import com.sayit.shadhi.Exceptions.VerificationFailedException;
import com.sayit.shadhi.Models.Astrologer;
import com.sayit.shadhi.Models.User;
import com.sayit.shadhi.Repositories.AstrologerRepository;
import com.sayit.shadhi.Repositories.UserRepository;
import com.sayit.shadhi.Status;
import io.minio.errors.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Value("{security.secret.key}")
    private String secreatKey;
    @Value("{jwt.issuer.name}")
    private String issuer;
    @Value("${spring.mail.username}") private String sender;

    long expirationTime = 14 * 24 * 60 * 60 * 1000;
    private final UserRepository userRepository;
    private final AstrologerRepository astrologerRepository;
    private  final PasswordEncoder passwordEncoder;
    private final RedisService redisService;
    private final ObjectMapper objectMapper;
    private final MinioService minioService;
    private final JavaMailSender mailSender;
    public  String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public String sendOtpForVerification(String email)throws UsernameNotFoundException{
        Optional<User> user = userRepository.findByEmail(email);
        Optional<Astrologer> astrologer = astrologerRepository.findByEmail(email);
        if(user.isPresent() || astrologer.isPresent()){
            throw new UserAlreadyExistException("User with this mail id already exist");
        }
        String otp = generateOTP();
        redisService.addItem(email , otp , Duration.ofMinutes(10));
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(email);
        message.setSubject("Your OTP");
        message.setText(otp);
        mailSender.send(message);
        return "Verification Email Sent to ${email}";
    }

    public ResponseEntity<String> createUserInDb(User user){

        userRepository.save(user);
        return ResponseEntity.ok("Log in sucess");
    }


    public ResponseEntity<String> loginAsUser(LoginDTO loginDTO) throws UsernameNotFoundException, JsonProcessingException {
        Optional<User> user =  userRepository.findByEmail(loginDTO.getUserName());
        if(user.isPresent()){
            boolean isValid =  passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword());
            if (isValid){
                String jwt = JWT.create()
                        .withIssuer(issuer)
                        .withSubject(objectMapper.writeValueAsString(user.get()))
                        .withIssuedAt(new Date())
                        .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                        .withClaim("ROLE", user.get().getGender() == "MALE" ? "BRIDE" :"GROOM")
                        .sign(Algorithm.HMAC256(secreatKey));
                return ResponseEntity.ok().body(jwt);
            }
        }
        throw new UsernameNotFoundException("User Not Found");
    }

    public String signupAsUser(User user){
        String value = redisService.getItem(user.getEmail());
        if(!value.isBlank()){
            throw new VerificationFailedException("mail not verified");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "signed-in successfully";
    }


    public GeneralStatus verifyOtp(
            String email , String OTP
    ){
        if(OTP.equals(
                redisService.getItem(email)
        )){
            redisService.addItem(email , OTP , Duration.ofMinutes(10));
            return GeneralStatus.ACCEPTED;
        }
        throw new RuntimeException("Not a valid OTP");
    }

    public String signUpAsAstrologer(AstrologerCreationDTO astrologer) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Astrologer astrologer1 = Astrologer
                .builder()
                .email(astrologer.getEmail())
                .price(astrologer.getPrice())
                .Certrificate(
                        minioService.postImageToTheServer(astrologer.getCertrificate().getInputStream() , astrologer.getEmail().concat("_certrificate"))
                )
                .name(astrologer.getName())
                .YOE(astrologer.getYOE())
                .age(astrologer.getAge())
                .password(
                        passwordEncoder.encode(astrologer.getPassword())
                )
                .build();
        astrologerRepository.save(astrologer1);
        return "signed up as astrologer";
    }
    public String loginAsAstrologer(LoginDTO loginDTO) throws JsonProcessingException {
        Optional<Astrologer> astrologer = astrologerRepository.findByEmail(loginDTO.getUserName());
        if(astrologer.isPresent()){
            boolean isValid =  passwordEncoder.matches(loginDTO.getPassword(), astrologer.get().getPassword());
            if (isValid){
                String jwt = JWT.create()
                        .withIssuer(issuer)
                        .withSubject(objectMapper.writeValueAsString(astrologer.get()))
                        .withIssuedAt(new Date())
                        .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                        .withClaim("ROLE", "ASTRO")
                        .sign(Algorithm.HMAC256(secreatKey));
                return jwt;
            }
        }else {
            throw new UsernameNotFoundException("User not found for this mail");
        }
        return null;
    }

    public String validatePassword(){
        return null;
    }

}
