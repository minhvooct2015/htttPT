package com.example;

import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;


@ApplicationScoped
public class LoginService {

    @Inject
    UserRepository userRepository;

    public UserEntity login(String taiKhoan, String matKhau) {
        UserEntity user = userRepository.findByTaiKhoan(taiKhoan);
        if (user == null || !user.checkPassword(matKhau)) {
            throw new ForbiddenException("Invalid username or password");
        }
        return user; // Replace with actual JWT token
    }

    public UserEntity findByTK(String taiKhoan) {
        return userRepository.findByTaiKhoan(taiKhoan);

    }



    public UserEntity register(UserRegistrationRequest request) {
        // Check if the user already exists
        if (userRepository.findByTaiKhoan(request.getTaiKhoan()) != null) {
            throw new BadRequestException("User already exists");
        }

        // Create a new User entity
        UserEntity newUser = new UserEntity();
        newUser.setHoTen(request.getHoTen());
        newUser.setEmail(request.getEmail());
        newUser.setSdt(request.getSdt());
        newUser.setDiaChi(request.getDiaChi());
        newUser.setMatKhau(request.getMatKhau()); // Hash password
        newUser.setTrangThai(request.getTrangThai());
        newUser.setTaiKhoan(request.getTaiKhoan());
        if(request.getVaiTro() != null)
        newUser.setVaiTro(request.getVaiTro());
        else newUser.setVaiTro(VaiTro.KHACHHANG);

        // Persist the new user to the database
        userRepository.save(newUser);
        return newUser;
    }

}
