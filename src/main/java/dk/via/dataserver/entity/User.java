package dk.via.dataserver.entity;

import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import jakarta.persistence.*;

@Entity
@Table(name ="users", schema ="sep3" )
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name ="username", unique = true ,nullable = false)
    String username;
    @Column(name="password", nullable = false)
    String password;
    @Column(name ="email", unique = true,nullable = false)
    String email;
    public void setUsername(String username) {
        //instead of StringUtility we used StringUtil if some errors later that may be the case

        if(StringUtil.isNullOrEmpty(username)){
            throw new IllegalArgumentException("username characters  cannot be null or empty");
        } else if (username.length() < 4 || username.length()>30) {
            throw new IllegalArgumentException("username characters must be less 30 characters long");
        }
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
